package com.mtpt.alidao;

import java.util.List;

import com.mtpt.alibean.TBEquityPcTotal;
import com.mtpt.alibean.page.PublicPage;

public interface TBEquityPcTotalMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TBEquityPcTotal record);

    int insertSelective(TBEquityPcTotal record);

    TBEquityPcTotal selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TBEquityPcTotal record);

    int updateByPrimaryKey(TBEquityPcTotal record);
    
    List<TBEquityPcTotal> selectAllTotalData(PublicPage page);
    
    Integer selectAllCount(PublicPage page);
    
    List<TBEquityPcTotal> selectAllData();
}