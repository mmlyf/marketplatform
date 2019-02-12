package com.mtpt.extend;

import org.apache.log4j.Logger;

import com.mtpt.bean.enumerate.DataTotalType;

import sun.util.logging.resources.logging_fr;

public class TimeTaskMethod {
	private static Logger log = Logger.getLogger(TimeTaskMethod.class);
	public static String totalCountByType(DataTotalType type,String time) {
		String result = "";
		switch (type) {
		case MO:
			result = "统计上行数据	";
			log.info("统计上行数据");
			break;
		case ORDER:
			result = "统计订单数据";
			log.info("统计订单数据");
			break;
		case MT:
			result = "统计下行数据";
			log.info("统计下行数据");
		default:
			break;
		}
		return result;
	}
	
}
