package com.mtpt.alicontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mtpt.alibean.TBRecord;
import com.mtpt.alibean.TBTehuiFlowTotal;
import com.mtpt.alibean.page.PublicPage;
import com.mtpt.aliservice.ITBTehuiFlowTotalService;
import com.mtpt.extend.OtherMethod;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;

/**
 * 
 * 特惠流量包活动的数据请求接口
 * @author lvgordon
 *
 */
@Controller
@RequestMapping("/tehuiFlowTotal")
public class TBTehuiFlowTotalController {
	@Resource 
	private ITBTehuiFlowTotalService service;
	
	/**
	 * 查找特惠流量包数据
	 * @param page
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/selectdata",method= {RequestMethod.POST,RequestMethod.GET})
	private void selectDataByPublicPageForTable(PublicPage page,HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		JSONObject json = service.selectDataByPublicPageForTable(page);
		OtherMethod.PrintFlush(response, json);
	}
	
	/**
	 * 请求数据总和
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/selecttotal",method= {RequestMethod.GET,RequestMethod.POST})
	private void selectDataTotal(HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		JSONObject json = service.selectDataTotal();
		OtherMethod.PrintFlush(response, json);
	}
}
