package com.mtpt.alidao;

import java.util.List;

import com.mtpt.alibean.TBEquityResult;

public interface TBEquityResultMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TBEquityResult record);

    int insertSelective(TBEquityResult record);

    TBEquityResult selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TBEquityResult record);

    int updateByPrimaryKey(TBEquityResult record);
    
    List<TBEquityResult> selectAllData();
}