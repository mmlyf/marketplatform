package com.mtpt.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtpt.alibean.TBHfczReview;
import com.mtpt.alidao.TBHfczReviewMapper;
import com.mtpt.bean.Zfbcz;
import com.mtpt.dao.ZfbczMapper;
import com.mtpt.extend.OtherMethod;
import com.mtpt.extend.SendMail;
import com.mtpt.service.IAliPayForPayService;

@Service("alipayforpayService")
public class AliPayForPayService implements IAliPayForPayService{

	@Autowired
	private TBHfczReviewMapper hfczreviewMapper;
	@Autowired
	private ZfbczMapper zfbczMpper;
	
	@Override
	public JSONObject insertCZAlipayOrders(Zfbcz zfbcz, String pyTotalmoey, String reason,HttpServletRequest request) {
		// TODO Auto-generated method stub
		TBHfczReview tbHfczReview = new TBHfczReview();
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		String emailaddress = "";
		Double totalmoney = Double.valueOf(pyTotalmoey);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String py_peid = "H"+sdf.format(date)+"-"+(11+(int)(Math.random()*89))+"20180730"+(1+(int)(Math.random()*9));
		JSONObject json = new JSONObject();
		if (totalmoney<20&&totalmoney>=1) {
			String id = OtherMethod.getUuid();
			zfbcz.setPyId(id);
			zfbcz.setPyCreatime(date);
			zfbcz.setPyIfpay(1);
			zfbcz.setPyZfqd(3);
			zfbcz.setPyTotalmoey(BigDecimal.valueOf(totalmoney));
			String pyLastday = OtherMethod.addYearTime(date);
			zfbcz.setPyLastday(pyLastday);
			zfbcz.setPySeno(py_peid);
			int result = zfbczMpper.insertSelective(zfbcz);
			if (result>0) {
				json.put("code", 0);
				json.put("seno", py_peid);
			}else {
				json.put("code", 1);
				json.put("seno", "");
			}		
		}else {
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
			int inhfczres = hfczreviewMapper.insertSelective(tbHfczReview);
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
		return json;
	}

}
