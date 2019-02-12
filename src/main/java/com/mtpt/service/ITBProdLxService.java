package com.mtpt.service;

import java.util.List;

import com.mtpt.bean.TBProdLx;

public interface ITBProdLxService {
	int deleteByPrimaryKey(Integer lxid);

    int insert(TBProdLx record);

    int insertSelective(TBProdLx record);

    TBProdLx selectByPrimaryKey(Integer lxid);

    int updateByPrimaryKeySelective(TBProdLx record);

    int updateByPrimaryKey(TBProdLx record);
    
    List<TBProdLx> selectByAll();
}
