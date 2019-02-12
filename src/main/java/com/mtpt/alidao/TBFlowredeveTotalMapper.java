package com.mtpt.alidao;

import java.util.List;

import com.mtpt.alibean.TBFlowredeveTotal;
import com.mtpt.alibean.page.PublicPage;

/**
 * 流量红包
 * @author lvgordon
 *
 */
public interface TBFlowredeveTotalMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TBFlowredeveTotal record);

    int insertSelective(TBFlowredeveTotal record);

    TBFlowredeveTotal selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TBFlowredeveTotal record);

    int updateByPrimaryKey(TBFlowredeveTotal record);
    
    List<TBFlowredeveTotal> selectByPublicPage(PublicPage page);
    
    Integer selectAllDataCount();
    
    List<TBFlowredeveTotal> selectAllData();
}