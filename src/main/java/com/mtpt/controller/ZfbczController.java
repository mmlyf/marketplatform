package com.mtpt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mtpt.bean.Zfbcz;
import com.mtpt.bean.page.PublicLocalPage;
import com.mtpt.bean.page.ZfbczPage;
import com.mtpt.service.IZfbczService;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/zfbcz")
public class ZfbczController {
	@Resource
	private IZfbczService zfbczService;
	@RequestMapping(value="/selectall",method= {RequestMethod.POST,RequestMethod.GET})
	private void selectAllDataByPage(ZfbczPage page,HttpServletRequest request,HttpServletResponse response) {
		int totals = zfbczService.selectAllDataCount();
		page.setTotalRecord(totals);
		List<Zfbcz> list = zfbczService.selectAllData(page);
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
		try {
			PrintWriter pw = response.getWriter();
			pw.write(json.toString());
			pw.flush();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
