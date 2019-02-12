package com.mtpt.aliservice;

import java.util.List;

import com.mtpt.alibean.TBEquityData;
import com.mtpt.alibean.page.EquityDataPage;

public interface ITBEquityDataService {
	int deleteByPrimaryKey(Integer id);

    int insert(TBEquityData record);

    int insertSelective(TBEquityData record);

    TBEquityData selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TBEquityData record);

    int updateByPrimaryKey(TBEquityData record);
    
    List<TBEquityData> selectDataByAddtime(String sectime);
}
