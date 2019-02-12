package com.mtpt.aliservice.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtpt.alibean.MutidayTotal;
import com.mtpt.alibean.page.PublicPage;
import com.mtpt.alidao.MutidayTotalMapper;
import com.mtpt.aliservice.IMutidayTotalService;

@Service("mutidaytotalService")
public class MutidayTotalService implements IMutidayTotalService{

	@Autowired
	private MutidayTotalMapper mapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(MutidayTotal record) {
		// TODO Auto-generated method stub
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(MutidayTotal record) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(record);
	}

	@Override
	public MutidayTotal selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(MutidayTotal record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(MutidayTotal record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public List<MutidayTotal> selectAllDataByPage(PublicPage page) {
		// TODO Auto-generated method stub
		return mapper.selectAllDataByPage(page);
	}

	@Override
	public Integer selectAllDataCount() {
		// TODO Auto-generated method stub
		return mapper.selectAllDataCount();
	}

	@Override
	public List<MutidayTotal> selectAllData() {
		// TODO Auto-generated method stub
		return mapper.selectAllData();
	}

}
