package com.mtpt.alicontroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mtpt.alibean.TBSceneData;
import com.mtpt.alibean.TBSceneJob;
import com.mtpt.alibean.page.SceneJobPage;
import com.mtpt.aliservice.ISceneMarketJobService;
import com.mtpt.extend.OtherMethod;

@Controller
@RequestMapping("/scenejob")
public class SceneMarketJobController {
	private Logger log = Logger.getLogger(SceneMarketJobController.class);

	@Resource
	private ISceneMarketJobService sceneMarketJobService;
	private SimpleDateFormat sdf = null;
	
	/**
	 * 
	 * @param tbSceneJob
	 * @param request
	 * @param response
	 * 添加场景营销数据
	 * 
	 */
	@RequestMapping(value="/insertscenejob",method = {RequestMethod.GET,RequestMethod.POST})
	private void insertSceneMarketJob(TBSceneJob tbSceneJob,HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		JSONObject json = sceneMarketJobService.insertSceneMarketJob(tbSceneJob);
		OtherMethod.PrintFlush(response, json);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * 查询获取场景营销任务的数据。并可以根据条件进行查询
	 * @throws UnsupportedEncodingException 
	 * 
	 */
	@RequestMapping(value="/selectscenejob",method = {RequestMethod.GET,RequestMethod.POST})
	private void selectSceneJobData(SceneJobPage page,HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("realname");
		if (page.getKeyword()!=null&&!page.getKeyword().equals("")) {
			String decodekeyword = URLDecoder.decode(page.getKeyword(),"utf-8");
			log.debug("keyword的值是："+decodekeyword);
			page.setKeyword(decodekeyword);
		}
		if (page.getKeyid()!=null&&!page.getKeyid().equals("")) {
			String decodekeyid = URLDecoder.decode(page.getKeyid(),"utf-8");
			log.debug("keyid的值是："+decodekeyid);
			page.setKeyid(decodekeyid);
		}
		JSONObject jsonmap = sceneMarketJobService.selectSceneJobData(page);
		OtherMethod.PrintFlush(response, jsonmap);
	}
	
	/**
	 * 
	 * @param tbSceneJob
	 * @param request
	 * @param response
	 * 用于更新场景营销任务的状态请求
	 * 传入的数据有任务ID和状态
	 * 
	 */
	@RequestMapping(value="/updatescenejob",method = {RequestMethod.GET,RequestMethod.POST})
	private void updateSceneJobForState(TBSceneJob tbSceneJob,HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		log.debug("当前提交的状态是："+tbSceneJob.getState());
		JSONObject json = sceneMarketJobService.updateSceneJobForState(tbSceneJob);
		OtherMethod.PrintFlush(response, json);
	}
	
	/**
	 * 
	 * @param jobid
	 * @param request
	 * @param response
	 * 发送标签数据的任务
	 * 
	 */
	@RequestMapping(value="/sendscenejob",method= {RequestMethod.GET,RequestMethod.POST})
	private void sendMessageSceneJob(Integer jobid,String worker,HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		log.info(worker+"执行\"标签任务\"发送操作");
		JSONObject json = new JSONObject();
		json.put("code", 0);
		OtherMethod.PrintFlush(response, json);
		
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * 暂停标签数据下发的短信任务
	 * 
	 */
	@RequestMapping(value="/stopsendjob",method= {RequestMethod.GET,RequestMethod.POST})
	private void stopSendSceneJob(HttpServletRequest request,HttpServletResponse response) {
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("realname");//获取操作用户的真是姓名
		log.info(name+"执行暂停\"标签数据发送\"任务。");
		JSONObject json = sceneMarketJobService.stopSendSceneJob();
		OtherMethod.PrintFlush(response, json);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * 继续发送标签任务
	 * 
	 */
	@RequestMapping(value="/startsendjob",method= {RequestMethod.GET,RequestMethod.POST})
	private void startSendSceneJob(HttpServletRequest request,HttpServletResponse response) {
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("realname");
		log.info(name+"执行\"标签数据下发的\"续发任务！");
		sceneMarketJobService.startSendSceneJob();
		JSONObject json = new JSONObject();
		json.put("code", 0);
		OtherMethod.PrintFlush(response, json);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * 停止标签所有的发送任务
	 * 
	 */
	@RequestMapping(value="/endsendjob",method= {RequestMethod.GET,RequestMethod.POST})
	private void EndSendSceneJob(HttpServletRequest request,HttpServletResponse response) {
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("realname");
		log.info(name+"执行停止所有\"标签数据\"下发任务");
		JSONObject json = sceneMarketJobService.EndSendSceneJob();
		OtherMethod.PrintFlush(response, json);
	}
	
	
	/**
	 * 
	 * @param jobid
	 * @param request
	 * @param response
	 * 删除可删除的标签任务
	 * 
	 */
	@RequestMapping(value="deletescenejob",method= {RequestMethod.GET,RequestMethod.POST})
	private void deleteSendSceneJob(Integer jobid,HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		JSONObject json = sceneMarketJobService.deleteSendSceneJob(jobid);
		OtherMethod.PrintFlush(response, json);
	}
	
	
	/**
	 * 
	 * @param tbSceneJob
	 * @param request
	 * @param response
	 * 标签数据导出数据
	 * 
	 */
	@RequestMapping(value="/outputjob",method = {RequestMethod.GET,RequestMethod.POST})
	private void outputSceneJobData(TBSceneJob tbSceneJob,HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		JSONObject json = sceneMarketJobService.outputSceneJobData(tbSceneJob);
		OtherMethod.PrintFlush(response, json);
	}
}
