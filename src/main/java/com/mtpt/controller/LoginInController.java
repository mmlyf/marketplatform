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
import com.mtpt.service.ITBPermissionService;
import com.mtpt.service.ITBSuserService;
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
	static {
		PropertyConfigurator.configure(Thread.currentThread().getContextClassLoader().getResource("log.properties").getPath());
//		PropertyConfigurator.configure("D://NEW_HSDTMarket_Platform/config/log.properties");//河北
	}
	@Resource
	ITBSuserService service;
	@Resource
	ITBPermissionService permissionService;
	private HttpSession session = null;
	
	@RequestMapping(value="/byIdPaw",method = {RequestMethod.POST,RequestMethod.GET})
	private void loginInByIdPaw(String username,String password,HttpServletRequest request,HttpServletResponse response) {
		
		HashMap<String, String> map = new HashMap<String, String>();
		if (username ==null&&password==null) {
			log.debug("用户名或者密码为空！");
		}else {
			map.put("username", username);
			password = MD5.encrypt(password);//加密输入的密码并传给后台比较
			System.out.println(password);
			map.put("password",password);
			TBSuser tbSuser = service.selectByNamePaw(map);
			try {
				PrintWriter pw = response.getWriter();
				JSONObject json = new JSONObject();
				if (tbSuser!=null) {
					log.info(username+"登录系统");
					json.put("code", 200);
					session = request.getSession();
					session.setAttribute("uid", tbSuser.getId());
					session.setAttribute("username", tbSuser.getUsername());
					session.setAttribute("realname", tbSuser.getRealname());
					session.setAttribute("permision", tbSuser.getPermission());
				}else {
					log.info(username+"登录系统失败！！");
					json.put("code", 404);
				}
				pw.write(json.toString());
				pw.flush();
				pw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
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
		List<TBSuser> listsuser = service.selectSuserAllData();
		session = request.getSession();
		String permission = (String) session.getAttribute("permision");
		JSONObject jsonmap = new JSONObject();
		List<JSONObject> jsonlist = new ArrayList<>();
		for(TBSuser tbSuser :listsuser) {
			int per;
			try {
				per = Integer.parseInt(permission);
			}catch (NumberFormatException e) {
				// TODO: handle exception
				per = 1001;
			}
			System.out.println("per的值是："+per);
			if (per!=1001&&per==11) {
				JSONObject map = new JSONObject();
				map.put("id", tbSuser.getId());
				map.put("loginname", tbSuser.getUsername());
				map.put("realname", tbSuser.getRealname());
				String[] permissions = tbSuser.getPermission().split(",");
				StringBuilder perstr = new StringBuilder();
				for(int i = 0 ;i<permissions.length;i++) {
					Integer id = Integer.parseInt(permissions[i]);
					TBPermission tbPermission = permissionService.selectByPermissionId(id);
					if (perstr.length()>0) {
						perstr.append(",");
					}
					perstr.append(tbPermission.getPerDes());
				}
				map.put("permission", perstr.toString());
				jsonlist.add(map);
			}else {
				if(tbSuser.getPermission().indexOf("1")!=-1) {
					
				}else {
					JSONObject map = new JSONObject();
					map.put("id", tbSuser.getId());
					map.put("loginname", tbSuser.getUsername());
					map.put("realname", tbSuser.getRealname());
					String[] permissions = tbSuser.getPermission().split(",");
					StringBuilder perstr = new StringBuilder();
					for(int i = 0 ;i<permissions.length;i++) {
						Integer id = Integer.parseInt(permissions[i]);
						TBPermission tbPermission = permissionService.selectByPermissionId(id);
						if (perstr.length()>0) {
							perstr.append(",");
						}
						log.debug("当前权限有："+tbPermission.getPerDes());
						perstr.append(tbPermission.getPerDes());
					}
					map.put("permission", perstr.toString());
					jsonlist.add(map);	
				}
			}
		}
		jsonmap.put("code", 0);
		jsonmap.put("msg", "");
		jsonmap.put("data", jsonlist);
		jsonmap.put("count", jsonlist.size());
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
		String mdpassword = MD5.encrypt(tbSuser.getPassword());//加密
		tbSuser.setPassword(mdpassword);
		int result = service.insertSelective(tbSuser);
		JSONObject json = new JSONObject();
		if (result>0) {
			json.put("code", 0);
		}else {
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
		String mdpassword = MD5.encrypt(tbSuser.getPassword());//加密
		tbSuser.setPassword(mdpassword);
		int result = service.updateByPrimaryKeySelective(tbSuser);
		JSONObject json = new JSONObject();
		if(result>0) {
			json.put("code", 0);
		}else {
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
		session = request.getSession();
		String name = (String) session.getAttribute("realname");
		TBSuser tbSuser = service.selectByPrimaryKey(id);
		log.info(name+"管理员删除了平台用户"+tbSuser.getUsername()+"的登录权限");
		int result = service.deleteByPrimaryKey(id);
		JSONObject json = new JSONObject();
		if(result>0) {
			json.put("code", 0);
		}else {
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
		TBSuser tbSuser = service.selectByPrimaryKey(id);
		session = request.getSession();
		String name = (String) session.getAttribute("realname");
		log.info(name+"管理员查看了"+tbSuser.getUsername()+"用户的基本信息");
		JSONObject json = new JSONObject();
		json.put("loginname", tbSuser.getUsername());
		json.put("password", tbSuser.getPassword());
		json.put("realname", tbSuser.getUsername());
		json.put("permission", tbSuser.getPermission());
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
	
	
	/**
	 * 
	 * @param response
	 * 查询权限表，获取对应的权限和ID并回传至前端界面
	 * 
	 */
	@RequestMapping(value="/selectpermission",method = {RequestMethod.GET,RequestMethod.POST})
	private void selectAllPermission(HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		List<TBPermission> list = permissionService.selectPermissionData();
		JSONObject json = new JSONObject();
		List<JSONObject> jsonlist = new ArrayList<>();
		for(TBPermission tbPermission:list) {
			JSONObject map = new JSONObject();
			map.put("per_id", tbPermission.getPerId());
			map.put("per_name", tbPermission.getPerDes());
			jsonlist.add(map);
		}
		json.put("data", jsonlist);
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

