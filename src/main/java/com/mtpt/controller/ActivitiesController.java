package com.mtpt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.json.Json;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mtpt.alibean.page.TBRecordPage;
import com.mtpt.bean.ActivityList;
import com.mtpt.bean.TBHfcs;
import com.mtpt.bean.page.ActivityPage;
import com.mtpt.extend.OtherMethod;
import com.mtpt.service.IActivitiesService;

import javafx.css.PseudoClass;
import sun.util.logging.resources.logging_fr;

@Controller
@RequestMapping("/reqact")
public class ActivitiesController {
	
	@Resource
	private IActivitiesService activityService;
	
	
	
	/**
	 * 查找活动未结束的活动并分页并将信息返回值前端
	 * @param page
	 * @param response
	 */
	@RequestMapping(value="/selunendact",method = {RequestMethod.POST,RequestMethod.GET})
	private void selectUnEndActByActivityPage(ActivityPage page,HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		JSONObject jsonmap = activityService.selectUnEndActivityByPage(page);
		OtherMethod.PrintFlush(response, jsonmap);
	}
	/**
	 * 查询已经结束的活动的数据并展示值前端
	 * @param page
	 * @param response
	 */
	@RequestMapping(value="/selendact",method = {RequestMethod.POST,RequestMethod.GET})
	private void selectEndActByActivityPage(ActivityPage page,HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		JSONObject jsonmap = activityService.selectEndActivityByPage(page);
		OtherMethod.PrintFlush(response, jsonmap);
	}
	
	
	/**
	 * 添加新活动数据
	 * @param actlist
	 * @param response
	 * @param request
	 */
	@RequestMapping(value="/addact",method = {RequestMethod.GET,RequestMethod.POST})
	private void addActivity(ActivityList actlist,HttpServletResponse response,HttpServletRequest request) {
		response.setContentType("text/html;charset=utf-8");
		JSONObject json = activityService.insertActivityInfo(actlist);
		OtherMethod.PrintFlush(response, json);
	}
	
	/**
	 * 更新数据
	 * @param activityList
	 * @param response
	 */
	@RequestMapping(value="/updateActInfo",method= {RequestMethod.GET,RequestMethod.POST})
	private void updateActivityInfoById(ActivityList activityList,HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		JSONObject json = activityService.updateActivityInfoById(activityList);
		OtherMethod.PrintFlush(response, json);
 	}
}
