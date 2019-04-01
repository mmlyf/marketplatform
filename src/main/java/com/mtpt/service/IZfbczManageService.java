package com.mtpt.service;

import org.json.JSONObject;

import com.mtpt.bean.page.ZfbczPage;

public interface IZfbczManageService {
	JSONObject selectAllDataByPage(ZfbczPage page);
	
}
