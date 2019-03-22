package com.mtpt.aliservice.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtpt.alibean.TBGFCCTotal;
import com.mtpt.alibean.page.PublicPage;
import com.mtpt.alidao.TBGFCCTotalMapper;
import com.mtpt.aliservice.ITBGFCCTotalService;
@Service("tbgfcctotal")
public class TBGFCCTotalService implements ITBGFCCTotalService{

	@Autowired
	private TBGFCCTotalMapper mapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(TBGFCCTotal record) {
		// TODO Auto-generated method stub
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(TBGFCCTotal record) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(record);
	}

	@Override
	public TBGFCCTotal selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(TBGFCCTotal record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(TBGFCCTotal record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public List<TBGFCCTotal> selectAllDataByPage(PublicPage page) {
		// TODO Auto-generated method stub
		return mapper.selectAllDataByPage(page);
	}

	@Override
	public Integer selectAllDataCount() {
		// TODO Auto-generated method stub
		return mapper.selectAllDataCount();
	}

}
