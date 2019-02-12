package com.mtpt.dao;

import java.util.List;

import com.mtpt.bean.TBProdLx;

public interface TBProdLxMapper {
	int deleteByPrimaryKey(Integer lxid);

    int insert(TBProdLx record);

    int insertSelective(TBProdLx record);

    TBProdLx selectByPrimaryKey(Integer lxid);

    int updateByPrimaryKeySelective(TBProdLx record);

    int updateByPrimaryKey(TBProdLx record);
    
    List<TBProdLx> selectByAll();
}