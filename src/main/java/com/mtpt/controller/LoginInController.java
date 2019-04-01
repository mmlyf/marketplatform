package com.mtpt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.reflection.ArrayUtil;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mtpt.bean.TBPermission;
import com.mtpt.bean.TBSuser;
import com.mtpt.bean.TBUsers;
import com.mtpt.extend.MD5;
import com.mtpt.extend.OtherMethod;
import com.mtpt.service.ILoginInAndInsertInUserService;
import com.mysql.cj.xdevapi.JsonLiteral;
/**
 * 
 * @author lvgordon
 * 登录的请求数据
 *
 */
@Controller
@RequestMapping("/login")
public class LoginInController {
	private Logger log = Logger.getLogger(LoginInController.class);

	@Resource
	private ILoginInAndInsertInUserService loginInAndInsertInUserService;
	private HttpSession session = null;
	
	@RequestMapping(value="/byIdPaw",method = {RequestMethod.POST,RequestMethod.GET})
	private void loginInByIdPaw(String username,String password,HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		JSONObject json = loginInAndInsertInUserService.loginInByIdPaw(username, password, request);
		OtherMethod.PrintFlush(response, json);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * 登出系统操作
	 * 
	 */
	@RequestMapping(value="logout",method = {RequestMethod.POST,RequestMethod.GET})
	private void logOutPlatform(HttpServletRequest request,HttpServletResponse response) {
		session = request.getSession();
		log.info(session.getAttribute("realname")+"登出系统");
		session.invalidate();
		try {
			response.sendRedirect("../jsp/login.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * 查询平台用户
	 * 
	 */
	@RequestMapping(value="/selectsuser",method = {RequestMethod.GET,RequestMethod.POST})
	private void selectSuserAllData(HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		session = request.getSession();
		String permission = (String) session.getAttribute("permision");
		JSONObject jsonmap = loginInAndInsertInUserService.selectSuserAllData(permission);
		OtherMethod.PrintFlush(response, jsonmap);
	}
	
	/**
	 * 
	 * @param tbSuser
	 * @param request
	 * @param response
	 * 添加用户信息
	 * 
	 */
	@RequestMapping(value="/insertsuser",method = {RequestMethod.GET,RequestMethod.POST})
	private void insertSuserData(TBSuser tbSuser,HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		session = request.getSession();
		String userrealname = (String) session.getAttribute("realname");
		log.info(userrealname+"管理员添加登录用户为"+tbSuser.getUsername());
		JSONObject json = loginInAndInsertInUserService.insertSuserData(tbSuser);
		OtherMethod.PrintFlush(response, json);
	}
	
	/**
	 * 
	 * @param tbSuser
	 * @param request
	 * @param response
	 * 更新用户信息
	 * 
	 */
	@RequestMapping(value="/updatesuser",method = {RequestMethod.GET,RequestMethod.POST})
	private void updateSuserDataById(TBSuser tbSuser,HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		session = request.getSession();
		String name = (String) session.getAttribute("realname");
		log.info(name+"管理员更新平台用户"+tbSuser.getUsername()+"的基本信息");
		JSONObject json = loginInAndInsertInUserService.updateSuserDataById(tbSuser);
		OtherMethod.PrintFlush(response, json);
	}
	
	/**
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * 删除平台用户
	 * 
	 */
	@RequestMapping(value="/deleteuser",method = {RequestMethod.POST,RequestMethod.GET})
	private void deleteSuserDataById(Integer id,HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		JSONObject json = loginInAndInsertInUserService.deleteSuserDataById(id,request);
		OtherMethod.PrintFlush(response, json);
	}
	
	/**
	 * 
	 * @param id
	 * @param response
	 * @param request
	 * 根据用户在数据表的ID查询用户的信息并将信息回传至前端页面
	 * 
	 */
	@RequestMapping(value="/selectuserbyid",method = {RequestMethod.POST,RequestMethod.GET})
	private void selectSuserDataById(Integer id,HttpServletResponse response,HttpServletRequest request) {
		response.setContentType("text/html;charset=utf-8");
		JSONObject json = loginInAndInsertInUserService.selectSuserDataById(id, request);
		OtherMethod.PrintFlush(response, json);
	}
	
	
	/**
	 * 
	 * @param response
	 * 查询权限表，获取对应的权限和ID并回传至前端界面
	 * 
	 */
	@RequestMapping(value="/selectpermission",method = {RequestMethod.GET,RequestMethod.POST})
	private void selectAllPermission(HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		JSONObject json = loginInAndInsertInUserService.selectAllPermission();
		OtherMethod.PrintFlush(response, json);
	}
}

