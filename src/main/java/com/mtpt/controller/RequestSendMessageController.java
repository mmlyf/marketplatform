package com.mtpt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Timer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mtpt.alibean.TBRecord;
import com.mtpt.alibean.TBReview;
import com.mtpt.aliservice.ITBRecordService;
import com.mtpt.aliservice.ITBReviewService;
import com.mtpt.extend.OtherMethod;
import com.mtpt.methodforsend.SendFileIn;
import com.mtpt.methodforsend.SendModelIn;
import com.mtpt.methodforsend.SendTimeWorkFileIn;
import com.mtpt.service.IRequestSendMessageService;

@Controller
@RequestMapping("/requestsend")
public class RequestSendMessageController {
	private Logger log = Logger.getLogger(RequestSendMessageController.class);

	@Resource 
	private IRequestSendMessageService requestSendMessageService;
	/**
	 * 
	 * @param taskid
	 * @param response
	 * 发送我的提交中文件导入的任务
	 * 
	 * 
	 */
	@RequestMapping(value="/sendfile",method = {RequestMethod.POST,RequestMethod.GET})
	private void sendMessageByFile(Integer taskid,String worker,HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		log.info(worker+"执行发送\"文件导入\"的任务。发送的任务ID是："+taskid);
		JSONObject json = new JSONObject();
		json.put("code", 1);
		OtherMethod.PrintFlush(response, json);
		requestSendMessageService.sendMessageByFile(taskid, worker);
	}
	
	/**
	 * 
	 * @param id
	 * @param response
	 * 获取发送维度数据的请求
	 * 获取维度数据的ID
	 * 
	 */
	@RequestMapping(value="sendmodel",method = {RequestMethod.POST,RequestMethod.GET})
	private void sendMessageByModel(Integer id,HttpServletResponse response,HttpServletRequest request) {
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("realname");
		log.info(name+"执行发送\"维度筛选\"任务。发送的任务ID是："+id);
		JSONObject json = new JSONObject();
		json.put("code", 1);
		OtherMethod.PrintFlush(response, json);	
		requestSendMessageService.sendMessageByModel(id);
	}

	/**
	 * 暂停文件上传
	 * @param response
	 * @param request
	 */
	@RequestMapping(value="/stopsendfile",method = {RequestMethod.POST,RequestMethod.GET})
	private void stopMessageByFile(HttpServletResponse response,HttpServletRequest request) {
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("realname");
		log.info(name+"执行暂停\"文件导入\"任务。");
		JSONObject json = requestSendMessageService.stopMessageByFile();
		OtherMethod.PrintFlush(response, json);

	}
	
	/**
	 * 暂停模型发送
	 * @param response
	 * @param request
	 */
	@RequestMapping(value="/stopsendmodel",method = {RequestMethod.POST,RequestMethod.GET})
	private void stopMessageByModel(HttpServletResponse response,HttpServletRequest request) {
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("realname");
		log.info(name+"执行暂停\"维度筛选\"任务。");
		JSONObject json = requestSendMessageService.stopMessageByModel();
		OtherMethod.PrintFlush(response, json);
	}
	
	/**
	 * 重新发送文件信息
	 * @param response
	 * @param request
	 */
	@RequestMapping(value="/startsendfile",method = {RequestMethod.POST,RequestMethod.GET})
	private void startMessageByFile(HttpServletResponse response,HttpServletRequest request) {
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("realname");
		log.info(name+"执行\"文件导入\"续发任务！");
		requestSendMessageService.startMessageByFile();
		JSONObject json = new JSONObject();
		json.put("code", 1);
		OtherMethod.PrintFlush(response, json);
	}
	
	/**
	 * 重新发送维度筛选短信
	 * @param response
	 * @param request
	 */
	@RequestMapping(value="/startsendmodel",method = {RequestMethod.POST,RequestMethod.GET})
	private void startMessageByModel(HttpServletResponse response,HttpServletRequest request) {
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("realname");
		log.info(name+"执行\"维度筛选\"续发任务");
		requestSendMessageService.startMessageByModel();
		JSONObject json = new JSONObject();
		json.put("code", 1);
		OtherMethod.PrintFlush(response, json);
	}
	
	/**
	 * 停止文件上传的发送任务
	 * @param response
	 * @param request
	 */
	@RequestMapping(value="/endsendfile",method = {RequestMethod.POST,RequestMethod.GET})
	private void endMessageByFile(HttpServletResponse response,HttpServletRequest request) {
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("realname");
		log.info(name+"执行结束\"文件导入\"中的所有任务！");
		JSONObject json = requestSendMessageService.endMessageByFile();
		OtherMethod.PrintFlush(response, json);
		
	}
	
	/**
	 * 停止发送维度筛选任务
	 * @param response
	 * @param request
	 */
	@RequestMapping(value="/endsendmodel",method = {RequestMethod.POST,RequestMethod.GET})
	private void endMessageByModel(HttpServletResponse response,HttpServletRequest request) {
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("realname");
		log.info(name+"执行结束\"维度筛选\"的所有任务");
		JSONObject json = requestSendMessageService.endMessageByModel();
		OtherMethod.PrintFlush(response, json);
	}
	
	@RequestMapping(value="/statefile",method = {RequestMethod.POST,RequestMethod.GET})
	private void stateBySendMessageFile(HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		JSONObject json = requestSendMessageService.stateBySendMessageFile();
		OtherMethod.PrintFlush(response, json);
	}
	
	@RequestMapping(value="/statemodel",method = {RequestMethod.POST,RequestMethod.GET})
	private void stateBySendMessageModel(HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		JSONObject json = requestSendMessageService.stateBySendMessageModel();
		OtherMethod.PrintFlush(response, json);
	}
}
