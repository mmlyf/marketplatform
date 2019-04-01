package com.mtpt.aliservice;

import java.util.List;

import org.json.JSONObject;

import com.mtpt.alibean.TBFlowredeveTotal;
import com.mtpt.alibean.page.PublicPage;

public interface ITBFlowredeveTotalService {

    JSONObject selectFlowRedPackageDataByPage(PublicPage page);
    
    JSONObject selectFlowRedPackageDataTotalCountByAll();
}
