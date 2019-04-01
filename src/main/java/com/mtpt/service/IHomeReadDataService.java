package com.mtpt.service;

import org.json.JSONObject;

import com.mtpt.bean.page.TotalPage;

public interface IHomeReadDataService {
	JSONObject selectTotalDataByPage(TotalPage page);
	
	JSONObject selectTotalDataDelayByPage(TotalPage page);
	
	JSONObject selectTotalDataForAMonth(String month);
	
	JSONObject selectTotalDataForWeek(Integer delay);
	
	JSONObject selectDataTotalForBrforeNow(String addtime);
}
