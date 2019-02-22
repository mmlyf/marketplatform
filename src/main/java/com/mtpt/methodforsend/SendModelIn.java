package com.mtpt.methodforsend;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;
import org.omg.CORBA.REBIND;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.mtpt.alibean.TBRecord;
import com.mtpt.alibean.TBReview;
import com.mtpt.aliservice.impl.TBRecordService;
import com.mtpt.aliservice.impl.TBReviewService;
import com.mtpt.bean.RepeatOpera;
import com.mtpt.bean.Review;
import com.mtpt.bean.TBDsjDxAll;
import com.mtpt.bean.TBDsjIceAll;
import com.mtpt.bean.TBMssage;
import com.mtpt.bean.page.TBMessagePage;
import com.mtpt.config.SpringContextUtil;
import com.mtpt.service.impl.TBDsjDxAllService;
import com.mtpt.service.impl.TBDsjIceAllService;
import com.mtpt.service.impl.TBMssageService;
import com.sun.org.apache.xml.internal.security.utils.Base64;

import sun.nio.ch.SelChImpl;

public class SendModelIn {
	private static TBMssageService mssageService = (TBMssageService) SpringContextUtil.getBean("tbMssageService");
	private static TBDsjIceAllService iceAllService = (TBDsjIceAllService) SpringContextUtil.getBean("iceservice");
	private static TBDsjDxAllService dxAllService	= (TBDsjDxAllService) SpringContextUtil.getBean("dxservice");
	private static TBReviewService reviewService = (TBReviewService) SpringContextUtil.getBean("reservice");
	private static Logger log = Logger.getLogger(SendFileIn.class);
	private static BlockingQueue<String> queue = new LinkedBlockingQueue<String>();
	private static String messageContent = "";
	private static ExecutorService pool = Executors.newSingleThreadExecutor();
	private static int re_id;
	public static boolean isStop = false;
	public static boolean isEnd = false;
	private static String sectime ;
	public static ArrayList<Integer> waitsend = new ArrayList<Integer>();
	public SendModelIn() {
		// TODO Auto-generated constructor stub
	}

	public static int getRe_id() {
		return re_id;
	}

	public static void addRe_id(final int id) {
		if (pool.isShutdown()) {
			pool = Executors.newSingleThreadExecutor();
		}
		pool.execute(new Runnable() {
			public void run() {
				// TODO Auto-generated method stub
				re_id = id;
				TBReview tbReview = new TBReview();
				tbReview.setId(id);
				tbReview.setState(3);
				reviewService.updateByPrimaryKeySelective(tbReview);
				send(id);
			}
		});
	}

	private static void send(int id) {
		TBReview tbReview = reviewService.selectByPrimaryKey(id);
		Review review = new Review();
		review.setCity(tbReview.getCity());
		review.setDangw(tbReview.getDangw());
		review.setProduct(tbReview.getProduct());
		review.setIfdx(tbReview.getIfdx());
		review.setIfrh(tbReview.getIfrh());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String sectime = sdf.format(tbReview.getSecTime());
		review.setSecTime(sectime);
		if (tbReview.getPrelx().equals("dsj_dx_all")) {
			List<TBDsjDxAll> dxAlls = dxAllService.selectByReview(review);
			for(TBDsjDxAll tbDsjDxAll:dxAlls) {
				queue.offer(tbDsjDxAll.getDxDn());
			}
		}else {
			List<TBDsjIceAll> iceAlls = iceAllService.selectByReview(review);
			for (TBDsjIceAll tbDsjIceAll:iceAlls) {
				queue.offer(tbDsjIceAll.getDxDn());
			}
		}
		TBMssage tbMssage = mssageService.selectByPrimaryKey(tbReview.getMigId());
		messageContent = tbMssage.getMisContent();
		log.info("当前消息的值是："+messageContent);
		outQueueForSend(queue,tbReview);
	}

	private static void outQueueForSend(BlockingQueue<String> queue,TBReview tbReview) {
		log.info("进入");
		List<String> list = new ArrayList<String>();
		int count = 0;
		while(true) {
			try {
				if (!isEnd) {
					if (!isStop) {
						if (!queue.isEmpty()) {
							log.info("指定");
							list.add(queue.take());
							count++;
						}else {
							log.info("当前队列已经为空");
						}
						if(count%80==0||(queue.isEmpty()&&count>0)) {
							if (tbReview.getIsdelblack().equals("是")) {
								list = Remove.blackList(list);
							}
							if(tbReview.getIsdeldays().equals("是")) {
								RepeatOpera repeatOpera = new RepeatOpera();
								Date date = new Date();
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
								repeatOpera.setDatain(sdf.format(date));
								repeatOpera.setDays(tbReview.getDeldays());
								list = Remove.threeDayList(list,repeatOpera);
							}
							if(!list.isEmpty()) {
								Thread thread = new Thread(new HandlerSend(list, messageContent, tbReview.getId(),2));
								thread.start();
								Thread.currentThread().sleep(3000);
								list.removeAll(list);
							}else {
								log.debug("当前的发送的队列被筛选之后，无数据发送");
							}
						}
						if (queue.isEmpty()) {
							break;
						}
					}else {
						Thread.currentThread().sleep(1000);
					}
				}else {
					Thread.currentThread().sleep(1000);
					pool.shutdownNow();
					break;
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (!isEnd&&queue.isEmpty()) {
			System.out.println("队列数据取完");
			TBReview overreview = new TBReview();
			overreview.setId(re_id);
			overreview.setState(4);
			int result = reviewService.updateByPrimaryKeySelective(overreview);
			Iterator<Integer> it = waitsend.iterator();
			while (it.hasNext()) {
				if (it.next()==re_id) {
					it.remove();
				}
			}
			if (result>0) {
				log.info("发送完成");
			}
		}else {
			log.info("停止");
			for(Integer id:waitsend) {
				TBReview endreview = new TBReview();
				endreview.setId(id);
				endreview.setState(7);
				reviewService.updateByPrimaryKeySelective(endreview);
			}
			isEnd = false;
			isStop = false;
			waitsend.clear();
		}
	}
}

