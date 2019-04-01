package com.mtpt.service;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;

import com.mtpt.bean.TBSuser;
import com.mtpt.bean.TBUsers;

public interface ILoginInAndInsertInUserService {
	JSONObject loginInByIdPaw(String username,String password,HttpServletRequest request);
	
	JSONObject selectSuserAllData(String permission);
	
	JSONObject insertSuserData(TBSuser tbSuser);
	
	JSONObject updateSuserDataById(TBSuser tbSuser);
	
	JSONObject deleteSuserDataById(Integer id,HttpServletRequest request);
	
	JSONObject selectSuserDataById(Integer id,HttpServletRequest request);
	
	JSONObject selectAllPermission();
}
