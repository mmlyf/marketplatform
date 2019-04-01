package com.mtpt.alicontroller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mtpt.alibean.page.PublicPage;
import com.mtpt.aliservice.impl.CreditCardTotalDataService;
import com.mtpt.extend.OtherMethod;

@Controller
@RequestMapping("/tbccTotal")
public class TBCCTotalController {
	@Resource
	private CreditCardTotalDataService cctotalService;
	
	/**
	 * 信用卡活动V1.0分页查询所有数据
	 * @param page
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/selectall",method= {RequestMethod.POST,RequestMethod.GET})
	private void selectAllDataByPage(PublicPage page,HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		JSONObject json = cctotalService.selectVoneCreditCardTotalData(page);
		OtherMethod.PrintFlush(response, json);
	}
	
	/**
	 * 查询所有统计数据的总和
	 * @param response
	 */
	@RequestMapping(value="/selecttotal",method= {RequestMethod.POST,RequestMethod.GET})
	private void selectAllDataTotal(HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		JSONObject json = cctotalService.selectVoneCreditCardSumTotal();
		OtherMethod.PrintFlush(response, json);
	}
}
