package com.mtpt.service;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;

import com.mtpt.bean.page.AlipayPage;

public interface IAlipayBindUserManageService {
	//分页查询支付宝绑定用户数据
	JSONObject selectAlipayBindUserByPage(AlipayPage page,HttpServletRequest request);
	
	//分页查询当前支付宝绑定用户流量赠送详细情况
	JSONObject selectAlipayBindUserSendFlowDetailByPage(AlipayPage page,HttpServletRequest request);
	
	//查询当前支付宝绑定用户流量赠送失败的数据 
	JSONObject selectAlipayBindUserUnGiftFlowByPage(AlipayPage page,HttpServletRequest request);
	
	//给特定的支付宝绑定用户赠送流量
	JSONObject submitAlipayBindUserGiftFlow(String phonenum,String flow);
	
	String outputAlipayBindUserInfo();
	
}
