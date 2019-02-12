package com.mtpt.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mtpt.bean.Orders;
import com.mtpt.bean.page.OrdersPage;
import com.mtpt.bean.page.TotalPage;

public interface IOrdersService {
	int deleteByPrimaryKey(String id);

	int insert(Orders record);

	int insertSelective(Orders record);

	Orders selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(Orders record);

	int updateByPrimaryKeyWithBLOBs(Orders record);

	int updateByPrimaryKey(Orders record);

	List<Orders> selectByOrdersPage(OrdersPage page);
	
	Integer selectByOrdersCount(OrdersPage page);
	
	Integer selectCountAllByPage(TotalPage page);
}
