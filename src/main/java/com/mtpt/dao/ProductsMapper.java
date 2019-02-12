package com.mtpt.dao;

import java.util.List;

import com.mtpt.bean.Products;

public interface ProductsMapper {
    int deleteByPrimaryKey(String id);

    int insert(Products record);

    int insertSelective(Products record);

    Products selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Products record);

    int updateByPrimaryKey(Products record);
    
    List<Products> selectByProductName(String proname);
}