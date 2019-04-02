package com.mtpt.alicontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mtpt.alibean.page.PublicPage;
import com.mtpt.aliservice.ICreditCardTotalDataService;
import com.mtpt.aliservice.impl.CreditCardTotalDataService;
import com.mtpt.extend.OtherMethod;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/tbcctotalTwo")
public class TBCCTotalTwoController {
	@Resource
	private ICreditCardTotalDataService cctotalService;
	
	/**
	 * 分页进行查询信用卡活动V2.0所有的数据并将数据通过json传至前端
	 * @param page
	 * @param response
	 */
	@RequestMapping(value="/selectAllData",method= {RequestMethod.GET,RequestMethod.POST})
	private void selectAllDataByPage(PublicPage page,HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		JSONObject json = cctotalService.selectVtwoCreditCardTotalData(page);
		OtherMethod.PrintFlush(response, json);
	}
	
	/**
	 * 获取信用卡活动V2.0统计数据总和
	 * @param response
	 */
	@RequestMapping(value="/selectTotal",method= {RequestMethod.POST,RequestMethod.GET})
	private void selectAllDataTotal(HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		JSONObject json = cctotalService.selectVtwoCreditCardSumTotal();
		OtherMethod.PrintFlush(response, json);
	}
}
