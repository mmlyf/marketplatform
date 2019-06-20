package com.mtpt.timework;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mtpt.alibean.TBRecord;
import com.mtpt.alibean.TBReview;
import com.mtpt.aliservice.ITBRecordService;
import com.mtpt.aliservice.ITBReviewService;
import com.mtpt.bean.enumerate.SendMailType;
import com.mtpt.extend.SendMail;
import com.mtpt.methodforsend.SendFileIn;
import com.mtpt.methodforsend.SendModelIn;

//@Component("startwork")
public class StartWorkTimeTask {
	private Logger log = Logger.getLogger(StartWorkTimeTask.class);
	
	@Resource 
	ITBReviewService reviewService;
	@Resource 
	ITBRecordService recordService;
	/**
	 * 定时重启短信程序，并进行续发
	 */
	public void startSendMessage() {
		log.debug("重启定时任务！");
		SendFileIn.isStop = false;
		for(int id : SendFileIn.waitsend) {
			TBRecord tbRecord = new TBRecord();
			tbRecord.setId(id);
			if (id==SendFileIn.getTaskid()) {
				tbRecord.setState(3);
			}else {
				tbRecord.setState(5);
			}
			recordService.updateByPrimaryKeySelective(tbRecord);
		}
		SendModelIn.isStop = true;
		for (int id :SendModelIn.waitsend) {
			TBReview tbReview = new TBReview();
			tbReview.setId(id);
			if (id==SendModelIn.getRe_id()) {
				tbReview.setState(3);
			}else {
				tbReview.setState(5);
			}
			reviewService.updateByPrimaryKeySelective(tbReview);
		}
		SendMail.sendMailForCommon("短信下发续发成功", "", SendMailType.DEVELOP);
	}
}
