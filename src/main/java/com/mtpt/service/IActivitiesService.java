package com.mtpt.service;

import org.json.JSONObject;

import com.mtpt.bean.ActivityList;
import com.mtpt.bean.page.ActivityPage;

public interface IActivitiesService {
	JSONObject selectUnEndActivityByPage(ActivityPage page);
	
	JSONObject selectEndActivityByPage(ActivityPage page);
	
	JSONObject insertActivityInfo(ActivityList activityList);
	
	JSONObject updateActivityInfoById(ActivityList activityList);
}
