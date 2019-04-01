package com.mtpt.aliservice;

import java.util.List;

import org.json.JSONObject;

import com.mtpt.alibean.TBOutCallCCount;
import com.mtpt.alibean.page.PublicPage;

public interface ITBOutCallCCountService {
    
    JSONObject selectOutCallPvCount(PublicPage page);
    
    JSONObject selectTongjianPfPvcountData(PublicPage page);
    
    JSONObject selectGFCCPvcountData(PublicPage page);
    
    JSONObject selectMoreCaseLanPvcountData(PublicPage page);
    
    JSONObject selectMoreCasePinPvcountData(PublicPage page);
}
