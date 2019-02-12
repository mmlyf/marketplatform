package com.mtpt.extend;

import java.awt.print.Pageable;
import java.util.HashMap;
import java.util.Map;

public class ProductNameType {
	
	private static Map<Integer, String> vagebondizeType = new HashMap<Integer, String>();
	private static Map<Integer, String> packageType = new HashMap<Integer, String>();
	private static Map<Integer, String> effectType = new HashMap<Integer, String>();
	private static Map<Integer, String> lsource = new HashMap<Integer, String>();
	static {
		vagebondizeType.put(1, "市内");
		vagebondizeType.put(2, "省内");
		vagebondizeType.put(3, "国内");
		packageType.put(10, "月包");
		packageType.put(11, "特惠月包");
		packageType.put(20, "闲时包");
		packageType.put(30, "组合月包");
		packageType.put(40, "加油包");
		packageType.put(50, "日包");
		packageType.put(60, "节日包");
		packageType.put(70, "半年包");
		effectType.put(1, "立即生效");
		effectType.put(2, "次月生效");
		lsource.put(1, "上行");
		lsource.put(2, "支付宝");
		lsource.put(3, "连接");
		lsource.put(4, "补订");
		lsource.put(5, "退订");
	}
	public static String getName(Integer vagaType,Integer packType,Integer effType) {
		String result="";
		result = vagebondizeType.get(vagaType)+"-"+packageType.get(packType)+"-"+effectType.get(effType);
		return result;
	}
	
	public static String getSource(Integer key) {
		return lsource.get(key);
	}

	
}
