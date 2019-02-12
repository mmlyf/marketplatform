package com.mtpt.aliservice.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtpt.alibean.TBTehuiFlowTotal;
import com.mtpt.alibean.page.PublicPage;
import com.mtpt.alidao.TBTehuiFlowTotalMapper;
import com.mtpt.aliservice.ITBTehuiFlowTotalService;

@Service("tehuiFlowTotalService")
public class TBTehuiFlowTotalService implements ITBTehuiFlowTotalService{

	@Autowired
	private TBTehuiFlowTotalMapper mapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(TBTehuiFlowTotal record) {
		// TODO Auto-generated method stub
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(TBTehuiFlowTotal record) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(record);
	}

	@Override
	public TBTehuiFlowTotal selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(TBTehuiFlowTotal record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(TBTehuiFlowTotal record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public List<TBTehuiFlowTotal> selectDataByPublicPage(PublicPage page) {
		// TODO Auto-generated method stub
		return mapper.selectDataByPublicPage(page);
	}

	@Override
	public Integer selectAllDataCount() {
		// TODO Auto-generated method stub
		return mapper.selectAllDataCount();
	}

	@Override
	public List<TBTehuiFlowTotal> selectAllData() {
		// TODO Auto-generated method stub
		return mapper.selectAllData();
	}

}
