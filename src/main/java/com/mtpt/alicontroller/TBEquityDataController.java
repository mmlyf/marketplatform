package com.mtpt.alicontroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mtpt.alibean.TBEquityData;
import com.mtpt.alibean.page.EquityDataPage;
import com.mtpt.aliservice.ITBEquityDataService;

@Controller
@RequestMapping("/equitydata")
public class TBEquityDataController {
	
	@Resource
	private ITBEquityDataService equityDataService;
	
	/**
	 * 查询权益的结果值
	 * @param page
	 * @param response
	 */
	@RequestMapping(value="/selectdataresult",method= {RequestMethod.POST,RequestMethod.GET})
	public void selectEquityDataWithResult(EquityDataPage page,HttpServletResponse response) {
		Integer records = equityDataService.selectDataAllCount();
		page.setTotalRecord(records);
		List<TBEquityData> list = equityDataService.selectEquityAndResultData(page);
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
		try {
			PrintWriter pw = response.getWriter();
			pw.write(json.toString());
			pw.flush();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
