package com.mtpt.service;

import org.json.JSONObject;

import com.mtpt.bean.TBMssage;
import com.mtpt.bean.page.TBMessagePage;

public interface IMessageManageService {
	JSONObject selectAllMessageDataByPage(TBMessagePage page);
	
	JSONObject updateMessageData(TBMssage tbMssage);
	
	JSONObject deleteMessageData(Integer msgid);
	
	JSONObject insertMessageData(TBMssage tbMssage);
	
	TBMssage selectByPrimaryKey(int id);
}
