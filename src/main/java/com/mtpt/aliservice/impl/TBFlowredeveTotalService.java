package com.mtpt.aliservice.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtpt.alibean.TBFlowredeveTotal;
import com.mtpt.alibean.page.PublicPage;
import com.mtpt.alidao.TBFlowredeveTotalMapper;
import com.mtpt.aliservice.ITBFlowredeveTotalService;

@Service("tbflowredevetotalservice")
public class TBFlowredeveTotalService implements ITBFlowredeveTotalService{

	@Autowired
	private TBFlowredeveTotalMapper mapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(TBFlowredeveTotal record) {
		// TODO Auto-generated method stub
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(TBFlowredeveTotal record) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(record);
	}

	@Override
	public TBFlowredeveTotal selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(TBFlowredeveTotal record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(TBFlowredeveTotal record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public List<TBFlowredeveTotal> selectByPublicPage(PublicPage page) {
		// TODO Auto-generated method stub
		return mapper.selectByPublicPage(page);
	}

	@Override
	public Integer selectAllDataCount() {
		// TODO Auto-generated method stub
		return mapper.selectAllDataCount();
	}

	@Override
	public List<TBFlowredeveTotal> selectAllData() {
		// TODO Auto-generated method stub
		return mapper.selectAllData();
	}

}
