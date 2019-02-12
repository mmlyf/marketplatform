package com.mtpt.alidao;

import java.util.List;

import com.mtpt.alibean.TBEquityData;

public interface TBEquityDataMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TBEquityData record);

    int insertSelective(TBEquityData record);

    TBEquityData selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TBEquityData record);

    int updateByPrimaryKey(TBEquityData record);
    
    List<TBEquityData> selectDataByAddtime(String sectime);
}