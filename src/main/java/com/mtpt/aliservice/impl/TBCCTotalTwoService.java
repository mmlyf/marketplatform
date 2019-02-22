package com.mtpt.aliservice.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtpt.alibean.TBCCTotalTwo;
import com.mtpt.alibean.page.PublicPage;
import com.mtpt.alidao.TBCCTotalTwoMapper;
import com.mtpt.aliservice.ITBCCTotalTwoService;

@Service("tbCCTotalTwoService")
public class TBCCTotalTwoService implements ITBCCTotalTwoService {

	@Autowired
	private TBCCTotalTwoMapper mapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(TBCCTotalTwo record) {
		// TODO Auto-generated method stub
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(TBCCTotalTwo record) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(record);
	}

	@Override
	public TBCCTotalTwo selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(TBCCTotalTwo record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(TBCCTotalTwo record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public List<TBCCTotalTwo> selectAllDataByPage(PublicPage page) {
		// TODO Auto-generated method stub
		return mapper.selectAllDataByPage(page);
	}

	@Override
	public Integer selectAllDataCount(PublicPage page) {
		// TODO Auto-generated method stub
		return mapper.selectAllDataCount(page);
	}

	@Override
	public List<TBCCTotalTwo> selectAllData() {
		// TODO Auto-generated method stub
		return mapper.selectAllData();
	}
}
