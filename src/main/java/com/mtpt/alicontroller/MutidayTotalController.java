package com.mtpt.alicontroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.print.attribute.standard.MediaSize.Other;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mtpt.alibean.MutidayTotal;
import com.mtpt.alibean.page.PublicPage;
import com.mtpt.aliservice.impl.MutidayTotalService;
import com.mtpt.extend.OtherMethod;

@Controller
@RequestMapping("/mutidaytotal")
public class MutidayTotalController {
	@Resource
	private MutidayTotalService mutiService;
	
	/**
	 * 
	 * @param page
	 * @param response
	 * 分页查询所有的流量包页面统计数据
	 * 
	 */
	@RequestMapping(value="/selectall",method= {RequestMethod.POST,RequestMethod.GET})
	private void selectAllDataByPage(PublicPage page,HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		JSONObject json = mutiService.selectAllMutidayDataByPage(page);
		OtherMethod.PrintFlush(response, json);
	}
	
	/**
	 * 
	 * @param response
	 * 对统计的数据进行总和处理并在页面上展示
	 * 
	 */
	@RequestMapping(value="/selecttotal",method= {RequestMethod.POST,RequestMethod.GET})
	private void selectAllDataByTotal(HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		JSONObject json = mutiService.selectAllMutidayDataTotal();
		OtherMethod.PrintFlush(response, json);
	}
}
