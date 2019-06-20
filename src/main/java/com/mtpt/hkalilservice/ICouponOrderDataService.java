package com.mtpt.hkalilservice;

import org.json.JSONObject;

import com.mtpt.alibean.page.PublicPage;

public interface ICouponOrderDataService {
	JSONObject selectAllCouponDataByPage(PublicPage page);
}
