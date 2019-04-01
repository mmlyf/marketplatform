package com.mtpt.service;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;

import com.mtpt.bean.Zfbcz;

public interface IAliPayForPayService {
	JSONObject insertCZAlipayOrders(Zfbcz zfbcz,String pyTotalmoey,String reason,HttpServletRequest request);
	
	
}
