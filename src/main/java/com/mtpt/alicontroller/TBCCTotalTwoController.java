package com.mtpt.alicontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mtpt.alibean.TBCCTotalTwo;
import com.mtpt.alibean.page.PublicPage;
import com.mtpt.aliservice.ITBCCTotalTwoService;
import com.mtpt.extend.OtherMethod;

import static org.junit.Assert.assertArrayEquals;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/tbcctotalTwo")
public class TBCCTotalTwoController {
	@Resource
	private ITBCCTotalTwoService totalTwoService;
	
	/**
	 * 分页进行查询所有的数据并将数据通过json传至前端
	 * @param page
	 * @param response
	 */
	@RequestMapping(value="/selectAllData",method= {RequestMethod.GET,RequestMethod.POST})
	private void selectAllDataByPage(PublicPage page,HttpServletResponse response) {
		int totalrecords = totalTwoService.selectAllDataCount(page);
		page.setTotalRecord(totalrecords);
		List<TBCCTotalTwo> datalist = totalTwoService.selectAllDataByPage(page);
		JSONObject json = new JSONObject();
		List<JSONObject> jsonlist = new ArrayList<>();
		for(TBCCTotalTwo tbccTotalTwo : datalist) {
			JSONObject valuemap = new JSONObject();
			valuemap.put("id", tbccTotalTwo.getId());
			valuemap.put("pv", tbccTotalTwo.getPv());
			valuemap.put("bc", tbccTotalTwo.getBc());
			valuemap.put("see_dx", tbccTotalTwo.getSeeDx());
			valuemap.put("see_llb", tbccTotalTwo.getSeeLlb());
			valuemap.put("see_muti", tbccTotalTwo.getSeeMuti());
			valuemap.put("see_tehui", tbccTotalTwo.getSeeTehui());
			valuemap.put("addtime", tbccTotalTwo.getAddtime());
			jsonlist.add(valuemap);
		}
		json.put("code", 0);
		json.put("msg", "");
		json.put("data", jsonlist);
		json.put("count", totalrecords);
		OtherMethod.PrintFlush(response, json);
	}
	
	@RequestMapping(value="/selectTotal",method= {RequestMethod.POST,RequestMethod.GET})
	private void selectAllDataTotal(HttpServletResponse response) {
		List<TBCCTotalTwo> list = totalTwoService.selectAllData();
		int pvtotal = 0;
		int bctotal = 0;
		int dxtotal = 0;
		int icetotal = 0;
		int mutitotal = 0;
		int tehuitotal = 0;
		JSONObject json = new JSONObject();
		for(TBCCTotalTwo tbccTotalTwo:list) {
			pvtotal += tbccTotalTwo.getPv();
			bctotal += tbccTotalTwo.getBc();
			dxtotal += tbccTotalTwo.getSeeDx();
			icetotal += tbccTotalTwo.getSeeLlb();
			mutitotal += tbccTotalTwo.getSeeMuti();
			tehuitotal += tbccTotalTwo.getSeeTehui();
		}
		json.put("pvtotal", pvtotal);
		json.put("bctotal", bctotal);
		json.put("dxtotal", dxtotal);
		json.put("icetotal", icetotal);
		json.put("mutitotal", mutitotal);
		json.put("tehuitotal", tehuitotal);
		OtherMethod.PrintFlush(response, json);
	}
}
