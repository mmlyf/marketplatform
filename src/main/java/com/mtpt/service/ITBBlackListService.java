package com.mtpt.service;

import java.util.List;

import com.mtpt.bean.TBBlackList;
import com.mtpt.bean.page.BlackPage;

public interface ITBBlackListService {
	 int deleteByPrimaryKey(Integer id);

	    int insert(TBBlackList record);

	    int insertSelective(TBBlackList record);

	    TBBlackList selectByPrimaryKey(Integer id);

	    int updateByPrimaryKeySelective(TBBlackList record);

	    int updateByPrimaryKey(TBBlackList record);
	    
	    List<TBBlackList> selectByAll();
	    
	    List<TBBlackList> selectByBlackPage(BlackPage page);
	    
	    Integer selectByCount(BlackPage page);
	    
	    int insertByList(List<String> list);
}
