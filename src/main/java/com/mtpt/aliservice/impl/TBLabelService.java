package com.mtpt.aliservice.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtpt.alibean.TBLabel;
import com.mtpt.alidao.TBLabelMapper;
import com.mtpt.aliservice.ITBLabelService;

@Service("tblabelService")
public class TBLabelService implements ITBLabelService{

	@Autowired
	private TBLabelMapper mapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(TBLabel record) {
		// TODO Auto-generated method stub
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(TBLabel record) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(record);
	}

	@Override
	public TBLabel selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(TBLabel record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(TBLabel record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public List<TBLabel> selectAllData() {
		// TODO Auto-generated method stub
		return mapper.selectAllData();
	}
}
