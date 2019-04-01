package com.mtpt.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mtpt.bean.TBBlackList;
import com.mtpt.bean.page.BlackPage;
import com.mtpt.dao.TBBlackListMapper;
import com.mtpt.service.IBlackListManageService;

@Service("blackListManageService")
public class BlackListManageService implements IBlackListManageService {

	@Autowired
	private TBBlackListMapper blacklistMapper;
	
	private SimpleDateFormat sdf = null;
	
	/**
	 * 分页查询当前黑名单的数据
	 */
	@Override
	public JSONObject selectBlackListDataByPage(BlackPage page) {
		// TODO Auto-generated method stub
		int totals = blacklistMapper.selectByCount(page);
		List<TBBlackList> blackList = blacklistMapper.selectByBlackPage(page);
		JSONObject jsonmap = new JSONObject();
		List<JSONObject> jsonlist = new ArrayList<JSONObject>();
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for(int i =0;i<blackList.size();i++) {
			JSONObject map = new JSONObject();
			map.put("id", blackList.get(i).getId());
			map.put("dn", blackList.get(i).getDn());
			String addtime = sdf.format(blackList.get(i).getAdTime());
			map.put("addtime", addtime);
			jsonlist.add(map);
		}
		jsonmap.put("code", 0);
		jsonmap.put("count", totals);
		jsonmap.put("msg", "");
		jsonmap.put("data", jsonlist);
		return jsonmap;
	}

	/**
	 * 单条添加黑名单数据
	 */
	@Override
	public JSONObject insertBlackListDataForPhone(TBBlackList tbBlackList) {
		// TODO Auto-generated method stub
		int result = blacklistMapper.insertSelective(tbBlackList);
		JSONObject json = new JSONObject();
		if(result>0) {
			json.put("code", 1);
		}else {
			json.put("code", 0);
		}
		return json;
	}

	/**
	 * 根据ID删除黑名单的数据
	 */
	@Override
	public JSONObject deleteBlackListDataById(int id) {
		// TODO Auto-generated method stub
		int result = blacklistMapper.deleteByPrimaryKey(id);
		JSONObject json = new JSONObject();
		if (result>0) {
			json.put("code", 0);
		}else {
			json.put("code", 1);
		}
		json.put("msg", "");
		json.put("data", "");
		return json;
	}

	/**
	 * 通过文件上传的方式，批量添加黑名单数据
	 */
	@Override
	public JSONObject uploadBlackDataByFile(MultipartFile blackfile) {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			InputStream in =  blackfile.getInputStream();
			InputStreamReader isr = new InputStreamReader(in);
			BufferedReader reader = new BufferedReader(isr);
			List<String> phonelist = new ArrayList<String>();
			String line = "";
			while((line=reader.readLine())!=null) {
				phonelist.add(line);
			}
			result = blacklistMapper.insertByList(phonelist);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject json = new JSONObject();
		if (result > 0) {
			json.put("code", 0);
		}else {
			json.put("code", 1);
		}
		json.put("msg", "");
		json.put("data", "");
		return json;
	}

	@Override
	public List<TBBlackList> selectByAll() {
		// TODO Auto-generated method stub
		return blacklistMapper.selectByAll();
	}

}
