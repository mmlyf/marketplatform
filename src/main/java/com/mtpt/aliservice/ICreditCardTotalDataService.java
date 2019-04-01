package com.mtpt.aliservice;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.json.JSONObject;

import com.mtpt.alibean.page.PublicPage;

public interface ICreditCardTotalDataService {
	/**
	 * 信用卡办理活动V1.0数据
	 * @param page
	 * @return
	 */
	//分页查询信用卡活动V1.0的数据
	JSONObject selectVoneCreditCardTotalData(PublicPage page);
	
	//获取信用卡活动V1.0的统计数据总和
	JSONObject selectVoneCreditCardSumTotal();
	
	/**
	 * 查询信用卡活动V2.0数据
	 * @param page
	 * @return
	 */
	//分页查询信用卡活动V2.0统计数据
	JSONObject selectVtwoCreditCardTotalData(PublicPage page);
	
	//获取信用卡活动V2.0的统计数据总和
	JSONObject selectVtwoCreditCardSumTotal();
}
