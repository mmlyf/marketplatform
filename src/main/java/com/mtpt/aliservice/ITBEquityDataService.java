package com.mtpt.aliservice;

import java.util.List;

import org.json.JSONObject;

import com.mtpt.alibean.TBEquityData;
import com.mtpt.alibean.page.EquityDataPage;

public interface ITBEquityDataService {
    
    JSONObject selectEquityDataWithResult(EquityDataPage page);
    
    List<TBEquityData> selectDataByAddtime(String sectime);
}
