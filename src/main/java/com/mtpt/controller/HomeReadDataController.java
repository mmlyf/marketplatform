package com.mtpt.controller;

import java.text.ParseException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mtpt.bean.page.TotalPage;
import com.mtpt.extend.OtherMethod;
import com.mtpt.service.IHomeReadDataService;

@Controller
@RequestMapping("/home")
public class HomeReadDataController {

	private Logger log = Logger.getLogger(HomeReadDataController.class);
	@Resource 
	private IHomeReadDataService homeReadDataService;
	
	@RequestMapping(value="/getTotal",method = {RequestMethod.POST,RequestMethod.GET})
	private void getTotalData(TotalPage totalPage,HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		JSONObject json = homeReadDataService.selectTotalDataByPage(totalPage);
		OtherMethod.PrintFlush(response, json);
	}

	/**
	 * 
	 * @param totalPage
	 * @param request
	 * @param response
	 * @throws ParseException
	 * 
	 * 
	 */
	@RequestMapping(value="/getTotaldelay",method = {RequestMethod.POST,RequestMethod.GET})
	private void selectDataTotalByDelayforTable(TotalPage totalPage,HttpServletRequest request,
			HttpServletResponse response) throws ParseException {
		response.setContentType("text/html;charset=utf-8");
		JSONObject jsonmap = homeReadDataService.selectTotalDataDelayByPage(totalPage);
		OtherMethod.PrintFlush(response, jsonmap);
	}

	/**
	 * 
	 * @param month
	 * @param response
	 * 获取统计界面中选择的月份，如果月份为空则展示当前月份的前一个月的数据
	 * 
	 */
	@RequestMapping(value="/selectmonth",method = {RequestMethod.POST,RequestMethod.GET})
	private void selectMonthData(String month,HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		JSONObject json = homeReadDataService.selectTotalDataForAMonth(month);
		OtherMethod.PrintFlush(response, json);
	}

	/**
	 * 
	 * 
	 * @param delay
	 * @param response
	 * 运营人员可以通过选取当前时间的前几周的数据，默认为当前时间的前一周
	 * 
	 */
	@RequestMapping(value="/selectweek",method = {RequestMethod.POST,RequestMethod.GET})
	private void selectWeekData(Integer delay,HttpServletResponse response) {
		response.setContentType("text/html; charset=UTF-8");
		JSONObject json = homeReadDataService.selectTotalDataForWeek(delay);
		OtherMethod.PrintFlush(response, json);
	}

	@RequestMapping(value="/selectnowbefore",method = {RequestMethod.POST,RequestMethod.GET})
	private void selectDataTotalForBrforeNow(String addtime,HttpServletResponse response) {
		response.setContentType("text/html; charset=UTF-8");
		JSONObject json = homeReadDataService.selectDataTotalForBrforeNow(addtime);
		OtherMethod.PrintFlush(response, json);
	}
}
