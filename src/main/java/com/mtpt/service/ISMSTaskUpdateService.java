package com.mtpt.service;

import org.json.JSONObject;

import com.mtpt.alibean.TBRecord;
import com.mtpt.alibean.TBReview;
import com.mtpt.alibean.page.TBRecordPage;
import com.mtpt.bean.Review;

public interface ISMSTaskUpdateService {
	JSONObject getFileInData(TBRecordPage tbRecordPage);
	
	JSONObject getModelInData(TBRecordPage tbRecordPage);
	
	JSONObject deleteFileTask(int taskid);
	
	JSONObject deleteModelTask(int taskid);
	
	JSONObject updateStateFileTask(TBRecord tbRecord);
	
	JSONObject updateStateModel(TBReview tbReview);
	
	JSONObject updateSmsTaskFileIn(TBRecord tbRecord);
	
	JSONObject exportModelData(Review review);
	
	
}
