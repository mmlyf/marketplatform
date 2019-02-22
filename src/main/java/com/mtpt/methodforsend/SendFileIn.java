package com.mtpt.methodforsend;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
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

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.mtpt.alibean.TBRecord;
import com.mtpt.aliservice.impl.TBRecordService;
import com.mtpt.bean.RepeatOpera;
import com.mtpt.bean.TBMssage;
import com.mtpt.config.SpringContextUtil;
import com.mtpt.service.impl.TBMssageService;
public class SendFileIn {
	
	private static TBMssageService mssageService = (TBMssageService) SpringContextUtil.getBean("tbMssageService");
	private static TBRecordService recordService = (TBRecordService) SpringContextUtil.getBean("tbrecord");
	private static Logger log = Logger.getLogger(SendFileIn.class);
	private static BlockingQueue<String> dataphonequeue = new LinkedBlockingQueue<String>();
	private static String messageContent = "";
	private static ExecutorService pool = Executors.newSingleThreadExecutor();
	private static int taskid;
	public static boolean isStop = false;
	public static boolean isEnd = false;
	private static String sectime ;
	public static ArrayList<Integer> waitsend = new ArrayList<Integer>();
	public SendFileIn() {}
	
	public static int getTaskid() {
		return taskid;
	}
	//将文件发送任务添加至任务池
	public static void addTaskid(final int id) {
		if (pool.isShutdown()) {
			pool = Executors.newSingleThreadExecutor();
		}
		log.info("等待列表中的数量是："+waitsend.size());
		pool.execute(new Runnable() {
			public void run() {
				// TODO Auto-generated method stub
				synchronized (this) {
					taskid = id;
					TBRecord tbRecord = new TBRecord();
					tbRecord.setId(id);
					tbRecord.setState(3);
					recordService.updateByPrimaryKeySelective(tbRecord);
					send(id);
				}
			}
		});
	}
	//读取数据并放入数据队列中
	private static void send(int id) {
		TBRecord tbRecord = recordService.selectByPrimaryKey(id);
		String path = "D://NEW_HSDTMarket_Platform/upload/"+tbRecord.getNewfilename();//河北环境
		File file = new File(path);
		log.info("当前文件的大小："+file.length());
		log.info("当前消息的ID是："+tbRecord.getMigId());
		if(tbRecord.getMigId()!=null) {
			log.info(mssageService);
			TBMssage tbMssage = mssageService.selectByPrimaryKey(tbRecord.getMigId());
			log.info("当前的消息的数量是："+tbMssage.getMisContent());
			messageContent = tbMssage.getMisContent();
		}else {
			messageContent = "当前并非任何运营消息";
		}
		log.info("当前消息是："+messageContent);
		try {
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
			InputStreamReader in = new InputStreamReader(bis);
			BufferedReader reader = new BufferedReader(in);
			String line = "";
			while((line = reader.readLine())!=null) {
				dataphonequeue.offer(line);
			}
			outQueueForSend(dataphonequeue,tbRecord);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	//任务数据出队列并按照80一组进行发送
	private static void outQueueForSend(BlockingQueue<String> queue,TBRecord tbRecord) {
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
							if (tbRecord.getIsdelblack().equals("是")) {
								list = Remove.blackList(list);//去黑名单
							}
							if(tbRecord.getIsdeldays().equals("是")) {
								RepeatOpera repeatOpera = new RepeatOpera();
								Date date = new Date();
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
								repeatOpera.setDatain(sdf.format(date));
								repeatOpera.setDays(tbRecord.getDeldays());
								list = Remove.threeDayList(list,repeatOpera);//去除三天重复
							}
							if(!list.isEmpty()) {
								Thread thread = new Thread(new HandlerSend(list, messageContent, tbRecord.getId(),1));
								thread.start();
								Thread.currentThread().sleep(3000);
								list.removeAll(list);
							}else {
								log.info("当前发送批次均为黑名单或者限定时间间隔内已经发送！");
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
			TBRecord overrecord = new TBRecord();
			overrecord.setId(taskid);
			overrecord.setState(4);
			overrecord.setEndtime(new Date());
			int result = recordService.updateByPrimaryKeySelective(overrecord);
			Iterator<Integer> it = waitsend.iterator();
			while(it.hasNext()) {
				if(it.next()==taskid) {
					it.remove();
				}
			}
			if (result>0) {
				log.info("发送完成");
			}
		}else {
			log.info("停止");
			for(Integer id:waitsend) {
				TBRecord endrecord = new TBRecord();
				endrecord.setId(id);
				endrecord.setState(7);
				recordService.updateByPrimaryKeySelective(endrecord);
			}
			isEnd = false;
			isStop = false;
			waitsend.clear();
		}
	}
}


