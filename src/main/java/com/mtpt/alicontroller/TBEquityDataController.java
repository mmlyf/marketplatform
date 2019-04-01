package com.mtpt.alicontroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mtpt.alibean.TBEquityData;
import com.mtpt.alibean.page.EquityDataPage;
import com.mtpt.aliservice.ITBEquityDataService;
import com.mtpt.extend.OtherMethod;

@Controller
@RequestMapping("/equitydata")
public class TBEquityDataController {
	
	@Resource
	private ITBEquityDataService equityDataService;
	
	/**
	 * 查询权益的结果值
	 * @param page
	 * @param response
	 */
	@RequestMapping(value="/selectdataresult",method= {RequestMethod.POST,RequestMethod.GET})
	public void selectEquityDataWithResult(EquityDataPage page,HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		JSONObject json = equityDataService.selectEquityDataWithResult(page);
		OtherMethod.PrintFlush(response, json);
	}
	
	
}
