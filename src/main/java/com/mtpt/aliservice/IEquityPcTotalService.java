package com.mtpt.aliservice;

import org.json.JSONObject;

import com.mtpt.alibean.page.PublicPage;

public interface IEquityPcTotalService {
	//根据page中所提供的参数查询所有的权益数据（进行分页查询）
	JSONObject selectAllEquityDataByPage(PublicPage page);
	
	//统计所有权益数据的总和并返回数据
	JSONObject selectAllEquityDataForTotal();
}
