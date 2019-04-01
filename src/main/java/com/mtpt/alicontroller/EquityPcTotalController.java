package com.mtpt.alicontroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mtpt.alibean.page.PublicPage;
import com.mtpt.aliservice.IEquityPcTotalService;
import com.mtpt.extend.OtherMethod;

/**
 * 
 * @author lvgordon
 * 对抽奖活动的数据统计表格进行展示，并通过json数据回传给前端
 *
 */
@Controller
@RequestMapping("/equityPcTotal")
public class EquityPcTotalController {
	private Logger log = Logger.getLogger(EquityPcTotalController.class);
	
	@Resource
	private IEquityPcTotalService pctotalService;

	/**
	 * 查询权益统计数据分页展示
	 * @param page
	 * @param response
	 */
	@RequestMapping(value="/selectall",method= {RequestMethod.POST,RequestMethod.GET})
	private void selectAllTotalData(PublicPage page,HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		JSONObject json = pctotalService.selectAllEquityDataByPage(page);
		OtherMethod.PrintFlush(response, json);
	}

	/**
	 * 获取权益统计数据的总和
	 * @param response
	 */
	@RequestMapping(value="/selecttotal",method= {RequestMethod.POST,RequestMethod.GET})
	private void selectAllDataTotal(HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		JSONObject json = pctotalService.selectAllEquityDataForTotal();
		OtherMethod.PrintFlush(response, json);
	}
}
