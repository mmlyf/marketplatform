package com.mtpt.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mtpt.service.IOrdersResultManageService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/ordermanage")
public class OrdersResultManageController {
	
	@Resource
	private IOrdersResultManageService ordersResultManageService;
	
	/**
	 * 请求接口用于自动赠送订购成功的订单的手机号码
	 */
	@CrossOrigin
	@RequestMapping(value="/ordergiftflow",method= {RequestMethod.POST,RequestMethod.GET})
	private void selectOrderSuccessGiftFlow(HttpServletResponse response) {
		ordersResultManageService.selectDayOrderSuccessAndGiftFlow();
		return ;
	}
	
	/**
	 * 请求一天的订购订单中有二次确认的数据
	 * @param response
	 */
	@CrossOrigin
	@RequestMapping(value="/selectconfirm",method= {RequestMethod.POST,RequestMethod.GET})
	private void selectSecondConfirmContent(HttpServletResponse response) {
		ordersResultManageService.selectDayOrderSecondConfirmContent();
		return;
	}
	
	
}
