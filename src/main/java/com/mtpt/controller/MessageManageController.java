package com.mtpt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mtpt.bean.TBMssage;
import com.mtpt.bean.page.TBMessagePage;
import com.mtpt.extend.OtherMethod;
import com.mtpt.service.IMessageManageService;
import com.mysql.cj.Session;
import com.sun.media.jfxmedia.control.VideoDataBuffer;

import sun.util.logging.resources.logging;
/**
 * 
 * @author lvgordon
 * 消息管理的控制器
 *
 */
@Controller
@RequestMapping("/msgmana")
public class MessageManageController {
	private Logger log = Logger.getLogger(MessageManageController.class);
	
	@Resource 
	private IMessageManageService messageManageService;
	private HttpSession session = null;
	
	/**
	 * 分页查询当前的消息列表
	 * @param page
	 * @param response
	 * @param request
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/select",method = {RequestMethod.POST,RequestMethod.GET})
	private void selectMessage(TBMessagePage page,HttpServletResponse response,HttpServletRequest request) throws UnsupportedEncodingException {
		response.setContentType("text/html;charset=utf-8");
		session = request.getSession();
		String name = (String) session.getAttribute("realname");
		log.info(name+"访问运营消息列表！");
		if (page.getKeyword()!=null&&!page.getKeyword().equals("")) {
			String decodekeyword = URLDecoder.decode(page.getKeyword(),"utf-8");
			page.setKeyword(decodekeyword);
		}
		JSONObject jsonmap = messageManageService.selectAllMessageDataByPage(page);
		OtherMethod.PrintFlush(response, jsonmap);
	}
	
	/**
	 * 更新消息列表中指定消息的内容
	 * @param tbMssage
	 * @param response
	 * @param request
	 */
	@RequestMapping(value="/updateid",method = {RequestMethod.POST,RequestMethod.GET})
	private void updateMessageByID(TBMssage tbMssage,HttpServletResponse response,HttpServletRequest request) {
		session = request.getSession();
		String name = (String) session.getAttribute("realname");
		log.info(name+"修改运营消息内容操作");
		JSONObject json = messageManageService.updateMessageData(tbMssage);
		OtherMethod.PrintFlush(response, json);
	}
	
	/**
	 * 删除消息列表中指定的消息
	 * @param misId
	 * @param response
	 * @param request
	 */
	@RequestMapping(value="/delete",method = {RequestMethod.POST,RequestMethod.GET})
	private void deleteMessageByID(Integer misId,HttpServletResponse response,HttpServletRequest request) {
		session = request.getSession();
		String name = (String) session.getAttribute("realname");
		log.info(name+"删除ID为"+misId+"的运营内容");
		log.debug("msgId的值"+misId);
		JSONObject json = messageManageService.deleteMessageData(misId);		
		OtherMethod.PrintFlush(response, json);
		
	}
	
	/**
	 * 添加消息
	 * @param tbMssage
	 * @param response
	 * @param request
	 */
	@RequestMapping(value="/insert",method = {RequestMethod.POST,RequestMethod.GET})
	private void insertMessage(TBMssage tbMssage,HttpServletResponse response	,HttpServletRequest request) {
		session = request.getSession();
		String name = (String) session.getAttribute("realname");
		log.info(name+"添加标题为："+tbMssage.getMisTitle()+"营销语");
		JSONObject json = messageManageService.insertMessageData(tbMssage);
		OtherMethod.PrintFlush(response, json);
	}
}
