package com.mtpt.alidao;

import java.util.List;

import com.mtpt.alibean.TBOutCallCCount;
import com.mtpt.alibean.page.PublicPage;

public interface TBOutCallCCountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TBOutCallCCount record);

    int insertSelective(TBOutCallCCount record);

    TBOutCallCCount selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TBOutCallCCount record);

    int updateByPrimaryKey(TBOutCallCCount record);
    
    List<TBOutCallCCount> selectAllDataByPage(PublicPage	page);
    
    Integer selectAllDataCountByPage();
}