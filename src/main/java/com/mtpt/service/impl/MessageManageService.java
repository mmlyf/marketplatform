package com.mtpt.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtpt.bean.TBMssage;
import com.mtpt.bean.page.TBMessagePage;
import com.mtpt.dao.TBMssageMapper;
import com.mtpt.service.IMessageManageService;

@Service("messageManageService")
public class MessageManageService implements IMessageManageService{

	@Autowired
	private TBMssageMapper tbmssageMapper;
	
	/**
	 * 分页查询当前的消息列表
	 */
	@Override
	public JSONObject selectAllMessageDataByPage(TBMessagePage page) {
		// TODO Auto-generated method stub
		int totals = tbmssageMapper.selectByCount(page);
		page.setTotalRecord(totals);
		List<TBMssage> list = tbmssageMapper.selectByCustom(page);
		JSONObject jsonmap = new JSONObject();
		List<JSONObject> jsonlist = new ArrayList<JSONObject>();
		for(TBMssage tbMssage:list) {
			JSONObject map = new JSONObject();
			map.put("id", tbMssage.getMisId());
			map.put("mistitle", tbMssage.getMisTitle());
			map.put("miscontent", tbMssage.getMisContent());
			jsonlist.add(map);
		}
		jsonmap.put("code", 0);
		jsonmap.put("count", totals);
		jsonmap.put("msg", "");
		jsonmap.put("data", jsonlist);
		return jsonmap;
	}


	/**
	 * 更新消息列表中指定消息的内容
	 */
	@Override
	public JSONObject updateMessageData(TBMssage tbMssage) {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();		
		int result = tbmssageMapper.updateByPrimaryKeySelective(tbMssage);
		if (result>0) {
			json.put("code", 1);
		}else {
			json.put("code", 0);
		}
		return json;
	}

	/**
	 * 删除指定消息
	 */
	@Override
	public JSONObject deleteMessageData(Integer msgid) {
		// TODO Auto-generated method stub
		int result = tbmssageMapper.deleteByPrimaryKey(msgid);
		JSONObject json = new JSONObject();
		if (result>0) {
			json.put("code", 1);
		}else {
			json.put("code", 0);
		}
		return json;
	}

	/**
	 * 添加消息至数据表
	 */
	@Override
	public JSONObject insertMessageData(TBMssage tbMssage) {
		// TODO Auto-generated method stub
		int result = tbmssageMapper.insertSelective(tbMssage);
		JSONObject json = new JSONObject();
		if(result>0) {
			json.put("code", 1);
		}else {
			json.put("code", 0);
		}
		return json;
	}


	@Override
	public TBMssage selectByPrimaryKey(int id) {
		// TODO Auto-generated method stub
		return tbmssageMapper.selectByPrimaryKey(id);
	}

}
