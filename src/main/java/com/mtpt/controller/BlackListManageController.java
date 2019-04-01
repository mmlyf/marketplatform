package com.mtpt.controller;



import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mtpt.bean.TBBlackList;
import com.mtpt.bean.page.BlackPage;
import com.mtpt.extend.OtherMethod;
import com.mtpt.service.IBlackListManageService;

@Controller
@RequestMapping("blackmana")
public class BlackListManageController {
	private Logger log = Logger.getLogger(BlackListManageController.class);
	@Resource 
	private IBlackListManageService blackListManageService;
	
	private HttpSession session = null;
	
	/**
	 * 分页查询当前黑名单的数据
	 * @param page
	 * @param response
	 * @param request
	 */
	@RequestMapping(value="/selectall",method = {RequestMethod.POST,RequestMethod.GET})
	 private void selectAllByLimit(BlackPage page,HttpServletResponse response,HttpServletRequest request) {
		response.setContentType("text/html;charset=utf-8");
		session = request.getSession();
		String name = (String) session.getAttribute("realname");
		log.info(name+"查看黑名单列表！");
		JSONObject jsonmap = blackListManageService.selectBlackListDataByPage(page);
		OtherMethod.PrintFlush(response, jsonmap);
	 }
	
	/**
	 * 单条添加黑名单数据
	 * @param tbBlackList
	 * @param response
	 * @param request
	 */
	@RequestMapping(value="/insert",method = {RequestMethod.POST,RequestMethod.GET})
	private void insertBlackPhone(TBBlackList tbBlackList,HttpServletResponse response,HttpServletRequest request) {
		session = request.getSession();
		String name = (String) session.getAttribute("realname");
		log.info(name+"执行插入黑名单的操作！插入的号码是："+tbBlackList.getDn());
		JSONObject json = blackListManageService.insertBlackListDataForPhone(tbBlackList);
		OtherMethod.PrintFlush(response, json);
	}
	
	/**
	 * 根据ID删除黑名单的数据
	 * @param id
	 * @param response
	 * @param request
	 */
	@RequestMapping(value="/delete",method = {RequestMethod.POST,RequestMethod.GET})
	private void deleteBlackPhone(int id,HttpServletResponse response,HttpServletRequest request) {
		session = request.getSession();
		String name = (String) session.getAttribute("realname");
		log.info(name+"执行删除黑名单操作，删除黑名单的ID是："+id);
		JSONObject json = blackListManageService.deleteBlackListDataById(id);
		OtherMethod.PrintFlush(response, json);
	}
	
	/**
	 * 通过文件上传的方式，批量添加黑名单数据
	 * @param file
	 * @param response
	 * @param request
	 */
	@RequestMapping(value="uploadfile",method = {RequestMethod.POST,RequestMethod.GET})
	private void uploadBlackFile(@RequestParam("file") MultipartFile file,
			HttpServletResponse response,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("realname");
		log.info(name+"执行黑名单导入操作。导入的黑名单的文件是："+file.getName());
		JSONObject json = blackListManageService.uploadBlackDataByFile(file);
		OtherMethod.PrintFlush(response, json);
	}
}
