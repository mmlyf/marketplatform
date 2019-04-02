package com.mtpt.aliservice;

import org.json.JSONObject;

import com.mtpt.alibean.TBSceneJob;
import com.mtpt.alibean.page.SceneJobPage;

public interface ISceneMarketJobService {
	JSONObject insertSceneMarketJob(TBSceneJob tbSceneJob);
	
	JSONObject selectSceneJobData(SceneJobPage page);
	
	JSONObject updateSceneJobForState(TBSceneJob tbSceneJob);
	
	JSONObject stopSendSceneJob();
	
	void startSendSceneJob();
	
	JSONObject EndSendSceneJob();
	
	JSONObject deleteSendSceneJob(Integer jobid);
	
	JSONObject outputSceneJobData(TBSceneJob tbSceneJob);
	
	void sendMessageSceneJob(Integer jobid,String worker);
	
	
}
