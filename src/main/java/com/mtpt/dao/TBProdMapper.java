package com.mtpt.dao;

import java.util.List;

import com.mtpt.bean.TBProd;

public interface TBProdMapper {
	int deleteByPrimaryKey(Integer proid);

    int insert(TBProd record);

    int insertSelective(TBProd record);

    TBProd selectByPrimaryKey(Integer proid);

    int updateByPrimaryKeySelective(TBProd record);

    int updateByPrimaryKey(TBProd record);
    
    List<TBProd> selectByLxid(int lxid);
}