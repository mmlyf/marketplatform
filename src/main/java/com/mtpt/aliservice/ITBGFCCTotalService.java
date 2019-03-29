package com.mtpt.aliservice;

import java.util.List;

import com.mtpt.alibean.TBGFCCTotal;
import com.mtpt.alibean.page.PublicPage;

public interface ITBGFCCTotalService {
	int deleteByPrimaryKey(Integer id);

    int insert(TBGFCCTotal record);

    int insertSelective(TBGFCCTotal record);

    TBGFCCTotal selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TBGFCCTotal record);

    int updateByPrimaryKey(TBGFCCTotal record);
    
    List<TBGFCCTotal> selectAllDataByPage(PublicPage page);
    
    Integer selectAllDataCount();
    
    List<TBGFCCTotal> selectMoreCaseLanAllDataByPage(PublicPage page);

	Integer selectMoreCaseLanAllDataCount();
	
	List<TBGFCCTotal> selectMoreCasePinAllDataByPage(PublicPage page);

	Integer selectMoreCasePinAllDataCount();
}
