package com.mtpt.service;

import java.util.List;

import com.mtpt.bean.TBHfcs;

public interface ITBHfcsService {
	 int deleteByPrimaryKey(Integer hfId);

	    int insert(TBHfcs record);

	    int insertSelective(TBHfcs record);

	    TBHfcs selectByPrimaryKey(Integer hfId);

	    int updateByPrimaryKeySelective(TBHfcs record);

	    int updateByPrimaryKey(TBHfcs record);
	    
	    List<TBHfcs> selectByAll();
}
