package com.mtpt.aliservice.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mchange.v1.lang.TVLUtils;
import com.mtpt.alibean.TBGFCCTotal;
import com.mtpt.alibean.TBOutCallCCount;
import com.mtpt.alibean.TBTongjianpfPvcount;
import com.mtpt.alibean.page.PublicPage;
import com.mtpt.alidao.TBGFCCTotalMapper;
import com.mtpt.alidao.TBOutCallCCountMapper;
import com.mtpt.alidao.TBTongjianpfPvcountMapper;
import com.mtpt.aliservice.ITBOutCallCCountService;
@Service("outCallCcountService")
public class TBOutCallCCountService implements ITBOutCallCCountService{

	private Logger log = Logger.getLogger(TBOutCallCCountService.class);
	
	@Autowired
	private TBOutCallCCountMapper outcallMapper;
	@Autowired 
	private TBTongjianpfPvcountMapper tjpfpvMapper;
	@Autowired
	private TBGFCCTotalMapper tbgfccTotalMapper;
	
	@Override
	public JSONObject selectOutCallPvCount(PublicPage page) {
		// TODO Auto-generated method stub
		Integer total = outcallMapper.selectAllDataCountByPage();
		page.setTotalRecord(total);
		List<TBOutCallCCount> list = outcallMapper.selectAllDataByPage(page);
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
		return json;
	}
	
	@Override
	public JSONObject selectTongjianPfPvcountData(PublicPage page) {
		// TODO Auto-generated method stub
		Integer totals = tjpfpvMapper.selectAllDataCount();
		page.setTotalRecord(totals);
		List<TBTongjianpfPvcount> list = tjpfpvMapper.selectAllDataByPage(page);
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
		return json;
	}
	@Override
	public JSONObject selectGFCCPvcountData(PublicPage page) {
		// TODO Auto-generated method stub
		Integer totals = tbgfccTotalMapper.selectAllDataCount();
		page.setTotalRecord(totals);
		List<TBGFCCTotal> list = tbgfccTotalMapper.selectAllDataByPage(page);
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
		return json;
	}
	@Override
	public JSONObject selectMoreCaseLanPvcountData(PublicPage page) {
		// TODO Auto-generated method stub
		Integer totals = tbgfccTotalMapper.selectMoreCaseLanAllDataCount();
		page.setTotalRecord(totals);
		List<TBGFCCTotal> list = tbgfccTotalMapper.selectMoreCaseLanAllDataByPage(page);
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
		return json;
	}
	@Override
	public JSONObject selectMoreCasePinPvcountData(PublicPage page) {
		// TODO Auto-generated method stub
		Integer totals = tbgfccTotalMapper.selectMoreCasePinAllDataCount();
		page.setTotalRecord(totals);
		List<TBGFCCTotal> list = tbgfccTotalMapper.selectMoreCasePinAllDataByPage(page);
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
		return json;
	}
	
}
