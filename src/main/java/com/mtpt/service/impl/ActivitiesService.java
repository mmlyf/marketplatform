package com.mtpt.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtpt.bean.ActivityList;
import com.mtpt.bean.page.ActivityPage;
import com.mtpt.dao.ActivityListMapper;
import com.mtpt.service.IActivitiesService;

@Service("activityService")
public class ActivitiesService implements IActivitiesService{

	
	@Autowired
	private ActivityListMapper activitylistMapper;
	
	private SimpleDateFormat sdf = null;
	@Override
	public JSONObject selectUnEndActivityByPage(ActivityPage page) {
		// TODO Auto-generated method stub
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		page.setSectime(sdf.format(date));
		int totals = activitylistMapper.selectActiUnEndCount(page);
		page.setTotalRecord(totals);
		List<ActivityList> actlist = activitylistMapper.selectActiUnEndByPage(page);
		JSONObject jsonmap = new JSONObject();
		List<JSONObject> jsonlist = new ArrayList<>();
		
		for(ActivityList tbActivilist : actlist) {
			JSONObject map = new JSONObject();
			map.put("actiid", tbActivilist.getId());
			map.put("actname", tbActivilist.getActName());
			map.put("starttime", sdf.format(tbActivilist.getActStarttime()));
			map.put("endtime", sdf.format(tbActivilist.getActEndtime()));
			map.put("addtime", sdf.format(tbActivilist.getAddtime()));
			map.put("actpage", tbActivilist.getActPage());
			jsonlist.add(map);
		}
		jsonmap.put("code", 0);
		jsonmap.put("msg", "");
		jsonmap.put("count", totals);
		jsonmap.put("data", jsonlist);
		return jsonmap;
	}

	@Override
	public JSONObject selectEndActivityByPage(ActivityPage page) {
		// TODO Auto-generated method stub
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		page.setSectime(sdf.format(date));
		int totals = activitylistMapper.selectActiEndCount(page);
		page.setTotalRecord(totals);
		List<ActivityList> actlist = activitylistMapper.selectActiEndByPage(page);
		JSONObject jsonmap = new JSONObject();
		List<JSONObject> jsonlist = new ArrayList<>();
		for(ActivityList tbActivilist : actlist) {
			JSONObject map = new JSONObject();
			map.put("actiid", tbActivilist.getId());
			map.put("actname", tbActivilist.getActName());
			map.put("starttime", sdf.format(tbActivilist.getActStarttime()));
			map.put("endtime", sdf.format(tbActivilist.getActEndtime()));
			map.put("addtime", sdf.format(tbActivilist.getAddtime()));
			map.put("actpage", tbActivilist.getActPage());
			jsonlist.add(map);
		}
		jsonmap.put("code", 0);
		jsonmap.put("msg", "");
		jsonmap.put("count", totals);
		jsonmap.put("data", jsonlist);
		return jsonmap;
	}

	@Override
	public JSONObject insertActivityInfo(ActivityList activityList) {
		// TODO Auto-generated method stub
		int result = activitylistMapper.insertSelective(activityList);
		JSONObject json = new JSONObject();
		if(result>0) {
			json.put("code", 0);
		}else {
			json.put("code", 1);
		}
		return json;
	}

	@Override
	public JSONObject updateActivityInfoById(ActivityList activityList) {
		// TODO Auto-generated method stub
		int upres = activitylistMapper.updateByPrimaryKeySelective(activityList);
		JSONObject json = new JSONObject();
		if (upres>0) {
			json.put("code", 0);
		}else {
			json.put("code", 1);
		}
		return json;
	}

}
