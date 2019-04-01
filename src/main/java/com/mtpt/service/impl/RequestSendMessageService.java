package com.mtpt.service.impl;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtpt.alibean.TBRecord;
import com.mtpt.alibean.TBReview;
import com.mtpt.alidao.TBRecordMapper;
import com.mtpt.alidao.TBReviewMapper;
import com.mtpt.dao.TBDsjDxAllMapper;
import com.mtpt.dao.TBDsjIceAllMapper;
import com.mtpt.methodforsend.SendFileIn;
import com.mtpt.methodforsend.SendModelIn;
import com.mtpt.methodforsend.SendTimeWorkFileIn;
import com.mtpt.service.IRequestSendMessageService;

@Service("requestSendMessageService")
public class RequestSendMessageService implements IRequestSendMessageService{

	private Logger log = Logger.getLogger(RequestSendMessageService.class);
	@Autowired
	private TBDsjDxAllMapper dsjdxallMapper;
	@Autowired
	private TBDsjIceAllMapper dsjiceallMapper;
	@Autowired
	private TBReviewMapper tbreviewMapper;
	@Autowired
	private TBRecordMapper tbrecordMapper;

	/**
	 * 发送文件上传的短信任务
	 */
	@Override
	public void sendMessageByFile(Integer taskid, String worker) {
		// TODO Auto-generated method stub
		TBRecord tbrecord = tbrecordMapper.selectByPrimaryKey(taskid);
		if(tbrecord.getIstimework().equals("是")) {
			log.debug("当前为定时任务");
			TBRecord timerecord = new TBRecord();
			timerecord.setState(101);
			timerecord.setId(taskid);
			timerecord.setLastwork(worker);
			tbrecordMapper.updateByPrimaryKeySelective(timerecord);
			SendTimeWorkFileIn.addFileTaskId(taskid);			
		}else {
			SendFileIn.waitsend.add(taskid);
			TBRecord tbRecord = new TBRecord();
			tbRecord.setState(5);
			tbRecord.setId(taskid);
			tbRecord.setLastwork(worker);
			tbrecordMapper.updateByPrimaryKeySelective(tbRecord);
			SendFileIn.addTaskid(taskid);
		}
	}

	/**
	 * 发送维度筛选的短信任务
	 */
	@Override
	public void sendMessageByModel(Integer id) {
		// TODO Auto-generated method stub
		TBReview tbreview = tbreviewMapper.selectByPrimaryKey(id);
		if(tbreview.getIsitmework().equals("是")) {
			TBReview timereview = new TBReview();
			timereview.setId(id);
			timereview.setState(101);
			tbreviewMapper.updateByPrimaryKeySelective(timereview);
			SendTimeWorkFileIn.addModelTaskId(id);
		}else {
			SendModelIn.waitsend.add(id);
			TBReview tbReview = new TBReview();
			tbReview.setId(id);
			tbReview.setState(5);
			tbreviewMapper.updateByPrimaryKeySelective(tbReview);
			SendModelIn.addRe_id(id);
		}
	}

	/**
	 * 暂停文件上传短信发送任务
	 */
	@Override
	public JSONObject stopMessageByFile() {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		if(SendFileIn.waitsend.isEmpty()) {
			json.put("code", 0);
		}else {
			SendFileIn.isStop = true;
			json.put("code", 1);
			for(int id: SendFileIn.waitsend) {
				TBRecord tbRecord = new TBRecord();
				tbRecord.setId(id);
				tbRecord.setState(6);
				tbrecordMapper.updateByPrimaryKeySelective(tbRecord);
			}
		}
		return json;
	}

	/**
	 * 暂停维度筛选短信发送任务
	 */
	@Override
	public JSONObject stopMessageByModel() {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		if(SendModelIn.waitsend.isEmpty()) {
			json.put("code", 0);
		}else {
			SendModelIn.isStop = true;
			json.put("code", 1);
			for(int id: SendModelIn.waitsend) {
				TBReview tbReview = new TBReview();
				tbReview.setId(id);
				tbReview.setState(6);
				tbreviewMapper.updateByPrimaryKeySelective(tbReview);
			}
		}
		return json;
	}

	/**
	 * 文件上传短信任务续发
	 */
	@Override
	public void startMessageByFile() {
		// TODO Auto-generated method stub
		SendFileIn.isStop = false;
		for(int id:SendFileIn.waitsend) {
			if(id==SendFileIn.getTaskid()) {
				TBRecord tbRecord = new TBRecord();
				tbRecord.setId(id);
				tbRecord.setState(3);
				tbrecordMapper.updateByPrimaryKeySelective(tbRecord);
			}else {
				TBRecord tbRecord = new TBRecord();
				tbRecord.setId(id);
				tbRecord.setState(5);
				tbrecordMapper.updateByPrimaryKeySelective(tbRecord);
			}
		}
	}

	/**
	 * 模型筛选续发
	 */
	@Override
	public void startMessageByModel() {
		// TODO Auto-generated method stub
		SendModelIn.isStop = false;
		for(int id:SendModelIn.waitsend) {
			if(id==SendModelIn.getRe_id()) {
				TBReview tbReview = new TBReview();
				tbReview.setId(id);
				tbReview.setState(3);
				tbreviewMapper.updateByPrimaryKeySelective(tbReview);
			}else {
				TBReview tbReview = new TBReview();
				tbReview.setId(id);
				tbReview.setState(5);
				tbreviewMapper.updateByPrimaryKeySelective(tbReview);
			}
		}
	}

	@Override
	public JSONObject endMessageByFile() {
		// TODO Auto-generated method stub
		log.debug("当前点击了结束任务");
		JSONObject json = new JSONObject();
		if(SendFileIn.waitsend.isEmpty()) {
			log.info("当前无群发任务");
			json.put("code", 0);
		}else {
			SendFileIn.isEnd = true;
			json.put("code", 1);
		}
		return json;
	}

	@Override
	public JSONObject endMessageByModel() {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		if(SendModelIn.waitsend.isEmpty()) {
			json.put("code", 0);
			log.info("当前无群发任务");
		}else {
			SendModelIn.isEnd = true;
			json.put("code", 1);
		}
		return json;
	}

	@Override
	public JSONObject stateBySendMessageFile() {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		if(SendFileIn.waitsend.isEmpty()) {
			json.put("state", 0);//当前暂无发送
		}else {
			if (!SendFileIn.isStop) {
				json.put("state", 1);//显示暂停
			}else {
				json.put("state", 2);//显示续发
			}
		}
		return json;
	}

	@Override
	public JSONObject stateBySendMessageModel() {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		if(SendModelIn.waitsend.isEmpty()) {
			json.put("state", 0);//当前暂无发送
		}else {
			if (!SendModelIn.isStop) {
				json.put("state", 1);//显示暂停
			}else {
				json.put("state", 2);//显示续发
			}
		}
		return json;
	}

}
