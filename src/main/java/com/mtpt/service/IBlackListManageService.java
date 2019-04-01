package com.mtpt.service;

import java.util.List;

import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import com.mtpt.bean.TBBlackList;
import com.mtpt.bean.page.BlackPage;

public interface IBlackListManageService {
	JSONObject selectBlackListDataByPage(BlackPage page);
	
	JSONObject insertBlackListDataForPhone(TBBlackList tbBlackList);
	
	JSONObject deleteBlackListDataById(int id);
	
	JSONObject uploadBlackDataByFile(MultipartFile blackfile);
	
	List<TBBlackList> selectByAll();
}
