package com.mtpt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtpt.bean.TBHfcs;
import com.mtpt.dao.TBHfcsMapper;
import com.mtpt.service.ITBHfcsService;
@Service("hfcsService")
public class TBHfcsService implements ITBHfcsService{

	@Autowired
	TBHfcsMapper mapper;
	
	@Override
	public int deleteByPrimaryKey(Integer hfId) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(hfId);
	}

	@Override
	public int insert(TBHfcs record) {
		// TODO Auto-generated method stub
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(TBHfcs record) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(record);
	}

	@Override
	public TBHfcs selectByPrimaryKey(Integer hfId) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(hfId);
	}

	@Override
	public int updateByPrimaryKeySelective(TBHfcs record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(TBHfcs record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public List<TBHfcs> selectByAll() {
		// TODO Auto-generated method stub
		return mapper.selectByAll();
	}

}
