package com.mtpt.service;

import org.apache.ibatis.annotations.Param;

import com.mtpt.bean.TBState;

public interface ITBStateService {
	int deleteByPrimaryKey(Integer id);

    int insert(TBState record);

    int insertSelective(TBState record);

    TBState selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TBState record);

    int updateByPrimaryKey(TBState record);
    
    TBState selectByState(@Param("state") int state);
}
