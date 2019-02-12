package com.mtpt.alidao;

import java.util.List;

import com.mtpt.alibean.TBTehuiFlowTotal;
import com.mtpt.alibean.page.PublicPage;

public interface TBTehuiFlowTotalMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TBTehuiFlowTotal record);

    int insertSelective(TBTehuiFlowTotal record);

    TBTehuiFlowTotal selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TBTehuiFlowTotal record);

    int updateByPrimaryKey(TBTehuiFlowTotal record);
    
    List<TBTehuiFlowTotal> selectDataByPublicPage(PublicPage page);
    
    Integer	selectAllDataCount();
    
    List<TBTehuiFlowTotal> selectAllData();
}