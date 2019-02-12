package com.mtpt.dao;

import com.mtpt.bean.MoReceive;
import com.mtpt.bean.page.TotalPage;

public interface MoReceiveMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(MoReceive record);

	int insertSelective(MoReceive record);

	MoReceive selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(MoReceive record);

	int updateByPrimaryKeyWithBLOBs(MoReceive record);

	int updateByPrimaryKey(MoReceive record);

	Integer selectCountAllByPage(TotalPage page);
}