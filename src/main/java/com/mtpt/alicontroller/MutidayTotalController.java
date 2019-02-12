package com.mtpt.alicontroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mtpt.alibean.MutidayTotal;
import com.mtpt.alibean.page.PublicPage;
import com.mtpt.aliservice.impl.MutidayTotalService;

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
		int records = mutiService.selectAllDataCount();
		page.setTotalRecord(records);
		List<MutidayTotal> list = mutiService.selectAllDataByPage(page);
		JSONObject json = new JSONObject();
		List<JSONObject> jsonlist = new ArrayList<>();
		for(MutidayTotal mutidayTotal:list) {
			JSONObject value = new JSONObject();
			value.put("id", mutidayTotal.getId());
			value.put("pv", mutidayTotal.getPv());
			value.put("bc", mutidayTotal.getBc());
			value.put("six_orderc", mutidayTotal.getSixOrderc());
			value.put("nine_orderc", mutidayTotal.getNineOrderc());
			value.put("addtime", mutidayTotal.getAddtime());
			jsonlist.add(value);
		}
		json.put("code", 0);
		json.put("msg", "");
		json.put("data", jsonlist);
		json.put("count", records);
		try {
			PrintWriter pw = response.getWriter();
			pw.write(json.toString());
			pw.flush();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		List<MutidayTotal> list = mutiService.selectAllData();
		int pv = 0;
		int bc = 0;
		int sixorderc = 0;
		int nineorderc = 0;
		JSONObject json = new JSONObject();
		for (MutidayTotal mutidayTotal:list) {
			pv += mutidayTotal.getPv();
			bc += mutidayTotal.getBc();
			sixorderc += mutidayTotal.getSixOrderc();
			nineorderc += mutidayTotal.getNineOrderc();
		}
		json.put("pv", pv);
		json.put("bc", bc);
		json.put("six_orderc", sixorderc);
		json.put("nine_orderc", nineorderc);
		try {
			PrintWriter pw = response.getWriter();
			pw.write(json.toString());
			pw.flush();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
