package com.mtpt.service;

import org.json.JSONObject;

public interface IRequestDataForFormDataService {
	JSONObject selectProdData(Integer prodid,Integer prodlxid);
	
	JSONObject selectMessageData();
	
	
}
