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
		int records = flowredeveTotalService.selectAllDataCount();
		page.setTotalRecord(records);
		List<TBFlowredeveTotal> list = flowredeveTotalService.selectByPublicPage(page);
		JSONObject json = new JSONObject();
		List<JSONObject> jsonlist = new ArrayList<>();
		for(TBFlowredeveTotal tbFlowredeveTotal:list) {
			JSONObject value = new JSONObject();
			value.put("id", tbFlowredeveTotal.getId());
			value.put("pv", tbFlowredeveTotal.getPv());
			value.put("liji_bc", tbFlowredeveTotal.getLijiBc());
			value.put("comfir_bc", tbFlowredeveTotal.getComfirBc());
			value.put("tx_count", tbFlowredeveTotal.getTxCount());
			value.put("pp_count", tbFlowredeveTotal.getPpCount());
			value.put("aqy_count", tbFlowredeveTotal.getAqyCount());
			value.put("yk_count", tbFlowredeveTotal.getYkCount());
			value.put("ordersuc_count", tbFlowredeveTotal.getOrdersucCount());
			value.put("orderunsuc_count", tbFlowredeveTotal.getOrderunsucCount());
			value.put("addtime", tbFlowredeveTotal.getAddtime());
			jsonlist.add(value);
		}
		json.put("code", 0);
		json.put("msg", "");
		json.put("data", jsonlist);
		json.put("count", records);
		PrintWriter pw = response.getWriter();
		pw.write(json.toString());
		pw.flush();
		pw.close();
	}
	
	/**
	 * 获取流量红包数据求和结果
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/selecttotal",method= {RequestMethod.POST,RequestMethod.GET})
	private void selectDataTotalCountByAll(HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		List<TBFlowredeveTotal> list = flowredeveTotalService.selectAllData();
		int pvtotal = 0;
		int lijibctotal = 0;
		int comfirbctotal = 0;
		int txcounttotal = 0;
		int ppcounttotal = 0;
		int aqycounttotal = 0;
		int ykcounttotal = 0;
		int ordersuccounttotal = 0;
		int orderunsuccounttotal = 0;
		for(TBFlowredeveTotal tbFlowredeveTotal:list) {
			pvtotal += tbFlowredeveTotal.getPv();
			lijibctotal += tbFlowredeveTotal.getLijiBc();
			comfirbctotal += tbFlowredeveTotal.getComfirBc();
			txcounttotal += tbFlowredeveTotal.getTxCount();
			ppcounttotal += tbFlowredeveTotal.getPpCount();
			aqycounttotal += tbFlowredeveTotal.getAqyCount();
			ykcounttotal += tbFlowredeveTotal.getYkCount();
			ordersuccounttotal += tbFlowredeveTotal.getOrdersucCount();
			orderunsuccounttotal += tbFlowredeveTotal.getOrderunsucCount();
		}
		JSONObject json = new JSONObject();
		json.put("pvtotal", pvtotal);
		json.put("lijibctotal", lijibctotal);
		json.put("comfirbctotal", comfirbctotal);
		json.put("txcounttotal", txcounttotal);
		json.put("ppcounttotal", ppcounttotal);
		json.put("aqycounttotal", aqycounttotal);
		json.put("ykcounttotal", ykcounttotal);
		json.put("ordersuccounttotal", ordersuccounttotal);
		json.put("orderunsuccounttotal", orderunsuccounttotal);
		PrintWriter pw = response.getWriter();
		pw.write(json.toString());
		pw.flush();
		pw.close();
	}
}
