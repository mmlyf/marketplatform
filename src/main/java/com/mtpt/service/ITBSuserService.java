package com.mtpt.service;

import java.util.List;
import java.util.Map;

import com.mtpt.bean.TBSuser;

public interface ITBSuserService {
	int deleteByPrimaryKey(Integer id);

	int insert(TBSuser record);

	int insertSelective(TBSuser record);

	TBSuser selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(TBSuser record);

	int updateByPrimaryKey(TBSuser record);

	TBSuser selectByNamePaw(Map<String, String> map);
	
	List<TBSuser> selectSuserAllData();
}
