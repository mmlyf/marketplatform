package com.mtpt.alicontroller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mtpt.alibean.page.PublicPage;
import com.mtpt.aliservice.IThirdOutActivityDataTotalService;
import com.mtpt.extend.OtherMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/thirdouttotal")
public class ThirdOutActivityDataTotalController {
	
	@Resource
	private IThirdOutActivityDataTotalService thirdOutActivityDataTotalService;
	
	@CrossOrigin
	@RequestMapping(value="/selectdx",method= {RequestMethod.POST,RequestMethod.GET})
	private void selectDxThirdDataByPage(PublicPage page,HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		JSONObject json = thirdOutActivityDataTotalService.selectDxThirdDataByPage(page);
		OtherMethod.PrintFlush(response, json);
	}
	
	@CrossOrigin
	@RequestMapping(value="/selecticegod",method= {RequestMethod.POST,RequestMethod.GET})
	private void selectIceGodDataByPage(PublicPage page,HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		JSONObject json = thirdOutActivityDataTotalService.selectIceGodDataByPage(page);
		OtherMethod.PrintFlush(response, json);
	}
	
	@CrossOrigin
	@RequestMapping(value="/selecticethird",method= {RequestMethod.POST,RequestMethod.GET})
	private void selectIceThirdDataByPage(PublicPage page,HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		JSONObject json = thirdOutActivityDataTotalService.selectIceThirdDataByPage(page);
		OtherMethod.PrintFlush(response, json);
	}
	
	@CrossOrigin
	@RequestMapping(value="/selectcoupon",method= {RequestMethod.GET,RequestMethod.POST})
	private void selectCouponData(HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		JSONObject json = thirdOutActivityDataTotalService.selectCouponDataByAll();
		OtherMethod.PrintFlush(response, json);
	}
}
