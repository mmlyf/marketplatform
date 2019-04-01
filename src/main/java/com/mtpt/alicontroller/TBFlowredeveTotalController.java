package com.mtpt.alicontroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mtpt.alibean.TBFlowredeveTotal;
import com.mtpt.alibean.page.PublicPage;
import com.mtpt.aliservice.ITBFlowredeveTotalService;
import com.mtpt.extend.OtherMethod;
import com.mysql.cj.log.Log;

@Controller
@RequestMapping("/flowredeve")
public class TBFlowredeveTotalController {
	private Logger log = Logger.getLogger(TBFlowredeveTotalController.class);
	@Resource 
	private ITBFlowredeveTotalService flowredeveTotalService;
	
	/**
	 * 获取流量红包统计数据并进行分页展示
	 * @param page
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/selectdata",method= {RequestMethod.POST,RequestMethod.GET})
	private void selectDataByPublicPageForTable(PublicPage page,HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		JSONObject json = flowredeveTotalService.selectFlowRedPackageDataByPage(page);
		OtherMethod.PrintFlush(response, json);
	}
	
	/**
	 * 获取流量红包数据求和结果
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/selecttotal",method= {RequestMethod.POST,RequestMethod.GET})
	private void selectDataTotalCountByAll(HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		JSONObject json = flowredeveTotalService.selectFlowRedPackageDataTotalCountByAll();
		OtherMethod.PrintFlush(response, json);
	}
}
