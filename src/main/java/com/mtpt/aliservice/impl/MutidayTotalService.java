package com.mtpt.aliservice.impl;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtpt.alibean.MutidayTotal;
import com.mtpt.alibean.page.PublicPage;
import com.mtpt.alidao.MutidayTotalMapper;
import com.mtpt.aliservice.IMutidayTotalService;

@Service("mutidaytotalService")
public class MutidayTotalService implements IMutidayTotalService{

	@Autowired
	private MutidayTotalMapper mapper;

	@Override
	public JSONObject selectAllMutidayDataByPage(PublicPage page) {
		// TODO Auto-generated method stub
		int records = mapper.selectAllDataCount();
		page.setTotalRecord(records);
		List<MutidayTotal> list = mapper.selectAllDataByPage(page);
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
		return json;
	}

	@Override
	public JSONObject selectAllMutidayDataTotal() {
		// TODO Auto-generated method stub
		List<MutidayTotal> list = mapper.selectAllData();
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
		return json;
	}
	
	

}
