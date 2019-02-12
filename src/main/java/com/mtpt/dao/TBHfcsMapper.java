package com.mtpt.dao;

import java.util.List;

import com.mtpt.bean.TBHfcs;

public interface TBHfcsMapper {
    int deleteByPrimaryKey(Integer hfId);

    int insert(TBHfcs record);

    int insertSelective(TBHfcs record);

    TBHfcs selectByPrimaryKey(Integer hfId);

    int updateByPrimaryKeySelective(TBHfcs record);

    int updateByPrimaryKey(TBHfcs record);
    
    List<TBHfcs> selectByAll();
}