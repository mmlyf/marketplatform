package com.mtpt.hkaliservice.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtpt.alibean.page.PublicPage;
import com.mtpt.hkalibean.TBCouponAddr;
import com.mtpt.hkalibean.TBCouponOrder;
import com.mtpt.hkalidao.TBCouponAddrMapper;
import com.mtpt.hkalidao.TBCouponOrderMapper;
import com.mtpt.hkalilservice.ICouponOrderDataService;
@Service("couponorder")
public class CouponOrderDataService implements ICouponOrderDataService{

	@Autowired
	private TBCouponOrderMapper couponOrderMapper;
	@Autowired
	private TBCouponAddrMapper couponAddrMapper;
	
	@Override
	public JSONObject selectAllCouponDataByPage(PublicPage page) {
		// TODO Auto-generated method stub
		Integer totals = couponOrderMapper.selectCouponOrderAllCountByPage(page);
		page.setTotalRecord(totals);
		List<TBCouponOrder> list = couponOrderMapper.selectCouponOrderAllDataByPage(page);
		JSONObject json = new JSONObject();
		List<JSONObject> jsonlist = new ArrayList<>();
		for(TBCouponOrder tbCouponOrder:list) {
			JSONObject value = new JSONObject();
			value.put("id", tbCouponOrder.getId());
			value.put("out_trade_on", tbCouponOrder.getOutTradeNo());
			value.put("subject", tbCouponOrder.getSubject());
			value.put("total_amount", tbCouponOrder.getTotalAmount());
			Integer payid = Integer.parseInt(tbCouponOrder.getPayid());
			value.put("payid", payid==1?"支付宝":"微信");
			Integer addid = Integer.parseInt(tbCouponOrder.getAddrId());
			TBCouponAddr tbCouponAddr = couponAddrMapper.selectByPrimaryKey(addid);
			if (tbCouponAddr==null) {
				value.put("address", "");
			}else {
				value.put("address", tbCouponAddr.getAddr()!=null?tbCouponAddr.getAddr():"");
			}
			value.put("body", tbCouponOrder.getBody()!=null?tbCouponOrder.getBody():"");
			Integer status = tbCouponOrder.getStatus()!=null?tbCouponOrder.getStatus():1;
			value.put("status", status==1?"未支付":"已支付");
			String timestr = tbCouponOrder.getAddtime();
			long timelong = Long.valueOf(timestr) * 1000;
			Date date = new Date(timelong);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String addtime = sdf.format(date);
			value.put("addtime", addtime);
			value.put("mobile", tbCouponOrder.getMobile()!=null?tbCouponOrder.getMobile():"");
			value.put("time", tbCouponOrder.getTime()!=null?tbCouponOrder.getTime():"");
			value.put("color", tbCouponOrder.getColor()!=null?tbCouponOrder.getColor():"");
			value.put("type", tbCouponOrder.getType()==1?"支付宝":(tbCouponOrder.getType()==2?"权益":"活动"));
			jsonlist.add(value);			
		}
		json.put("code", 0);
		json.put("msg", "");
		json.put("count", totals);
		json.put("data", jsonlist);
		return json;
	}

}
