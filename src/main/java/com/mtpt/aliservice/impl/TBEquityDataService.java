package com.mtpt.aliservice.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtpt.alibean.TBEquityData;
import com.mtpt.alibean.page.EquityDataPage;
import com.mtpt.alidao.TBEquityDataMapper;
import com.mtpt.aliservice.ITBEquityDataService;
@Service("equityDataService")
public class TBEquityDataService implements ITBEquityDataService{

	@Autowired
	private TBEquityDataMapper mapper;

	@Override
	public JSONObject selectEquityDataWithResult(EquityDataPage page) {
		// TODO Auto-generated method stub
		Integer records = mapper.selectDataAllCount(page);
		page.setTotalRecord(records);
		List<TBEquityData> list = mapper.selectEquityAndResultData(page);
		JSONObject json = new JSONObject();
		List<JSONObject> jsonlist = new ArrayList<>();
		int count = 1;
		for(TBEquityData tbEquityData:list) {
			JSONObject value = new JSONObject();
			value.put("id", count);
			value.put("dn", tbEquityData.getDn());
			value.put("qy_id", tbEquityData.getQyId());
			value.put("qy_name", tbEquityData.getQyName());
			if (tbEquityData.getSource()==1) {
				value.put("source", "抽奖	");
			}else if (tbEquityData.getSource()==3) {
				value.put("source", "流量红包");
			}
			value.put("zs_time", tbEquityData.getResultdata().getZsTime()!=null?tbEquityData.getResultdata().getZsTime():"");
			value.put("zs_state", tbEquityData.getResultdata().getZsState()!=null?tbEquityData.getResultdata().getZsState():"");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String addtime = sdf.format(tbEquityData.getAddtime());
			value.put("addtime", addtime);
			count++;
			jsonlist.add(value);
		}
		json.put("code", 0);
		json.put("msg", "");
		json.put("data", jsonlist);
		json.put("count", records);
		return json;
	}

	@Override
	public List<TBEquityData> selectDataByAddtime(String sectime) {
		// TODO Auto-generated method stub
		return mapper.selectDataByAddtime(sectime);
	}
	
	
}
