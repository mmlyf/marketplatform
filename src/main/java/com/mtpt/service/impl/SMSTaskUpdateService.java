package com.mtpt.service.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtpt.alibean.TBRecord;
import com.mtpt.alibean.TBReview;
import com.mtpt.alibean.page.TBRecordPage;
import com.mtpt.alidao.TBRecordMapper;
import com.mtpt.alidao.TBReviewMapper;
import com.mtpt.bean.Review;
import com.mtpt.bean.TBMssage;
import com.mtpt.bean.TBProdLx;
import com.mtpt.bean.TBState;
import com.mtpt.dao.TBMssageMapper;
import com.mtpt.dao.TBProdLxMapper;
import com.mtpt.dao.TBProdMapper;
import com.mtpt.dao.TBStateMapper;
import com.mtpt.extend.OutputFile;
import com.mtpt.service.ISMSTaskUpdateService;

@Service("smsTaskUpdateService")
public class SMSTaskUpdateService implements ISMSTaskUpdateService{
	private Logger log = Logger.getLogger(SMSTaskUpdateService.class);

	@Autowired
	private TBRecordMapper tbrecordMapper;
	@Autowired
	private TBReviewMapper reviewMapper;
	@Autowired
	private TBStateMapper stateMapper;
	@Autowired 
	private TBProdLxMapper prodlxMapper;
	@Autowired
	private TBMssageMapper mssageMapper;
	
	SimpleDateFormat sdf = null;
	@Override
	public JSONObject getFileInData(TBRecordPage tbRecordPage) {
		// TODO Auto-generated method stub
		log.debug("当前查询的Word和type是："+tbRecordPage.getKeytype()+"/"+tbRecordPage.getKeyword());
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		JSONObject jsonmap = new JSONObject();
		List<JSONObject> jsonlist = new ArrayList<JSONObject>();
		int totals = tbrecordMapper.selectAllCount(tbRecordPage);
		tbRecordPage.setTotalPage(totals);
		List<TBRecord> list = tbrecordMapper.selectByRecordPage(tbRecordPage);
		for(TBRecord tbRecord:list) {
			JSONObject map = new JSONObject();
			map.put("id", tbRecord.getId());
			map.put("groupname", tbRecord.getGroupname());
			map.put("filename", tbRecord.getFilename());
			map.put("filenum", tbRecord.getFilenum());
			map.put("addman", tbRecord.getAddman());
			map.put("addtime", sdf.format(tbRecord.getAddtime()));
			map.put("lastwork", tbRecord.getLastwork());
			if (tbRecord.getEndtime()!=null) {
				map.put("endtime", sdf.format(tbRecord.getEndtime()));
			}else {
				map.put("endtime", "");
			}
			map.put("istimework", tbRecord.getIstimework());
			if (tbRecord.getWorktime()!=null) {
				map.put("worktime", sdf.format(tbRecord.getWorktime()));
			}else {
				map.put("worktime", "");
			}
			if (tbRecord.getMigId()!=null) {
				TBMssage tbMssage = mssageMapper.selectByPrimaryKey(tbRecord.getMigId());
				if (tbMssage==null) {
					map.put("msgtitle", "");
					map.put("msgcontent", "");
				}else {
					map.put("msgtitle", tbMssage.getMisTitle()!=null?tbMssage.getMisTitle():"");
					map.put("msgcontent", tbMssage.getMisContent()!=null?tbMssage.getMisContent():"");
				}	
			}else {
				map.put("msgtitle", "");
				map.put("msgcontent", "");
			}
			map.put("isdelblack", tbRecord.getIsdelblack());
			map.put("isdeldays", tbRecord.getIsdeldays());
			map.put("deldays", tbRecord.getDeldays());
			map.put("reviewman", tbRecord.getReviewman());
			TBState tbState = stateMapper.selectByState(tbRecord.getState());
			map.put("state", tbState.getStatename());
			jsonlist.add(map);
		}
		jsonmap.put("code", 0);
		jsonmap.put("msg", "");
		jsonmap.put("count",totals);
		jsonmap.put("data", jsonlist);
		return jsonmap;
	}

