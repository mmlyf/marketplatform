package com.mtpt.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.mtpt.service.ITBMssageService;
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
	ITBMssageService msgService;
	private HttpSession session = null;
	
	
	@RequestMapping(value="/select",method = {RequestMethod.POST,RequestMethod.GET})
	private void selectMessage(TBMessagePage page,HttpServletResponse response,HttpServletRequest request) {
		session = request.getSession();
		String name = (String) session.getAttribute("realname");
		log.info(name+"访问运营消息列表！");
		int totals = msgService.selectByCount(page);
		page.setTotalRecord(totals);
		List<TBMssage> list = msgService.selectByCustom(page);
		JSONObject jsonmap = new JSONObject();
		List<JSONObject> jsonlist = new ArrayList<JSONObject>();
		response.setContentType("text/html;charset=utf-8");
		for(TBMssage tbMssage:list) {
			JSONObject map = new JSONObject();
			map.put("id", tbMssage.getMisId());
			map.put("mistitle", tbMssage.getMisTitle());
			map.put("miscontent", tbMssage.getMisContent());
			jsonlist.add(map);
		}
		jsonmap.put("code", 0);
		jsonmap.put("count", totals);
		jsonmap.put("msg", "");
		jsonmap.put("data", jsonlist);
		try {
			PrintWriter pw = response.getWriter();
			pw.write(jsonmap.toString());
			pw.flush();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/updateid",method = {RequestMethod.POST,RequestMethod.GET})
	private void updateMessageByID(TBMssage tbMssage,HttpServletResponse response,HttpServletRequest request) {
		session = request.getSession();
		String name = (String) session.getAttribute("realname");
		log.info(name+"修改运营消息内容操作");
		JSONObject json = new JSONObject();		
		int result = msgService.updateByPrimaryKeySelective(tbMssage);
		if (result>0) {
			json.put("code", 1);
		}else {
			json.put("code", 0);
		}
		try {
			PrintWriter pw = response.getWriter();
			pw.write(json.toString());
			pw.flush();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/delete",method = {RequestMethod.POST,RequestMethod.GET})
	private void deleteMessageByID(Integer misId,HttpServletResponse response,HttpServletRequest request) {
		session = request.getSession();
		String name = (String) session.getAttribute("realname");
		log.info(name+"删除ID为"+misId+"的运营内容");
		log.debug("msgId的值"+misId);
		int result = msgService.deleteByPrimaryKey(misId);
		JSONObject json = new JSONObject();
		if (result>0) {
			json.put("code", 1);
		}else {
			json.put("code", 0);
		}
		try {
			PrintWriter pw = response.getWriter();
			pw.write(json.toString());
			pw.flush();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@RequestMapping(value="/insert",method = {RequestMethod.POST,RequestMethod.GET})
	private void insertMessage(TBMssage tbMssage,HttpServletResponse response	,HttpServletRequest request) {
		session = request.getSession();
		String name = (String) session.getAttribute("realname");
		log.info(name+"添加标题为："+tbMssage.getMisTitle()+"营销语");
		int result = msgService.insertSelective(tbMssage);
		JSONObject json = new JSONObject();
		if(result>0) {
			json.put("code", 1);
		}else {
			json.put("code", 0);
		}
		try {
			PrintWriter pw = response.getWriter();
			pw.write(json.toString());
			pw.flush();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
