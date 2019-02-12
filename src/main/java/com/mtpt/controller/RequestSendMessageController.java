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
import com.mtpt.methodforsend.SendFileIn;
import com.mtpt.methodforsend.SendModelIn;
import com.mtpt.methodforsend.SendTimeWorkFileIn;
import com.mtpt.service.ITBDsjDxAllService;
import com.mtpt.service.ITBDsjIceAllService;

@Controller
@RequestMapping("/requestsend")
public class RequestSendMessageController {
	private Logger log = Logger.getLogger(RequestSendMessageController.class);
	
	@Resource
	ITBDsjDxAllService dxallservice;
	@Resource
	ITBDsjIceAllService iceallservice;
	@Resource 
	ITBReviewService reservice;
	@Resource
	ITBRecordService rdservice;
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
		log.info(worker+"执行发送\"文件导入\"的任务。发送的任务ID是："+taskid);
		JSONObject json = new JSONObject();
		json.put("code", 1);
		try {
			PrintWriter pWriter = response.getWriter();
			pWriter.write(json.toString());
			pWriter.flush();
			pWriter.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		TBRecord tbrecord = rdservice.selectByPrimaryKey(taskid);
		if(tbrecord.getIstimework().equals("是")) {
			log.debug("当前为定时任务");
			TBRecord timerecord = new TBRecord();
			timerecord.setState(101);
			timerecord.setId(taskid);
			timerecord.setLastwork(worker);
			rdservice.updateByPrimaryKeySelective(timerecord);
			SendTimeWorkFileIn.addFileTaskId(taskid);			
		}else {
			SendFileIn.waitsend.add(taskid);
			TBRecord tbRecord = new TBRecord();
			tbRecord.setState(5);
			tbRecord.setId(taskid);
			tbRecord.setLastwork(worker);
			rdservice.updateByPrimaryKeySelective(tbRecord);
			SendFileIn.addTaskid(taskid);
		}
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
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("realname");
		log.info(name+"执行发送\"维度筛选\"任务。发送的任务ID是："+id);
		JSONObject json = new JSONObject();
		json.put("code", 1);
		try {
			PrintWriter pWriter = response.getWriter();
			pWriter.write(json.toString());
			pWriter.flush();
			pWriter.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		TBReview tbreview = reservice.selectByPrimaryKey(id);
		if(tbreview.getIsitmework().equals("是")) {
			TBReview timereview = new TBReview();
			timereview.setId(id);
			timereview.setState(101);
			reservice.updateByPrimaryKeySelective(timereview);
			SendTimeWorkFileIn.addModelTaskId(id);
		}else {
			SendModelIn.waitsend.add(id);
			TBReview tbReview = new TBReview();
			tbReview.setId(id);
			tbReview.setState(5);
			reservice.updateByPrimaryKeySelective(tbReview);
			SendModelIn.addRe_id(id);
		}
	}
	
	@RequestMapping(value="/stopsendfile",method = {RequestMethod.POST,RequestMethod.GET})
	private void stopMessageByFile(HttpServletResponse response,HttpServletRequest request) {
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("realname");
		log.info(name+"执行暂停\"文件导入\"任务。");
		JSONObject json = new JSONObject();
		if(SendFileIn.waitsend.isEmpty()) {
			json.put("code", 0);
		}else {
			SendFileIn.isStop = true;
				json.put("code", 1);
				for(int id: SendFileIn.waitsend) {
					TBRecord tbRecord = new TBRecord();
					tbRecord.setId(id);
					tbRecord.setState(6);
					rdservice.updateByPrimaryKeySelective(tbRecord);
				}
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
	
	@RequestMapping(value="/stopsendmodel",method = {RequestMethod.POST,RequestMethod.GET})
	private void stopMessageByModel(HttpServletResponse response,HttpServletRequest request) {
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("realname");
		log.info(name+"执行暂停\"维度筛选\"任务。");
		JSONObject json = new JSONObject();
		if(SendModelIn.waitsend.isEmpty()) {
			json.put("code", 0);
		}else {
			SendModelIn.isStop = true;
			json.put("code", 1);
			for(int id: SendModelIn.waitsend) {
				TBReview tbReview = new TBReview();
				tbReview.setId(id);
				tbReview.setState(6);
				reservice.updateByPrimaryKeySelective(tbReview);
			}
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
	
	@RequestMapping(value="/startsendfile",method = {RequestMethod.POST,RequestMethod.GET})
	private void startMessageByFile(HttpServletResponse response,HttpServletRequest request) {
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("realname");
		log.info(name+"执行\"文件导入\"续发任务！");
		SendFileIn.isStop = false;
		for(int id:SendFileIn.waitsend) {
			if(id==SendFileIn.getTaskid()) {
				TBRecord tbRecord = new TBRecord();
				tbRecord.setId(id);
				tbRecord.setState(3);
				rdservice.updateByPrimaryKeySelective(tbRecord);
			}else {
				TBRecord tbRecord = new TBRecord();
				tbRecord.setId(id);
				tbRecord.setState(5);
				rdservice.updateByPrimaryKeySelective(tbRecord);
			}
			try {
				PrintWriter pw = response.getWriter();
				JSONObject json = new JSONObject();
				json.put("code", 1);
				pw.write(json.toString());
				pw.flush();
				pw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@RequestMapping(value="/startsendmodel",method = {RequestMethod.POST,RequestMethod.GET})
	private void startMessageByModel(HttpServletResponse response,HttpServletRequest request) {
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("realname");
		log.info(name+"执行\"维度筛选\"续发任务");
		SendModelIn.isStop = false;
		for(int id:SendModelIn.waitsend) {
			if(id==SendModelIn.getRe_id()) {
				TBReview tbReview = new TBReview();
				tbReview.setId(id);
				tbReview.setState(3);
				reservice.updateByPrimaryKeySelective(tbReview);
			}else {
				TBReview tbReview = new TBReview();
				tbReview.setId(id);
				tbReview.setState(5);
				reservice.updateByPrimaryKeySelective(tbReview);
			}
			try {
				PrintWriter pw = response.getWriter();
				JSONObject json = new JSONObject();
				json.put("code", 1);
				pw.write(json.toString());
				pw.flush();
				pw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@RequestMapping(value="/endsendfile",method = {RequestMethod.POST,RequestMethod.GET})
	private void endMessageByFile(HttpServletResponse response,HttpServletRequest request) {
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("realname");
		log.info(name+"执行结束\"文件导入\"中的所有任务！");
		log.debug("当前点击了结束任务");
		JSONObject json = new JSONObject();
		if(SendFileIn.waitsend.isEmpty()) {
			log.info("当前无群发任务");
			json.put("code", 0);
		}else {
			SendFileIn.isEnd = true;
			json.put("code", 1);
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
	
	@RequestMapping(value="/endsendmodel",method = {RequestMethod.POST,RequestMethod.GET})
	private void endMessageByModel(HttpServletResponse response,HttpServletRequest request) {
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("realname");
		log.info(name+"执行结束\"维度筛选\"的所有任务");
		JSONObject json = new JSONObject();
		if(SendModelIn.waitsend.isEmpty()) {
			json.put("code", 0);
			log.info("当前无群发任务");
		}else {
			SendModelIn.isEnd = true;
			json.put("code", 1);
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
	
	@RequestMapping(value="/statefile",method = {RequestMethod.POST,RequestMethod.GET})
	private void stateBySendMessageFile(HttpServletResponse response) throws IOException {
		JSONObject json = new JSONObject();
		if(SendFileIn.waitsend.isEmpty()) {
			json.put("state", 0);//当前暂无发送
		}else {
			if (!SendFileIn.isStop) {
				json.put("state", 1);//显示暂停
			}else {
				json.put("state", 2);//显示续发
			}
		}
		PrintWriter pw = response.getWriter();
		pw.write(json.toString());
		pw.flush();
		pw.close();
	}
	
	@RequestMapping(value="/statemodel",method = {RequestMethod.POST,RequestMethod.GET})
	private void stateBySendMessageModel(HttpServletResponse response) throws IOException {
		JSONObject json = new JSONObject();
		if(SendModelIn.waitsend.isEmpty()) {
			json.put("state", 0);//当前暂无发送
		}else {
			if (!SendModelIn.isStop) {
				json.put("state", 1);//显示暂停
			}else {
				json.put("state", 2);//显示续发
			}
		}
		PrintWriter pw = response.getWriter();
		pw.write(json.toString());
		pw.flush();
		pw.close();
	}
}
