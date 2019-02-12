package com.mtpt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtpt.bean.DataTotal;
import com.mtpt.bean.page.TotalPage;
import com.mtpt.dao.DataTotalMapper;
import com.mtpt.service.IDataTotalService;

@Service("dataTotalService")
public class DataTotalService implements IDataTotalService{
	
	@Autowired
	DataTotalMapper mapper;
	
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(id);
	}

	public int insert(DataTotal record) {
		// TODO Auto-generated method stub
		return mapper.insert(record);
	}

	public int insertSelective(DataTotal record) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(record);
	}

	public DataTotal selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(DataTotal record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(DataTotal record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKey(record);
	}

	public DataTotal selectByAddTime(String adtime) {
		// TODO Auto-generated method stub
		return mapper.selectByAddTime(adtime);
	}

	public List<DataTotal> selectDataTotalByDelayPage(TotalPage totalPage) {
		// TODO Auto-generated method stub
		return mapper.selectDataTotalByDelayPage(totalPage);
	}

	public List<DataTotal> selectDataTotalMonth(String sectime) {
		// TODO Auto-generated method stub
		return mapper.selectDataTotalMonth(sectime);
	}

	public List<DataTotal> selectDataTotalWeek(Integer delay) {
		// TODO Auto-generated method stub
		return mapper.selectDataTotalWeek(delay);
	}

	@Override
	public Integer selectCountByDelay(TotalPage totalPage) {
		// TODO Auto-generated method stub
		return mapper.selectCountByDelay(totalPage);
	}
	
}
