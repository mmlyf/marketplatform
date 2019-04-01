package com.mtpt.aliservice.impl;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtpt.alibean.TBFlowredeveTotal;
import com.mtpt.alibean.page.PublicPage;
import com.mtpt.alidao.TBFlowredeveTotalMapper;
import com.mtpt.aliservice.ITBFlowredeveTotalService;

@Service("tbflowredevetotalservice")
public class TBFlowredeveTotalService implements ITBFlowredeveTotalService{

	@Autowired
	private TBFlowredeveTotalMapper mapper;

	@Override
	public JSONObject selectFlowRedPackageDataByPage(PublicPage page) {
		// TODO Auto-generated method stub
		int records = mapper.selectAllDataCount();
		page.setTotalRecord(records);
		List<TBFlowredeveTotal> list = mapper.selectByPublicPage(page);
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
		return json;
	}

	@Override
	public JSONObject selectFlowRedPackageDataTotalCountByAll() {
		// TODO Auto-generated method stub
		List<TBFlowredeveTotal> list = mapper.selectAllData();
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
		return json;
	}
	
}
