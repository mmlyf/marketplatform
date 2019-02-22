package com.mtpt.service;

import java.util.List;

import com.mtpt.alibean.page.TBRecordPage;
import com.mtpt.bean.ActivityList;
import com.mtpt.bean.page.ActivityPage;

public interface IActivityListService {
	int deleteByPrimaryKey(Integer id);

	int insert(ActivityList record);

	int insertSelective(ActivityList record);

	ActivityList selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(ActivityList record);

	int updateByPrimaryKey(ActivityList record);

	Integer selectActiUnEndCount(ActivityPage page);

	Integer selectActiEndCount(ActivityPage page);

	List<ActivityList> selectActiUnEndByPage(ActivityPage page);

	List<ActivityList> selectActiEndByPage(ActivityPage page);
	
	ActivityList selectActiInfoById(Integer id);
}
