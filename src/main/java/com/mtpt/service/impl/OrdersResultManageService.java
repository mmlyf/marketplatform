package com.mtpt.service.impl;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtpt.alibean.TBOrderFlowgift;
import com.mtpt.alibean.TBSecondConfirm;
import com.mtpt.alidao.TBOrderFlowgiftMapper;
import com.mtpt.alidao.TBSecondConfirmMapper;
import com.mtpt.bean.Orders;
import com.mtpt.bean.Products;
import com.mtpt.bean.page.OrdersPage;
import com.mtpt.dao.OrdersMapper;
import com.mtpt.dao.ProductsMapper;
import com.mtpt.extend.OtherMethod;
import com.mtpt.service.IAlipayBindUserManageService;
import com.mtpt.service.IOrdersResultManageService;
@Service("orderSuccessFlowGift")
public class OrdersResultManageService implements IOrdersResultManageService{
	
	private Logger log = Logger.getLogger(OrdersResultManageService.class);
	
	@Autowired
	private OrdersMapper ordersMapper;
	@Autowired
	private TBOrderFlowgiftMapper orderflowgiftMapper;
	@Autowired
	private ProductsMapper productsMapper;
	@Resource
	private IAlipayBindUserManageService alipayBindUserManageService;
	@Resource
	private TBSecondConfirmMapper secondConfirmMapper;
	
	
	@Override
	public void selectDayOrderSuccessAndGiftFlow() {
		// TODO Auto-generated method stub
		Date date = new Date();
		long timereduceday = date.getTime() - 24*60*60*1000;
		date = new Date(timereduceday);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String timestr = sdf.format(date);
		String starttime = timestr + " 00:00:00";
		String endtime = timestr + " 23:59:59";
		OrdersPage page = new OrdersPage();
		page.setDate_star(starttime);
		page.setDate_end(endtime);
		page.setDdlx("0");
		List<Orders> orderlist = ordersMapper.selectByOrdersPageNoLimit(page);
		if (orderlist.isEmpty()||orderlist==null) {
			log.debug("进行的订购无成功记录。");
		}else {
			for(Orders orders:orderlist) {
				TBOrderFlowgift tbOrderFlowgift = new TBOrderFlowgift();
				tbOrderFlowgift.setDn(orders.getMobile());
				tbOrderFlowgift.setOrderFlowno(orders.getSerialno());
				Products products = productsMapper.selectByPrimaryKey(orders.getProductid());
				tbOrderFlowgift.setProdName(products.getProductname());
				JSONObject json = alipayBindUserManageService.submitAlipayBindUserGiftFlow(orders.getMobile(), "500");
				if (json.getInt("code")==0) {
					tbOrderFlowgift.setIsGift(0);
					int inres = orderflowgiftMapper.insertSelective(tbOrderFlowgift);
					if (inres>0) {
						log.debug("订购记录成功充值");
					}
				}
			}
		}
	}
	
	/**
	 * 查询当前的二次确认的情况
	 */
	@Override
	public void selectDayOrderSecondConfirmContent() {
		// TODO Auto-generated method stub
		Date date = new Date();
		long reduceday = date.getTime() - 10*60*60*1000;
		date = new Date(reduceday);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String timestr = sdf.format(date);
		String starttime = timestr + " 00:00:00";
		String endtime = timestr + " 23:59:59";
		OrdersPage page = new OrdersPage();
		page.setDate_end(endtime);
		page.setDate_star(starttime);
		List<Orders> orderslist = ordersMapper.selectByOrdersPageNoLimit(page);
		if (orderslist==null||orderslist.isEmpty()) {
			log.debug("当前时间无订购记录");
		}else {
			for(Orders orders : orderslist) {
				try {
					TBSecondConfirm tbSecondConfirm = new TBSecondConfirm();
					String result = OtherMethod.selectSecondConfirmForXier(orders.getSerialno());
					JSONObject json = new JSONObject(result);
					String code = json.getString("code");
					tbSecondConfirm.setCode(code);
					tbSecondConfirm.setAgw(orders.getSerialno());
					tbSecondConfirm.setDn(orders.getMobile());
					tbSecondConfirm.setOrderTime(orders.getAddtime());
					if (code.equals("0")) {
						JSONObject confirmMo = json.getJSONObject("confirmMo");
						String confirmcontent = confirmMo.getString("ud");
						tbSecondConfirm.setContent(confirmcontent);
						tbSecondConfirm.setConfirmTime(confirmMo.getString("deliver_time"));
					}else {
						tbSecondConfirm.setContent(json.getString("msg"));
					}
					secondConfirmMapper.insertSelective(tbSecondConfirm);
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
}
