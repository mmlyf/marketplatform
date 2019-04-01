package com.mtpt.aliservice;

import java.util.List;

import org.json.JSONObject;

import com.mtpt.alibean.TBHfczReview;
import com.mtpt.alibean.page.HfczReviewPage;

public interface ITBHfczReviewService {
	int insertSelective(TBHfczReview record);
	
	JSONObject selectHfczReviewDataByPage(HfczReviewPage page);
	
	JSONObject updateHfczReviewState(String reviewid,String state);
	
}
