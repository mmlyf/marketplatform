package com.mtpt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtpt.bean.Orders;
import com.mtpt.bean.page.OrdersPage;
import com.mtpt.bean.page.TotalPage;
import com.mtpt.dao.OrdersMapper;
import com.mtpt.service.IOrdersService;

@Service("orderService")
public class OrdersService implements IOrdersService{

	@Autowired
	OrdersMapper mapper;
	
	
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(id);
	}

	public int insert(Orders record) {
		// TODO Auto-generated method stub
		return mapper.insert(record);
	}

	public int insertSelective(Orders record) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(record);
	}

	public Orders selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(Orders record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKeyWithBLOBs(Orders record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeyWithBLOBs(record);
	}

	public int updateByPrimaryKey(Orders record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKey(record);
	}

	public List<Orders> selectByOrdersPage(OrdersPage page) {
		// TODO Auto-generated method stub
		return mapper.selectByOrdersPage(page);
	}

	public Integer selectByOrdersCount(OrdersPage page) {
		// TODO Auto-generated method stub
		return mapper.selectByOrdersCount(page);
	}

	@Override
	public Integer selectCountAllByPage(TotalPage page) {
		// TODO Auto-generated method stub
		return mapper.selectCountAllByPage(page);
	}


}
