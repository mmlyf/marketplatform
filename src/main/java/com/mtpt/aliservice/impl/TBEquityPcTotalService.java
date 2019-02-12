package com.mtpt.aliservice.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtpt.alibean.TBEquityPcTotal;
import com.mtpt.alibean.page.PublicPage;
import com.mtpt.alidao.TBEquityPcTotalMapper;
import com.mtpt.aliservice.ITBEquityPcTotalService;

@Service("equityPcTotalService")
public class TBEquityPcTotalService implements ITBEquityPcTotalService{

	@Autowired
	private TBEquityPcTotalMapper mapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(TBEquityPcTotal record) {
		// TODO Auto-generated method stub
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(TBEquityPcTotal record) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(record);
	}

	@Override
	public TBEquityPcTotal selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(TBEquityPcTotal record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(TBEquityPcTotal record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public List<TBEquityPcTotal> selectAllTotalData(PublicPage page) {
		// TODO Auto-generated method stub
		return mapper.selectAllTotalData(page);
	}

	@Override
	public Integer selectAllCount(PublicPage page) {
		// TODO Auto-generated method stub
		return mapper.selectAllCount(page);
	}

	@Override
	public List<TBEquityPcTotal> selectAllData() {
		// TODO Auto-generated method stub
		return mapper.selectAllData();
	}

}
