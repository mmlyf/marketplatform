package com.mtpt.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtpt.bean.Zfbcz;
import com.mtpt.bean.page.ZfbczPage;
import com.mtpt.dao.ZfbczMapper;
import com.mtpt.service.IZfbczManageService;

@Service("zfbczService")
public class ZfbczManageService implements IZfbczManageService{

	@Autowired
	private ZfbczMapper zfbczMapper;
	@Override
	public JSONObject selectAllDataByPage(ZfbczPage page) {
		// TODO Auto-generated method stub
		int totals = zfbczMapper.selectAllDataCountByPage(page);
		page.setTotalRecord(totals);
		List<Zfbcz> list = zfbczMapper.selectAllData(page);
		int i = 1;
		JSONObject json = new JSONObject();
		List<JSONObject> jsonlist = new ArrayList<>();
		for(Zfbcz zfbcz: list) {
			JSONObject value = new JSONObject();
			value.put("id", i);
			value.put("orderno", zfbcz.getPySeno());
			value.put("dn", zfbcz.getPyDn());
			value.put("proname", zfbcz.getPyOftitle());
			value.put("price", zfbcz.getPyTotalmoey());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String creatime = sdf.format(zfbcz.getPyCreatime());
			value.put("ordertime", creatime);
			if(zfbcz.getPyIfpay()==0) {
				value.put("zfstate", "未充值");
			}else if(zfbcz.getPyIfpay()==1) {
				value.put("zfstate", "已充值");
			}else {
				value.put("zfstate", "无效");
			}
			if(zfbcz.getPyOfcode()==null) {
				value.put("czstate", "");
			}else {
				if(zfbcz.getPyOfcode()==1) {
					value.put("czstate", "充值成功");
				}else if(zfbcz.getPyOfcode()==0) {
					value.put("czstate", "充值中");
				}else {
					value.put("czstate", "撤销（失败）");
				}
			}
			if(zfbcz.getPyZfqd()==1) {
				value.put("qudao", "支付宝");
			}else if(zfbcz.getPyZfqd()==2) {
				value.put("qudao", "微信");
			}else if(zfbcz.getPyZfqd()==3){
				value.put("qudao", "网页");
			}else {
				value.put("qudao", "");
			}
			i++;
			jsonlist.add(value);
		}
		json.put("code", 0);
		json.put("msg", "");
		json.put("count", totals);
		json.put("data", jsonlist);
		return json;
	}

}
