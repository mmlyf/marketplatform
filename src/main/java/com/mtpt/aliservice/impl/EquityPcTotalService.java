package com.mtpt.aliservice.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtpt.alibean.TBEquityPcTotal;
import com.mtpt.alibean.TBEquityResult;
import com.mtpt.alibean.page.PublicPage;
import com.mtpt.alidao.TBEquityPcTotalMapper;
import com.mtpt.alidao.TBEquityResultMapper;
import com.mtpt.aliservice.IEquityPcTotalService;

@Service("equityPcTotalService")
public class EquityPcTotalService implements IEquityPcTotalService{

	@Autowired
	private TBEquityPcTotalMapper pctotalMapper;
	@Autowired
	private TBEquityResultMapper resultMapper;
	
	@Override
	public JSONObject selectAllEquityDataByPage(PublicPage page) {
		// TODO Auto-generated method stub
		Integer totals = pctotalMapper.selectAllCount(page);
		page.setTotalRecord(totals);
		List<TBEquityPcTotal> list = pctotalMapper.selectAllTotalData(page);
		JSONObject json = new JSONObject();
		List<JSONObject> jsonlist = new ArrayList<>();
		for(TBEquityPcTotal pctotal:list) {
			JSONObject map = new JSONObject();
			map.put("id", pctotal.getId());
			map.put("pvcount", pctotal.getPvCount());
			map.put("bccount", pctotal.getBcCount());
			map.put("youkucount", pctotal.getYoukuCount());
			map.put("aqiycount", pctotal.getAqiyCount());
			map.put("mgcount", pctotal.getMgCount());
			map.put("txcount", pctotal.getTxCount());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String adtime = sdf.format(pctotal.getAddtime());
			map.put("addtime", adtime);
			jsonlist.add(map);
		}
		json.put("code", 0);
		json.put("msg", "");
		json.put("count", totals);
		json.put("data", jsonlist);
		return json;
	}

	@Override
	public JSONObject selectAllEquityDataForTotal() {
		// TODO Auto-generated method stub
		List<TBEquityPcTotal> list = pctotalMapper.selectAllData();
		List<TBEquityResult> resultlist = resultMapper.selectAllData();
		int pvcounttotal = 0;
		int bccounttotal = 0;
		int ykcounttotal = 0;
		int aqycounttotal = 0;
		int mgcounttotal = 0;
		int txcounttotal = 0;
		for(TBEquityPcTotal tbEquityPcTotal:list) {
			pvcounttotal += tbEquityPcTotal.getPvCount();
			bccounttotal += tbEquityPcTotal.getBcCount();
			ykcounttotal += tbEquityPcTotal.getYoukuCount();
			aqycounttotal += tbEquityPcTotal.getAqiyCount();
			mgcounttotal += tbEquityPcTotal.getMgCount();
			txcounttotal += tbEquityPcTotal.getTxCount();
		}
		int ykresultcount = 0;
		int aqiresultcount = 0;
		int mgresultcount = 0;
		int txresultcount = 0;

		for(TBEquityResult tbEquityResult:resultlist) {
			String qynum = tbEquityResult.getQyNum()!=null?tbEquityResult.getQyNum():"";
			if (qynum.equals("")) {
				
			}else {
				switch (qynum) {
				case "1000000000104610"://芒果
					mgresultcount += 1;
					break;
				case "1000000000105003"://腾讯
					txresultcount += 1;
					break;
				case "1000000000105204"://爱奇艺
					aqiresultcount += 1;
					break;
				case "1000000000104609"://优酷
					ykresultcount += 1;
					break;
				default:
					break;
				}
			}
		}

		JSONObject value = new JSONObject();
		value.put("pvtotal", pvcounttotal);
		value.put("bctotal", bccounttotal);
		value.put("yktotal", ykcounttotal);
		value.put("aqytotal", aqycounttotal);
		value.put("mgtotal", mgcounttotal);
		value.put("txtotal", txcounttotal);
		value.put("mgresult", mgresultcount);
		value.put("txresult", txresultcount);
		value.put("aqyresult", aqiresultcount);
		value.put("ykresult", ykresultcount);
		return value;
	}

}
