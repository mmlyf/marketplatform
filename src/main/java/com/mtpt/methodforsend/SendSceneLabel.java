package com.mtpt.methodforsend;

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

import com.mtpt.alibean.TBReview;
import com.mtpt.alibean.TBSceneData;
import com.mtpt.alibean.TBSceneJob;
import com.mtpt.aliservice.impl.TBSceneDataService;
import com.mtpt.aliservice.impl.TBSceneJobService;
import com.mtpt.bean.RepeatOpera;
import com.mtpt.bean.TBMssage;
import com.mtpt.config.SpringContextUtil;
import com.mtpt.service.impl.TBMssageService;

public class SendSceneLabel {
	private static Logger log = Logger.getLogger(SendSceneLabel.class);
	private static TBMssageService mssageService = (TBMssageService) SpringContextUtil.getBean("tbMssageService");
	private static TBSceneJobService tbSceneJobService = (TBSceneJobService) SpringContextUtil.getBean("tbscenejobservice");
	private static TBSceneDataService tbSceneDataService = (TBSceneDataService) SpringContextUtil.getBean("tbsceneService");
	public static boolean isStop = false;
	public static boolean isEnd = false;
	private static int job_id;
	private static BlockingQueue<String> queue = new LinkedBlockingQueue<>();
	private static ExecutorService pool = Executors.newSingleThreadExecutor();
	public static ArrayList<Integer> waitsend = new ArrayList<>();
	private static String messageContent ;
	
	public SendSceneLabel() {}
	public static int getJob_id() {
		return job_id;
	}
	public static void setJob_id(int id) {
		if(pool.isShutdown()) {
			pool = Executors.newSingleThreadExecutor();
		}
		pool.execute(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				job_id = id;
				TBSceneJob tbSceneJob = new TBSceneJob();
				tbSceneJob.setId(id);
				tbSceneJob.setState(3);
				tbSceneJobService.updateByPrimaryKeySelective(tbSceneJob);		
				runJob(job_id);
			}
		});
	}
	
	/**
	 * 
	 * @param job_id
	 * 执行从数据库中取数据并将数据放入队列里
	 * 
	 */
	private static void runJob(int job_id) {
		TBSceneJob tbSceneJob = tbSceneJobService.selectByPrimaryKey(job_id);
		String[] labels = tbSceneJob.getSceneBq().split(",");
		List<String> resultlist = new ArrayList<>();
		for(int i = 0 ; i< labels.length; i++) {
			List<TBSceneData> scenedatalist = tbSceneDataService.selectDataByLabel(labels[i]);
			List<String> phonelist = new ArrayList<>();
			for(TBSceneData tbSceneData:scenedatalist) {
				phonelist.add(tbSceneData.getSceneDn());
			}
			switch (tbSceneJob.getBqOpera()) {
			case "1"://交集
				if (resultlist.isEmpty()) {
					resultlist = phonelist;
				}else {
					if (!phonelist.isEmpty()) {
						resultlist.retainAll(phonelist);
					}
				}
				break;
			case "2"://并集
			case "3"://无
				if (resultlist.isEmpty()) {
					resultlist = phonelist;
				}else {
					if (!phonelist.isEmpty()) {
						phonelist.removeAll(resultlist);
						resultlist.addAll(phonelist);
					}
				}
				break;
			default:
				break;
			}
		}
		TBMssage tbMssage = mssageService.selectByPrimaryKey(tbSceneJob.getMisId());
		messageContent = tbMssage.getMisContent();
		for(String phone : resultlist) {
			queue.offer(phone);
		}
		outQueue(queue, tbSceneJob);
	}
	
	private static void outQueue(BlockingQueue<String> queue,TBSceneJob sceneJob) {
		List<String> phones = new ArrayList<>();
		int count = 0;
		while(true) {
			try {
			if (!isEnd) {
				if (!isStop) {
					if (!queue.isEmpty()) {
						phones.add(queue.take());
						count ++;
					}
					if(count%80==0||(queue.isEmpty()&&count>0)) {
						if (sceneJob.getIsDelblack().equals("是")) {
							phones = Remove.blackList(phones);
						}
						if (sceneJob.getIsDeldays().equals("是")) {
							RepeatOpera repeatOpera = new RepeatOpera();
							Date date = new Date();
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							repeatOpera.setDatain(sdf.format(date));
							repeatOpera.setDays(sceneJob.getDeldays());
							phones = Remove.threeDayList(phones,repeatOpera);
						}
						if(!phones.isEmpty()) {
							Thread thread = new Thread(new HandlerSend(phones, messageContent, sceneJob.getId(),3));
							thread.start();
							Thread.currentThread().sleep(2000);
							phones.removeAll(phones);
						}
					}
					if(queue.isEmpty()) {
						log.debug("队列为空");
						break;
					}
				}else {
					Thread.currentThread().sleep(1000);
				}
			}else {
				Thread.currentThread().sleep(1000);
			}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		TBSceneJob tbSceneJob = new TBSceneJob();
		if (!isEnd&&queue.isEmpty()) {
			System.out.println("队列数据取完");
			
			tbSceneJob.setId(job_id);
			tbSceneJob.setState(4);
			int result = tbSceneJobService.updateByPrimaryKeySelective(tbSceneJob);
			Iterator<Integer> it = waitsend.iterator();
			while (it.hasNext()) {
				if (it.next()==job_id) {
					it.remove();
				}
			}
			if (result>0) {
				log.info("发送完成");
			}
		}else {
			log.info("停止");
			for(Integer id:waitsend) {
				tbSceneJob.setId(id);
				tbSceneJob.setState(7);
				tbSceneJobService.updateByPrimaryKeySelective(tbSceneJob);
			}
			isEnd = false;
			isStop = false;
			waitsend.clear();
		}
	}
}
