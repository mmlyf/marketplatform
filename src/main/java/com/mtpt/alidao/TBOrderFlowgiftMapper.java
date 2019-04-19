package com.mtpt.alidao;

import com.mtpt.alibean.TBOrderFlowgift;

public interface TBOrderFlowgiftMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TBOrderFlowgift record);

    int insertSelective(TBOrderFlowgift record);

    TBOrderFlowgift selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TBOrderFlowgift record);

    int updateByPrimaryKey(TBOrderFlowgift record);
}