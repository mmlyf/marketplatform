package com.mtpt.dao;

import java.util.List;

import com.mtpt.alibean.page.TBRecordPage;
import com.mtpt.bean.ActivityList;

public interface ActivityListMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityList record);

    int insertSelective(ActivityList record);

    ActivityList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivityList record);

    int updateByPrimaryKey(ActivityList record);
    
    int selectActiCount(TBRecordPage page);
    
    List<ActivityList> selectActiByPage(TBRecordPage page);
}