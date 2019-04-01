package com.mtpt.service;

import org.json.JSONObject;


public interface IRequestSendMessageService {
	void sendMessageByFile(Integer taskid,String worker);
	
	void sendMessageByModel(Integer id);
	
	JSONObject stopMessageByFile();
	
	JSONObject stopMessageByModel();
	
	void startMessageByFile();
	
	void startMessageByModel();
	
	JSONObject endMessageByFile();
	
	JSONObject endMessageByModel();
	
	JSONObject stateBySendMessageFile();
	
	JSONObject stateBySendMessageModel();
}
