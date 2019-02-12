package com.mtpt.alidao;

import java.util.List;

import com.mtpt.alibean.TBCCTotal;
import com.mtpt.alibean.page.PublicPage;

public interface TBCCTotalMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(TBCCTotal record);

	int insertSelective(TBCCTotal record);

	TBCCTotal selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(TBCCTotal record);

	int updateByPrimaryKey(TBCCTotal record);

	 List<TBCCTotal> selectAllDataByPage(PublicPage page);
	 
	 Integer selectAllDataCount(PublicPage page);
	 
	 List<TBCCTotal> selectAllData();
}