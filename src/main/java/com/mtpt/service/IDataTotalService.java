package com.mtpt.service;

import java.util.List;

import com.mtpt.bean.DataTotal;
import com.mtpt.bean.page.TotalPage;

public interface IDataTotalService {
	int deleteByPrimaryKey(Integer id);

	int insert(DataTotal record);

	int insertSelective(DataTotal record);

	DataTotal selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(DataTotal record);

	int updateByPrimaryKey(DataTotal record);

	DataTotal selectByAddTime(String adtime);

	List<DataTotal> selectDataTotalByDelayPage(TotalPage totalPage);
	
	Integer selectCountByDelay(TotalPage totalPage);

	List<DataTotal> selectDataTotalMonth(String sectime);

	List<DataTotal> selectDataTotalWeek(Integer delay);
	
	List<DataTotal> selectDataTotalByDelay(TotalPage page);
}
