package com.mtpt.dao;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.mtpt.bean.TBProdDw;

public interface TBProdDwMapper {
	int deleteByPrimaryKey(Integer dwId);

	int insert(TBProdDw record);

	int insertSelective(TBProdDw record);

	TBProdDw selectByPrimaryKey(Integer dwId);

	int updateByPrimaryKeySelective(TBProdDw record);

	int updateByPrimaryKey(TBProdDw record);

	List<TBProdDw> selectByProid(@RequestParam("proid") int proid);
}