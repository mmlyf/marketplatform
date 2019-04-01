package com.mtpt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.json.Json;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mtpt.alibean.TBHfczReview;
import com.mtpt.aliservice.ITBHfczReviewService;
import com.mtpt.bean.Zfbcz;
import com.mtpt.extend.OtherMethod;
import com.mtpt.extend.SendMail;
import com.mtpt.service.IAliPayForPayService;

/**
 * 
 * @author lvgordon
 * 支付宝话费充值
 * 对话费进行充值
 *
 */
@Controller
@RequestMapping("/alipay")
public class AliPayForPayController {
	@Resource
	private IAliPayForPayService alipayService;

	@RequestMapping(value="/insert",method= {RequestMethod.POST,RequestMethod.GET})
	private void insertAlipayOrders(Zfbcz zfbcz,String pyTotalmoey,String reason,HttpServletResponse response,HttpServletRequest request) {
		response.setContentType("text/html;charset=utf-8");
		JSONObject json = alipayService.insertCZAlipayOrders(zfbcz, pyTotalmoey, reason, request);
		OtherMethod.PrintFlush(response, json);
	}
}
