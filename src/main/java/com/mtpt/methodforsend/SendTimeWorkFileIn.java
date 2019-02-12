package com.mtpt.methodforsend;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;

import com.mtpt.alibean.TBRecord;
import com.mtpt.alibean.TBReview;
import com.mtpt.alibean.TBSceneJob;
import com.mtpt.aliservice.impl.TBRecordService;
import com.mtpt.aliservice.impl.TBReviewService;
import com.mtpt.aliservice.impl.TBSceneJobService;
import com.mtpt.bean.TBMssage;
import com.mtpt.config.SpringContextUtil;
import com.mtpt.service.impl.TBDsjDxAllService;
import com.mtpt.service.impl.TBDsjIceAllService;
import com.mtpt.service.impl.TBMssageService;

public class SendTimeWorkFileIn {
	private static Logger log = Logger.getLogger(SendTimeWorkFileIn.class);
	private static TBMssageService mssageService = (TBMssageService) SpringContextUtil.getBean("tbMssageService");
	private static TBRecordService recordService = (TBRecordService) SpringContextUtil.getBean("tbrecord");
	private static TBReviewService reviewService = (TBReviewService) SpringContextUtil.getBean("reservice");
	private static TBSceneJobService sceneJobService = (TBSceneJobService) SpringContextUtil.getBean("tbscenejobservice");
	private static ExecutorService filepool = Executors.newSingleThreadExecutor();
	private static ExecutorService modelpool = Executors.newSingleThreadExecutor();
	private static ExecutorService scenepool = Executors.newSingleThreadExecutor();
	
	/**
	 * 
	 * @param taskid
	 * 文件定时任务
	 * 
	 */
	public static void addFileTaskId(int taskid) {
		Timer timer = new Timer();
		filepool.execute(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				TBRecord tbRecord = recordService.selectByPrimaryKey(taskid);
				timer.schedule(new TimerTask() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						SendFileIn.waitsend.add(taskid);
						TBRecord tbRecord = new TBRecord();
						tbRecord.setState(5);
						tbRecord.setId(taskid);
						recordService.updateByPrimaryKeySelective(tbRecord);
						SendFileIn.addTaskid(taskid);
					}
				}, tbRecord.getWorktime());
			}
		});
	}
	
	/**
	 * 
	 * @param id
	 * 维度定时任务
	 * 
	 */
	public static void addModelTaskId(int id) {
		Timer timer = new Timer();
		modelpool.execute(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				TBReview tbReview = reviewService.selectByPrimaryKey(id);
				timer.schedule(new TimerTask() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						TBReview tbReview = new TBReview();
						tbReview.setId(id);
						tbReview.setState(5);
						reviewService.updateByPrimaryKeySelective(tbReview);
						SendModelIn.addRe_id(id);
					}
				}, tbReview.getWorktime());
			}
		});
	}
	
	/**
	 * 
	 * @param jobid
	 * 添加场景营销任务
	 * 
	 */
	public static void addSceneTaskId(int jobid) {
		Timer timer = new Timer();
		scenepool.execute(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				TBSceneJob tbSceneJob = sceneJobService.selectByPrimaryKey(jobid);
				timer.schedule(new TimerTask() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						TBSceneJob sceneJob = new TBSceneJob();
						sceneJob.setId(jobid);
						sceneJob.setState(5);
						sceneJobService.updateByPrimaryKeySelective(sceneJob);
						SendSceneLabel.setJob_id(jobid);
					}
				}, tbSceneJob.getWorkTime());
			}
		});
	}
}
