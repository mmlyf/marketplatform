package com.mtpt.alicontroller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mtpt.alibean.TBOutCallCCount;
import com.mtpt.alibean.page.PublicPage;
import com.mtpt.aliservice.ITBOutCallCCountService;
import com.mtpt.extend.OtherMethod;

@Controller
@RequestMapping("/outcall")
public class TBOutCallCCountController {
	@Resource
	private ITBOutCallCCountService outCallCCountService;
	
	
	@RequestMapping(value="/select",method= {RequestMethod.GET,RequestMethod.POST})
	private void selectOutCallPvCount(PublicPage page,HttpServletResponse response) {
		Integer total = outCallCCountService.selectAllDataCountByPage();
		page.setTotalRecord(total);
		List<TBOutCallCCount> list = outCallCCountService.selectAllDataByPage(page);
		JSONObject json = new JSONObject();
		List<JSONObject> jsonlist = new ArrayList<>();
		for(TBOutCallCCount tbOutCallCCount:list) {
			JSONObject value = new JSONObject();
			value.put("id", tbOutCallCCount.getId());
			value.put("pv", tbOutCallCCount.getPv());
			value.put("addtime", tbOutCallCCount.getAddtime());
			jsonlist.add(value);
		}
		json.put("code", 0);
		json.put("msg", "");
		json.put("count", total);
		json.put("data", jsonlist);
		OtherMethod.PrintFlush(response, json);
	}
}
