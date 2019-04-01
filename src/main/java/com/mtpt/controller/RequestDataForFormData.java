package com.mtpt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mtpt.bean.TBMssage;
import com.mtpt.bean.TBProd;
import com.mtpt.bean.TBProdDw;
import com.mtpt.bean.TBProdLx;
import com.mtpt.extend.OtherMethod;
import com.mtpt.service.IRequestDataForFormDataService;

@Controller
@RequestMapping("/requestdata")
public class RequestDataForFormData {
	private Logger log = Logger.getLogger(RequestDataForFormData.class);
	
	@Resource
	private IRequestDataForFormDataService requestDataForFormService;

	@RequestMapping(value="/selectprod",method = {RequestMethod.POST,RequestMethod.GET})
	private void selectProdData(HttpServletResponse response,Integer prodid,Integer prodlxid) {
		log.debug("proid的值是："+prodid+"以及prodlxid:"+prodlxid);
		response.setContentType("text/html; charset=UTF-8");
		JSONObject json = requestDataForFormService.selectProdData(prodid, prodlxid);
		OtherMethod.PrintFlush(response, json);
	}
	
	@RequestMapping(value="/selectmsg",method = {RequestMethod.POST,RequestMethod.GET})
	private void selectMessageData(HttpServletResponse response) {
		response.setContentType("text/html; charset=UTF-8");
		JSONObject jsondata = requestDataForFormService.selectMessageData();
		OtherMethod.PrintFlush(response, jsondata);
		
	}
}
