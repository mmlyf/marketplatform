package com.mtpt.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mtpt.service.ITimeTaskService;

@Controller
@RequestMapping("timetask")
public class TimeTaskRunController {
	@Resource
	private ITimeTaskService timeTaskService;
	
	@RequestMapping(value="/download",method= {RequestMethod.GET,RequestMethod.POST})
	private void downloadFtpMethod() {
		timeTaskService.downloadFtpTimeTask();
	}
	
	@RequestMapping(value="/startwork",method= {RequestMethod.GET,RequestMethod.POST})
	private void startSendWork() {
		timeTaskService.startSendTask();
	}
	
	@RequestMapping(value="/stopwork",method= {RequestMethod.GET,RequestMethod.POST})
	private void stopSendWork() {
		timeTaskService.stopSendTask();
	}
}
