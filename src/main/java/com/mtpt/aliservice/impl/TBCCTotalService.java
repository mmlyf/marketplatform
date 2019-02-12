package com.mtpt.aliservice.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtpt.alibean.TBCCTotal;
import com.mtpt.alibean.page.PublicPage;
import com.mtpt.alidao.TBCCTotalMapper;
import com.mtpt.aliservice.ITBCCTotalService;
@Service("tbcctotalservice")
public class TBCCTotalService implements ITBCCTotalService{

	@Autowired
	private TBCCTotalMapper mapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(TBCCTotal record) {
		// TODO Auto-generated method stub
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(TBCCTotal record) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(record);
	}

	@Override
	public TBCCTotal selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(TBCCTotal record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(TBCCTotal record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public List<TBCCTotal> selectAllDataByPage(PublicPage page) {
		// TODO Auto-generated method stub
		return mapper.selectAllDataByPage(page);
	}

	@Override
	public Integer selectAllDataCount(PublicPage page) {
		// TODO Auto-generated method stub
		return mapper.selectAllDataCount(page);
	}

	@Override
	public List<TBCCTotal> selectAllData() {
		// TODO Auto-generated method stub
		return mapper.selectAllData();
	}
}
