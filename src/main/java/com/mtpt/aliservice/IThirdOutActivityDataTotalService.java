package com.mtpt.aliservice;

import org.json.JSONObject;

import com.mtpt.alibean.page.PublicPage;

public interface IThirdOutActivityDataTotalService {
	//低消放在第三方数据统计
	JSONObject selectDxThirdDataByPage(PublicPage page);
	
	//冰神卡第三方数据统计展示
	JSONObject selectIceGodDataByPage(PublicPage page);
	
	//冰淇淋第三方数据统计展示
	JSONObject selectIceThirdDataByPage(PublicPage page);
	
	JSONObject selectCouponDataByAll();
}
