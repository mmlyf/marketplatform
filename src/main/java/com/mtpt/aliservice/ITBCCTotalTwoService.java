package com.mtpt.aliservice;

import java.util.List;

import com.mtpt.alibean.TBCCTotalTwo;
import com.mtpt.alibean.page.PublicPage;

public interface ITBCCTotalTwoService {
	int deleteByPrimaryKey(Integer id);

    int insert(TBCCTotalTwo record);

    int insertSelective(TBCCTotalTwo record);

    TBCCTotalTwo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TBCCTotalTwo record);

    int updateByPrimaryKey(TBCCTotalTwo record);
    
    List<TBCCTotalTwo> selectAllDataByPage(PublicPage page);
    
    List<TBCCTotalTwo> selectAllData();
    
    Integer selectAllDataCount(PublicPage page);
}
