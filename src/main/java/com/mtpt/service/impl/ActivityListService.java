package com.mtpt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtpt.alibean.page.TBRecordPage;
import com.mtpt.bean.ActivityList;
import com.mtpt.dao.ActivityListMapper;
import com.mtpt.service.IActivityListService;

@Service("activitylistService")
public class ActivityListService implements IActivityListService {

	@Autowired
	ActivityListMapper mapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(ActivityList record) {
		// TODO Auto-generated method stub
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(ActivityList record) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(record);
	}

	@Override
	public ActivityList selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(ActivityList record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(ActivityList record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public Integer selectActiCount(TBRecordPage page) {
		// TODO Auto-generated method stub
		return mapper.selectActiCount(page);
	}

	@Override
	public List<ActivityList> selectActiByPage(TBRecordPage page) {
		// TODO Auto-generated method stub
		return mapper.selectActiByPage(page);
	}

}