	@Override
	public JSONObject getModelInData(TBRecordPage tbRecordPage) {
		// TODO Auto-generated method stub
		List<TBReview> list = reviewMapper.selectByReviewPage(tbRecordPage);
		int totals = reviewMapper.selectCountAll(tbRecordPage);
		JSONObject jsonmap = new JSONObject();
		List<JSONObject> jsonlist = new ArrayList<JSONObject>();
		tbRecordPage.setTotalRecord(totals);
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (TBReview tbReview:list) {
			JSONObject map = new JSONObject();
			map.put("id", tbReview.getId());
			map.put("city",tbReview.getCity());
			map.put("source_type", tbReview.getSourceType());
			map.put("product", tbReview.getProduct());
			map.put("dangw", tbReview.getDangw());
			map.put("ifrh", tbReview.getIfrh());
			map.put("ifdx", tbReview.getIfdx());
			map.put("istimework", tbReview.getIsitmework());
			if(tbReview.getIsitmework().equals("是")) {
				String worktimestr = sdf.format(tbReview.getWorktime());
				map.put("worktime", worktimestr);
			}else {
				map.put("worktime", "");
			}
			map.put("isdelblack", tbReview.getIsdelblack());
			map.put("isdeldays", tbReview.getIsdeldays());
			map.put("deldays", tbReview.getDeldays());
			map.put("count", tbReview.getCount());
			TBState tbState = stateMapper.selectByState(tbReview.getState());
			map.put("state", tbState.getStatename());
			map.put("rduser", tbReview.getRdUser());
			if (tbReview.getSecTime()!=null) {
				sdf = new SimpleDateFormat("yyyy-MM-dd");
				String secstr  = sdf.format(tbReview.getSecTime());
				map.put("sectime", secstr);
			}else {
				map.put("sectime	", "");
			}
			if (tbReview.getAddTime()!=null) {
				sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String addtimestr = sdf.format(tbReview.getAddTime());
				map.put("createtime", addtimestr);
			}else {
				map.put("createtime", "");
			}
			if (tbReview.getMigId()!=null) {
				TBMssage tbMssage = mssageMapper.selectByPrimaryKey(tbReview.getMigId());
				map.put("msgtitle", tbMssage.getMisTitle());
				map.put("msgcontent", tbMssage.getMisContent());
			}else {
				map.put("msgtitle", "");
				map.put("msgcontent", "");
			}
			map.put("reuser", tbReview.getReUser());
			jsonlist.add(map);
		}
		jsonmap.put("code", 0);
		jsonmap.put("msg", "");
		jsonmap.put("count",totals);
		jsonmap.put("data", jsonlist);
		return jsonmap;
	}

	@Override
	public JSONObject deleteFileTask(int taskid) {
		// TODO Auto-generated method stub
		int result = tbrecordMapper.deleteByPrimaryKey(taskid);
		JSONObject json = new JSONObject();
		if (result>0) {
			json.put("code", 1);
		}else {
			json.put("code", 0);
		}
		return json;
	}

	@Override
	public JSONObject deleteModelTask(int taskid) {
		// TODO Auto-generated method stub
		int result = reviewMapper.deleteByPrimaryKey(taskid);
		JSONObject json = new JSONObject();
		if (result>0) {
			json.put("code", 1);
		}else {
			json.put("code", 0);
		}
		return json;
	}

	@Override
	public JSONObject updateStateFileTask(TBRecord tbRecord) {
		// TODO Auto-generated method stub
		int result = tbrecordMapper.updateByPrimaryKeySelective(tbRecord);
		JSONObject json = new JSONObject();
		if (result>0) {
			json.put("code", 1);
		}else {
			json.put("code", 0);
		}
		return json;
	}

	@Override
	public JSONObject updateStateModel(TBReview tbReview) {
		// TODO Auto-generated method stub
		int result = reviewMapper.updateByPrimaryKeySelective(tbReview);
		JSONObject json = new JSONObject();
		if(result>0) {
			json.put("code", 1);
		}else {
			json.put("code", 0);
		}
		return json;
	}

	@Override
	public JSONObject updateSmsTaskFileIn(TBRecord tbRecord) {
		// TODO Auto-generated method stub
		if (tbRecord.getIstimework()!=null&&tbRecord.getIstimework().equals("on")) {
			tbRecord.setIstimework("是");
		}else {
			tbRecord.setIstimework("否");
			tbRecord.setWorktime(null);
		}
		if (tbRecord.getIsdeldays()!=null&&tbRecord.getIsdelblack().equals("on")) {
			tbRecord.setIsdelblack("是");
		}else {
			tbRecord.setIsdelblack("否");

		}
		if (tbRecord.getIsdeldays()!=null&&tbRecord.getIsdeldays().equals("on")) {
			tbRecord.setIsdeldays("是");
		}else {
			tbRecord.setIsdeldays("否");
			tbRecord.setDeldays(null);
		}
		tbRecord.setState(0);
		int result = tbrecordMapper.updateByPrimaryKeySelective(tbRecord);
		JSONObject json = new JSONObject();
		if (result>0) {
			json.put("code", 1);
		}else {
			json.put("code",0);
		}
		return json;
	}

	@Override
	public JSONObject exportModelData(Review review) {
		// TODO Auto-generated method stub
		if(review.getPrelx()!=null) {
			System.out.println("prelx is :"+review.getPrelx());
			int lxid = Integer.parseInt(review.getPrelx());
			TBProdLx tbProdLx = prodlxMapper.selectByPrimaryKey(lxid);
			review.setPrelx(tbProdLx.getLxvalue());
		}
		JSONObject json = new JSONObject();
		String path = OutputFile.exportModelData(review);
		json.put("code", 0);
		json.put("filepath", path);
		return json;
	}

}
