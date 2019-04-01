package com.mtpt.service;

import org.json.JSONObject;

import com.mtpt.bean.page.IceBookPage;
import com.mtpt.bean.page.OrdersPage;

public interface IBusinessModuleManageService {
	JSONObject selectAllOrdersDataByPage(OrdersPage page);
	
	JSONObject  selectOrdersDetailByPhoneNum(String phone);
	
	//对订单进行订购、补订和退订的操作
	JSONObject ordersOperationForOrder(String id,String phonenum,Integer actioncode);
	
	JSONObject selectDsjIceBookDataDetailByPage(IceBookPage page);
	
	JSONObject selectIceCustomServiceBookDataByPage(IceBookPage page);
	
	JSONObject outputDsjIceDataByPage(IceBookPage page);
	
	JSONObject outputIceCustomServiceDataByPage(IceBookPage page);
	
	JSONObject selectOrdersPhoneForDetail(String phone);	
	
	
}
