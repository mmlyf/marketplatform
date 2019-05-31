package com.mtpt.aliservice.impl;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtpt.alibean.TBDxThirdPageTotal;
import com.mtpt.alibean.TBIcePageTotal;
import com.mtpt.alibean.TBIceThirdPageTotal;
import com.mtpt.alibean.page.PublicPage;
import com.mtpt.alidao.TBDxThirdPageTotalMapper;
import com.mtpt.alidao.TBIcePageTotalMapper;
import com.mtpt.alidao.TBIceThirdPageTotalMapper;
import com.mtpt.aliservice.IThirdOutActivityDataTotalService;
@Service("thirdOutActivityDataTotalService")
public class ThirdOutActivityDataTotalService implements IThirdOutActivityDataTotalService{

	
	@Autowired
	private TBDxThirdPageTotalMapper dxthirdPageTotalMapper;
	@Autowired
	private TBIcePageTotalMapper icepageTotalMapper;
	@Autowired
	private TBIceThirdPageTotalMapper icethirdPageTotalMapper;
	
	@Override
	public JSONObject selectDxThirdDataByPage(PublicPage page) {
		// TODO Auto-generated method stub
		int totals = dxthirdPageTotalMapper.selectDxThirdCountByPage(page);
		page.setTotalRecord(totals);
		List<TBDxThirdPageTotal> list = dxthirdPageTotalMapper.selectDxThirdDataByPage(page);
		JSONObject json = new JSONObject();
		List<JSONObject> jsonlist = new ArrayList<>();
		for(TBDxThirdPageTotal tbDxThirdPageTotal:list) {
			JSONObject value = new JSONObject();
			value.put("id", tbDxThirdPageTotal.getId());
			value.put("pv", tbDxThirdPageTotal.getPv());
			value.put("addtime", tbDxThirdPageTotal.getAddtime());
			jsonlist.add(value);
		}
		json.put("code", 0);
		json.put("msg", "");
		json.put("count", totals);
		json.put("data", jsonlist);
		return json;
	}

	@Override
	public JSONObject selectIceGodDataByPage(PublicPage page) {
		// TODO Auto-generated method stub
		int totals = icepageTotalMapper.selectIceGodCountByPage(page);
		page.setTotalRecord(totals);
		List<TBIcePageTotal> list = icepageTotalMapper.selectIceGodDataByPage(page);
		JSONObject json = new JSONObject();
		List<JSONObject> jsonlist = new ArrayList<>();
		for(TBIcePageTotal tbIcePageTotal:list) {
			JSONObject value = new JSONObject();
			value.put("id", tbIcePageTotal.getId());
			value.put("pv", tbIcePageTotal.getPv());
			value.put("addtime", tbIcePageTotal.getAddtime());
			jsonlist.add(value);
		}
		json.put("code", 0);
		json.put("msg", "");
		json.put("count", totals);
		json.put("data", jsonlist);
		return json;
	}

	@Override
	public JSONObject selectIceThirdDataByPage(PublicPage page) {
		// TODO Auto-generated method stub
		int totals = icethirdPageTotalMapper.selectIceThirdCountPage(page);
		page.setTotalRecord(totals);
		List<TBIceThirdPageTotal> list = icethirdPageTotalMapper.selectIceThirdDataPage(page);
		JSONObject json = new JSONObject();
		List<JSONObject> jsonlist = new ArrayList<>();
		for(TBIceThirdPageTotal tbIceThirdPageTotal:list) {
			JSONObject value = new JSONObject();
			value.put("id", tbIceThirdPageTotal.getId());
			value.put("pv", tbIceThirdPageTotal.getPv());
			value.put("addtime", tbIceThirdPageTotal.getAddtime());
			jsonlist.add(value);
		}
		json.put("code", 0);
		json.put("msg", "");
		json.put("count", totals);
		json.put("data", jsonlist);
		return json;
	}

}
