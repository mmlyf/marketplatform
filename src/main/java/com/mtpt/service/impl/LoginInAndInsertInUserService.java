package com.mtpt.service.impl;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtpt.bean.TBPermission;
import com.mtpt.bean.TBSuser;
import com.mtpt.bean.TBUsers;
import com.mtpt.dao.TBPermissionMapper;
import com.mtpt.dao.TBSuserMapper;
import com.mtpt.extend.MD5;
import com.mtpt.service.ILoginInAndInsertInUserService;

@Service("logininAndInsertInUserService")
public class LoginInAndInsertInUserService implements ILoginInAndInsertInUserService{

	private Logger log = Logger.getLogger(LoginInAndInsertInUserService.class);
	@Autowired 
	private TBSuserMapper suserMapper;
	@Autowired 
	private TBPermissionMapper permissionMpper;

	private HttpSession session = null;
	
	/**
	 * 根据用户的ID和密码进行验证账号的合法性
	 */
	@Override
	public JSONObject loginInByIdPaw(String username, String password,HttpServletRequest request) {
		// TODO Auto-generated method stub
		HashMap<String, String> map = new HashMap<String, String>();
		JSONObject json = new JSONObject();
		if (username ==null&&password==null) {
			log.debug("用户名或者密码为空！");
		}else {
			map.put("username", username);
			password = MD5.encrypt(password);//加密输入的密码并传给后台比较
			System.out.println(password);
			map.put("password",password);
			TBSuser tbSuser = suserMapper.selectByNamePaw(map);
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
		}
		return json;
	}

	/**
	 * 根据用户的权限查看平台的用户（具有管理员权限才可查看）
	 */
	@Override
	public JSONObject selectSuserAllData(String permission) {
		// TODO Auto-generated method stub
		List<TBSuser> listsuser = suserMapper.selectSuserAllData();
		JSONObject jsonmap = new JSONObject();
		List<JSONObject> jsonlist = new ArrayList<>();
		for(TBSuser tbSuser :listsuser) {
			int per;
			try {
				per = Integer.parseInt(permission);
			}catch (Exception e) {
				// TODO: handle exception
				per = 1001;
			}
			if (per!=1001&&per==11) {
				JSONObject map = new JSONObject();
				map.put("id", tbSuser.getId());
				map.put("loginname", tbSuser.getUsername());
				map.put("realname", tbSuser.getRealname());
				String[] permissions = tbSuser.getPermission().split(",");
				StringBuilder perstr = new StringBuilder();
				for(int i = 0 ;i<permissions.length;i++) {
					Integer id = Integer.parseInt(permissions[i]);
					TBPermission tbPermission = permissionMpper.selectByPermissionId(id);
					if (perstr.length()>0) {
						perstr.append(",");
					}
					perstr.append(tbPermission.getPerDes());
				}
				map.put("permission", perstr.toString());
				jsonlist.add(map);
			}else {
				String userpermission = tbSuser.getPermission()!=null?tbSuser.getPermission():"0";
				if(userpermission.indexOf("1")!=-1) {
					
				}else {
					JSONObject map = new JSONObject();
					map.put("id", tbSuser.getId());
					map.put("loginname", tbSuser.getUsername());
					map.put("realname", tbSuser.getRealname());
					String[] permissions = tbSuser.getPermission().split(",");
					StringBuilder perstr = new StringBuilder();
					for(int i = 0 ;i<permissions.length;i++) {
						Integer id = Integer.parseInt(permissions[i]);
						TBPermission tbPermission = permissionMpper.selectByPermissionId(id);
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
		return jsonmap;
	}

	/**
	 * 添加用户信息
	 */
	@Override
	public JSONObject insertSuserData(TBSuser tbSuser) {
		// TODO Auto-generated method stub
		String mdpassword = MD5.encrypt(tbSuser.getPassword());//加密
		tbSuser.setPassword(mdpassword);
		int result = suserMapper.insertSelective(tbSuser);
		JSONObject json = new JSONObject();
		if (result>0) {
			json.put("code", 0);
		}else {
			json.put("code", 1);
		}
		return json;
	}

	
	/**
	 * 更新用户信息
	 */
	@Override
	public JSONObject updateSuserDataById(TBSuser tbSuser) {
		// TODO Auto-generated method stub
		String mdpassword = MD5.encrypt(tbSuser.getPassword());//加密
		tbSuser.setPassword(mdpassword);
		int result = suserMapper.updateByPrimaryKeySelective(tbSuser);
		JSONObject json = new JSONObject();
		if(result>0) {
			json.put("code", 0);
		}else {
			json.put("code", 1);
		}
		return json;
	}

	
	/**
	 * 删除用户信息
	 */
	@Override
	public JSONObject deleteSuserDataById(Integer id,HttpServletRequest request) {
		// TODO Auto-generated method stub
		session = request.getSession();
		String name = (String) session.getAttribute("realname");
		TBSuser tbSuser = suserMapper.selectByPrimaryKey(id);
		log.info(name+"管理员删除了平台用户"+tbSuser.getUsername()+"的登录权限");
		int result = suserMapper.deleteByPrimaryKey(id);
		JSONObject json = new JSONObject();
		if(result>0) {
			json.put("code", 0);
		}else {
			json.put("code", 1);
		}
		return json;
	}

	/**
	 * 根据用户的ID查询当前用户的详细信息
	 */
	@Override
	public JSONObject selectSuserDataById(Integer id,HttpServletRequest request) {
		// TODO Auto-generated method stub
		TBSuser tbSuser = suserMapper.selectByPrimaryKey(id);
		session = request.getSession();
		String name = (String) session.getAttribute("realname");
		log.info(name+"管理员查看了"+tbSuser.getUsername()+"用户的基本信息");
		JSONObject json = new JSONObject();
		json.put("loginname", tbSuser.getUsername());
		json.put("password", tbSuser.getPassword());
		json.put("realname", tbSuser.getUsername());
		json.put("permission", tbSuser.getPermission());
		return json;
	}

	/**
	 * 获取所有权限的数据
	 */
	@Override
	public JSONObject selectAllPermission() {
		// TODO Auto-generated method stub
		List<TBPermission> list = permissionMpper.selectPermissionData();
		JSONObject json = new JSONObject();
		List<JSONObject> jsonlist = new ArrayList<>();
		for(TBPermission tbPermission:list) {
			JSONObject map = new JSONObject();
			map.put("per_id", tbPermission.getPerId());
			map.put("per_name", tbPermission.getPerDes());
			jsonlist.add(map);
		}
		json.put("data", jsonlist);
		return json;
	}

}
