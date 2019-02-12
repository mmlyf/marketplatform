package com.mtpt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtpt.bean.Products;
import com.mtpt.dao.ProductsMapper;
import com.mtpt.service.IProductsService;
@Service("productsService")
public class ProductsService implements IProductsService{
	
	@Autowired
	ProductsMapper mapper;
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(id);
	}

	public int insert(Products record) {
		// TODO Auto-generated method stub
		return mapper.insert(record);
	}

	public int insertSelective(Products record) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(record);
	}

	public Products selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(Products record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(Products record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKey(record);
	}

	public List<Products> selectByProductName(String proname) {
		// TODO Auto-generated method stub
		return mapper.selectByProductName(proname);
	}

}
