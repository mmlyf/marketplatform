package com.mtpt.aliservice;

import java.util.List;

import org.json.JSONObject;

import com.mtpt.alibean.MutidayTotal;
import com.mtpt.alibean.page.PublicPage;

public interface IMutidayTotalService {
	
    
    //分页查询所有多日包的统计数据
    JSONObject selectAllMutidayDataByPage(PublicPage page);
    
    //统计所有多日包统计数据的总和
    JSONObject selectAllMutidayDataTotal();
}
