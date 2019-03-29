package com.mtpt.alicontroller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mtpt.alibean.TBGFCCTotal;
import com.mtpt.alibean.TBOutCallCCount;
import com.mtpt.alibean.TBTongjianpfPvcount;
import com.mtpt.alibean.page.PublicPage;
import com.mtpt.aliservice.ITBGFCCTotalService;
import com.mtpt.aliservice.ITBOutCallCCountService;
import com.mtpt.aliservice.ITBTongjianpfPvcountService;
import com.mtpt.extend.OtherMethod;

@Controller
@RequestMapping("/outcall")
public class TBOutCallCCountController {
	private Logger log = Logger.getLogger(TBOutCallCCountController.class);
	@Resource
	private ITBOutCallCCountService outCallCCountService;
	@Resource 
	private ITBTongjianpfPvcountService tongjianpfPvcountService;
	@Resource 
	private ITBGFCCTotalService gfccTotalService;
	
	/**
	 * 查询外呼的信用卡页面浏览数
	 * @param page
	 * @param response
	 */
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
	
	/**
	 * 获取通建外呼信用卡页面的PV数据
	 * @param page
	 * @param response
	 */
	@CrossOrigin
	@RequestMapping(value="/seltjpfpv",method= {RequestMethod.POST,RequestMethod.GET})
	private void selectTongjianPfPvcountData(PublicPage page,HttpServletResponse response) {
		Integer totals = tongjianpfPvcountService.selectAllDataCount();
		page.setTotalRecord(totals);
		List<TBTongjianpfPvcount> list = tongjianpfPvcountService.selectAllDataByPage(page);
		JSONObject json = new JSONObject();
		List<JSONObject> jsonlist = new ArrayList<>();
		if (list.isEmpty()) {
			log.debug("当前记录中无值");
			json.put("code", 0);
			json.put("msg", "当前无值");
			json.put("data", "");
			json.put("count", 0);	
		}else {
			for(TBTongjianpfPvcount tongjianpfPvcount : list) {
				JSONObject value = new JSONObject();
				value.put("id", tongjianpfPvcount.getId());
				value.put("pv", tongjianpfPvcount.getPv());
				value.put("addtime", tongjianpfPvcount.getAddtime());
				jsonlist.add(value);
			}
			json.put("code", 0);
			json.put("msg", "");
			json.put("count", totals);
			json.put("data", jsonlist);
		}
		OtherMethod.PrintFlush(response, json);
	}
	
	/**
	 * 获取广发外呼信用卡页面的PV数据
	 * @param page
	 * @param response
	 */
	@CrossOrigin
	@RequestMapping(value="/selgfpv",method= {RequestMethod.POST,RequestMethod.GET})
	private void selectGFCCPvcountData(PublicPage page,HttpServletResponse response) {
		Integer totals = gfccTotalService.selectAllDataCount();
		page.setTotalRecord(totals);
		List<TBGFCCTotal> list = gfccTotalService.selectAllDataByPage(page);
		JSONObject json = new JSONObject();
		List<JSONObject> jsonlist = new ArrayList<>();
		if (list.isEmpty()) {
			log.debug("当前记录中无值");
			json.put("code", 0);
			json.put("msg", "当前无值");
			json.put("data", "");
			json.put("count", 0);	
		}else {
			for(TBGFCCTotal tbgfccTotal : list) {
				JSONObject value = new JSONObject();
				value.put("id", tbgfccTotal.getId());
				value.put("pv", tbgfccTotal.getPv());
				value.put("addtime", tbgfccTotal.getAddtime());
				jsonlist.add(value);
			}
			json.put("code", 0);
			json.put("msg", "");
			json.put("count", totals);
			json.put("data", jsonlist);
		}
		OtherMethod.PrintFlush(response, json);
	}
	
	/**
	 * 颢闵的写字楼项目 MORE东兰活动数据的获取
	 * @param page
	 * @param response
	 */
	@CrossOrigin
	@RequestMapping(value="/caselan",method= {RequestMethod.POST,RequestMethod.GET})
	private void selectMoreCaseLanPvcountData(PublicPage page,HttpServletResponse response) {
		Integer totals = gfccTotalService.selectMoreCaseLanAllDataCount();
		page.setTotalRecord(totals);
		List<TBGFCCTotal> list = gfccTotalService.selectMoreCaseLanAllDataByPage(page);
		JSONObject json = new JSONObject();
		List<JSONObject> jsonlist = new ArrayList<>();
		if (list.isEmpty()) {
			log.debug("当前记录中无值");
			json.put("code", 0);
			json.put("msg", "当前无值");
			json.put("data", "");
			json.put("count", 0);	
		}else {
			for(TBGFCCTotal tbgfccTotal : list) {
				JSONObject value = new JSONObject();
				value.put("id", tbgfccTotal.getId());
				value.put("pv", tbgfccTotal.getPv());
				value.put("addtime", tbgfccTotal.getAddtime());
				jsonlist.add(value);
			}
			json.put("code", 0);
			json.put("msg", "");
			json.put("count", totals);
			json.put("data", jsonlist);
		}
		OtherMethod.PrintFlush(response, json);
	}
	
	/**
	 * 颢明的商业项目 叫 MORE（名品商厦）的页面浏览数据的获取
	 * @param page
	 * @param response
	 */
	@CrossOrigin
	@RequestMapping(value="/casepin",method= {RequestMethod.POST,RequestMethod.GET})
	private void selectMoreCasePinPvcountData(PublicPage page,HttpServletResponse response) {
		Integer totals = gfccTotalService.selectMoreCasePinAllDataCount();
		page.setTotalRecord(totals);
		List<TBGFCCTotal> list = gfccTotalService.selectMoreCasePinAllDataByPage(page);
		JSONObject json = new JSONObject();
		List<JSONObject> jsonlist = new ArrayList<>();
		if (list.isEmpty()) {
			log.debug("当前记录中无值");
			json.put("code", 0);
			json.put("msg", "当前无值");
			json.put("data", "");
			json.put("count", 0);	
		}else {
			for(TBGFCCTotal tbgfccTotal : list) {
				JSONObject value = new JSONObject();
				value.put("id", tbgfccTotal.getId());
				value.put("pv", tbgfccTotal.getPv());
				value.put("addtime", tbgfccTotal.getAddtime());
				jsonlist.add(value);
			}
			json.put("code", 0);
			json.put("msg", "");
			json.put("count", totals);
			json.put("data", jsonlist);
		}
		OtherMethod.PrintFlush(response, json);
	}
}
