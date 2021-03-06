package com.mtpt.aliservice.impl;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtpt.alibean.TBTehuiFlowTotal;
import com.mtpt.alibean.page.PublicPage;
import com.mtpt.alidao.TBTehuiFlowTotalMapper;
import com.mtpt.aliservice.ITBTehuiFlowTotalService;

@Service("tehuiFlowTotalService")
public class TBTehuiFlowTotalService implements ITBTehuiFlowTotalService{

	@Autowired
	private TBTehuiFlowTotalMapper mapper;

	@Override
	public JSONObject selectDataByPublicPageForTable(PublicPage page) {
		// TODO Auto-generated method stub
		Integer recodes = mapper.selectAllDataCount();
		recodes = recodes!=null?recodes:0;
		page.setTotalRecord(recodes);
		List<TBTehuiFlowTotal> list = mapper.selectDataByPublicPage(page);
		JSONObject json = new JSONObject();
		List<JSONObject> jsonlist = new ArrayList<>();
		for(TBTehuiFlowTotal tehuiFlowTotal:list) {
			JSONObject value = new JSONObject();
			value.put("id", tehuiFlowTotal.getId());
			value.put("pv", tehuiFlowTotal.getPv());
			value.put("liji_bc", tehuiFlowTotal.getLijiBc());
			value.put("comfir_bc", tehuiFlowTotal.getComfirBc());
			value.put("tan_seebc", tehuiFlowTotal.getTanSeebc());
			value.put("3g_succount", tehuiFlowTotal.get_3gSuccount());
			value.put("8g_succount", tehuiFlowTotal.get_8gSuccount());
			value.put("12g_succount", tehuiFlowTotal.get_12gSuccount());
			value.put("25g_succount", tehuiFlowTotal.get_25gSuccount());
			value.put("40g_succount", tehuiFlowTotal.get_40gSuccount());
			value.put("3g_unsuccount", tehuiFlowTotal.get_3gUnsuccount());
			value.put("8g_unsuccount", tehuiFlowTotal.get_8gUnsuccount());
			value.put("12g_unsuccount", tehuiFlowTotal.get_12gUnsuccount());
			value.put("25g_unsuccount", tehuiFlowTotal.get_25gUnsuccount());
			value.put("40g_unsuccount", tehuiFlowTotal.get_40gUnsuccount());
			value.put("addtime", tehuiFlowTotal.getAddtime());
			jsonlist.add(value);
		}
		json.put("code", 0);
		json.put("msg", "");
		json.put("data", jsonlist);
		json.put("count", recodes);
		return json;
	}

	@Override
	public JSONObject selectDataTotal() {
		// TODO Auto-generated method stub
		List<TBTehuiFlowTotal> list = mapper.selectAllData();
		int pvtotal = 0;
		int lijibctotal = 0;
		int comfirbctotal = 0;
		int tanseebctotal = 0;
		int _3gsuccounttotal = 0;
		int _8gsuccounttotal = 0;
		int _12gsuccounttotal = 0;
		int _25gsuccounttotal = 0;
		int _40gsuccounttotal = 0;
		int _3gunsuccounttotal = 0;
		int _8gunsuccounttotal = 0;
		int _12gunsuccounttotal = 0;
		int _25gunsuccounttotal = 0;
		int _40gunsuccounttotal = 0;
		int allsuccounttotal = 0;
		int allunsuccounttotal = 0;
		for(TBTehuiFlowTotal tbTehuiFlowTotal:list) {
			pvtotal += tbTehuiFlowTotal.getPv();
			lijibctotal += tbTehuiFlowTotal.getLijiBc();
			comfirbctotal += tbTehuiFlowTotal.getComfirBc();
			tanseebctotal += tbTehuiFlowTotal.getTanSeebc();
			_3gsuccounttotal += tbTehuiFlowTotal.get_3gSuccount();
			_8gsuccounttotal += tbTehuiFlowTotal.get_8gSuccount();
			_12gsuccounttotal += tbTehuiFlowTotal.get_12gSuccount();
			_25gsuccounttotal += tbTehuiFlowTotal.get_25gSuccount();
			_40gsuccounttotal += tbTehuiFlowTotal.get_40gSuccount();
			_3gunsuccounttotal += tbTehuiFlowTotal.get_3gUnsuccount();
			_8gunsuccounttotal += tbTehuiFlowTotal.get_8gUnsuccount();
			_12gunsuccounttotal += tbTehuiFlowTotal.get_12gUnsuccount();
			_25gunsuccounttotal += tbTehuiFlowTotal.get_25gUnsuccount();
			_40gunsuccounttotal += tbTehuiFlowTotal.get_40gUnsuccount();
			allsuccounttotal += tbTehuiFlowTotal.get_3gSuccount()+tbTehuiFlowTotal.get_8gSuccount()+tbTehuiFlowTotal.get_12gSuccount()+tbTehuiFlowTotal.get_25gSuccount()+tbTehuiFlowTotal.get_40gSuccount();
			allunsuccounttotal +=  tbTehuiFlowTotal.get_3gUnsuccount()+tbTehuiFlowTotal.get_8gUnsuccount()+tbTehuiFlowTotal.get_12gUnsuccount()+tbTehuiFlowTotal.get_25gUnsuccount()+tbTehuiFlowTotal.get_40gUnsuccount();
		}
		JSONObject json = new JSONObject();
		json.put("pvtotal", pvtotal);
		json.put("lijibctotal", lijibctotal);
		json.put("comfirbctotal", comfirbctotal);
		json.put("tanseebctotal", tanseebctotal);
		json.put("_3gsuccounttotal", _3gsuccounttotal);
		json.put("_8gsuccounttotal", _8gsuccounttotal);
		json.put("_12gsuccounttotal", _12gsuccounttotal);
		json.put("_25gsuccounttotal", _25gsuccounttotal);
		json.put("_40gsuccounttotal", _40gsuccounttotal);
		json.put("_3gunsuccounttotal", _3gunsuccounttotal);
		json.put("_8gunsuccounttotal", _8gunsuccounttotal);
		json.put("_12gunsuccounttotal", _12gunsuccounttotal);
		json.put("_25gunsuccounttotal", _25gunsuccounttotal);
		json.put("_40gunsuccounttotal", _40gunsuccounttotal);
		json.put("allsuccounttotal", allsuccounttotal);
		json.put("allunsuccounttotal", allunsuccounttotal);
		return json;
	}
	
	

}
