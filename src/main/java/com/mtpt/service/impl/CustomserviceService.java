package com.mtpt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtpt.bean.CustomService;
import com.mtpt.bean.page.IceBookPage; 
import com.mtpt.dao.CustomServiceMapper;
import com.mtpt.service.ICustomService;

@Service("customService")
public class CustomserviceService implements ICustomService{

	@Autowired
	CustomServiceMapper mapper;
	
	
	@Override
	public int deleteByPrimaryKey(Integer dxId) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(dxId);
	}

	@Override
	public int insert(CustomService record) {
		// TODO Auto-generated method stub
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(CustomService record) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(record);
	}

	@Override
	public CustomService selectByPrimaryKey(Integer dxId) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(dxId);
	}

	@Override
	public int updateByPrimaryKeySelective(CustomService record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(CustomService record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public List<CustomService> selectIceDataByPage(IceBookPage page) {
		// TODO Auto-generated method stub
		return mapper.selectIceDataByPage(page);
	}

	@Override
	public Integer selectIceDataCountByPage(IceBookPage page) {
		// TODO Auto-generated method stub
		return mapper.selectIceDataCountByPage(page);
	}

	@Override
	public List<CustomService> selectAllCsDataByPage(IceBookPage page) {
		// TODO Auto-generated method stub
		return mapper.selectAllCsDataByPage(page);
	}

	
}
