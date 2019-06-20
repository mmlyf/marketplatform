package com.mtpt.hkalicontroller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mtpt.alibean.page.PublicPage;
import com.mtpt.extend.OtherMethod;
import com.mtpt.hkalilservice.ICouponOrderDataService;

@Controller
@RequestMapping("/couponorder")
public class CouponOrderDataController {
	
	@Resource
	private ICouponOrderDataService couponOrderDataService;
	
	@CrossOrigin
	@RequestMapping(value="/selectdata",method= {RequestMethod.POST,RequestMethod.GET})
	private void selectCouponOrderDataByPage(PublicPage page,HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		JSONObject json = couponOrderDataService.selectAllCouponDataByPage(page);
		OtherMethod.PrintFlush(response, json);
	}

}
