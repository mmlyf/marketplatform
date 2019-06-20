package com.mtpt.service.impl;

import org.springframework.stereotype.Service;

import com.mtpt.service.ITimeTaskService;
import com.mtpt.timework.DownloadTimeTask;
import com.mtpt.timework.StartWorkTimeTask;
import com.mtpt.timework.StopWorkTimeTask;

@Service("timetask")
public class TimeTaskService implements ITimeTaskService{

	@Override
	public void downloadFtpTimeTask() {
		// TODO Auto-generated method stub
		DownloadTimeTask downloadTimeTask = new DownloadTimeTask();
		downloadTimeTask.downloadFtpFile();
	}

	@Override
	public void startSendTask() {
		// TODO Auto-generated method stub
		StartWorkTimeTask startWorkTimeTask = new StartWorkTimeTask();
		startWorkTimeTask.startSendMessage();
	}

	@Override
	public void stopSendTask() {
		// TODO Auto-generated method stub
		StopWorkTimeTask stopWorkTimeTask = new StopWorkTimeTask();
		stopWorkTimeTask.stopSendMessage();
	}

}
