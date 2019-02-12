package com.mtpt.methodforsend;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.mtpt.alibean.TBCBRecord;
import com.mtpt.aliservice.impl.TBCbRecordService;
import com.mtpt.config.SpringContextUtil;

public class DataInDB {
	private static Logger log = Logger.getLogger(DataInDB.class);
	private static TBCbRecordService tbcbService = (TBCbRecordService) SpringContextUtil.getBean("tbcbrecordservice");
	
	public static boolean sendPhoneInDB(List<String> phonelist,int flag,int gpid) {
		List<TBCBRecord> list = new ArrayList<>();
		Date date = new Date();
		for(String phonestr:phonelist) {
			TBCBRecord tbcbRecord = new TBCBRecord();
			tbcbRecord.setCdTell(phonestr);
			tbcbRecord.setRdId(gpid);
			tbcbRecord.setCdSendstat(1);
			tbcbRecord.setSendflag(flag);
			tbcbRecord.setCdSendtime(date);
			list.add(tbcbRecord);
		}
		int result = tbcbService.insertByList(list);
		if (result>0) {
			return true;
		}else {
			return false;
		}
	}
 }
