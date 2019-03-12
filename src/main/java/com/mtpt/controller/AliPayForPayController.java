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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mtpt.alibean.TBHfczReview;
import com.mtpt.aliservice.ITBHfczReviewService;
import com.mtpt.bean.Zfbcz;
import com.mtpt.extend.OtherMethod;
import com.mtpt.extend.SendMail;
import com.mtpt.service.IZfbczService;

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
	private IZfbczService zfbczService;
	@Resource
	private ITBHfczReviewService hfczreviewService;
	
	@RequestMapping(value="/insert",method= {RequestMethod.POST,RequestMethod.GET})
	private void insertAlipayOrders(Zfbcz zfbcz,String pyTotalmoey,String reason,HttpServletResponse response,HttpServletRequest request) {
		TBHfczReview tbHfczReview = new TBHfczReview();
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		String emailaddress = "";
		String id = OtherMethod.getUuid();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		//rand(11,99).'20180730'.rand(1,9
		String py_peid = "H"+sdf.format(date)+"-"+(11+(int)(Math.random()*89))+"20180730"+(1+(int)(Math.random()*9));
		zfbcz.setPyId(id);
		zfbcz.setPyCreatime(date);
		zfbcz.setPyIfpay(1);
		zfbcz.setPyZfqd(3);
		Double totalmoney = Double.valueOf(pyTotalmoey);
		zfbcz.setPyTotalmoey(BigDecimal.valueOf(totalmoney));
		String pyLastday = OtherMethod.addYearTime(date);
		zfbcz.setPyLastday(pyLastday);
		zfbcz.setPySeno(py_peid);
		int result = zfbczService.insertSelective(zfbcz);
		JSONObject json = new JSONObject();
		if (result>0) {
			if (totalmoney<20&&totalmoney>=1) {
				json.put("code", 0);
				json.put("seno", py_peid);
			}else {
				tbHfczReview.setSeNo(py_peid);
				tbHfczReview.setCzDn(zfbcz.getPyDn());
				tbHfczReview.setCzAmount(totalmoney);
				tbHfczReview.setCzReason(reason);
				sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				tbHfczReview.setCzAddtime(sdf.format(date));
				tbHfczReview.setCzReviewstate(0);
				tbHfczReview.setCzAddman(username);
				if (totalmoney>=20&&totalmoney<50) {
					tbHfczReview.setCzReviewer("裴秋婷");
					emailaddress = "32223815@qq.com";
				}else if (totalmoney>=50) {
					tbHfczReview.setCzReviewer("贲莉");
					emailaddress = "benl@mobile99.cn";
				}
				int inhfczres = hfczreviewService.insertSelective(tbHfczReview);
				if (inhfczres>0) {
					SendMail.sendMailForToMailAddress("当前有充值订单需要审核，"
							+ "请访问链接\"http://221.192.138.29:8089/HSDT_Market_Platform/jsp/login.jsp\"进行处理。谢谢！", 
							"", emailaddress);
					json.put("code", 2);
				}else {
					json.put("code", 1);
					json.put("msg", "审核任务保存失败！");
				}
			}
		}else {
			json.put("code", 1);
			json.put("seno", "");
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
}
