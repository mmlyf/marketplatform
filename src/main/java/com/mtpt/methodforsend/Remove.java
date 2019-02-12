package com.mtpt.methodforsend;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import javax.annotation.Resource;

import com.mtpt.alibean.TBRecord;
import com.mtpt.bean.RepeatOpera;
import com.mtpt.bean.TBBlackList;
import com.mtpt.config.SpringContextUtil;
import com.mtpt.service.IProduceService;
import com.mtpt.service.ITBBlackListService;
import com.mtpt.service.impl.ProduceService;
import com.mtpt.service.impl.TBBlackListService;

public class Remove {
	
	static TBBlackListService blackListService = (TBBlackListService) SpringContextUtil.getBean("balckservice");
	static ProduceService pService = (ProduceService) SpringContextUtil.getBean("pservice");
	
	public static List<String> blackList(List<String> phonelist) {
		List<TBBlackList> list = blackListService.selectByAll();
		List<String> blklist = new ArrayList<String>();
		for(TBBlackList tbBlackList:list) {
			blklist.add(tbBlackList.getDn());
		}
		phonelist.removeAll(blklist);
		return phonelist;
	}
	
	public static List<String> threeDayList(List<String> phonelist,RepeatOpera repeatOpera){
		List<String> threeday = pService.selectThreeday(repeatOpera);
		phonelist.removeAll(threeday);
		return phonelist;
	}
}
