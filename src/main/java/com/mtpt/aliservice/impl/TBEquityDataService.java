package com.mtpt.aliservice.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtpt.alibean.TBEquityData;
import com.mtpt.alibean.page.EquityDataPage;
import com.mtpt.alidao.TBEquityDataMapper;
import com.mtpt.aliservice.ITBEquityDataService;
@Service("equityDataService")
public class TBEquityDataService implements ITBEquityDataService{

	@Autowired
	private TBEquityDataMapper mapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(TBEquityData record) {
		// TODO Auto-generated method stub
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(TBEquityData record) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(record);
	}

	@Override
	public TBEquityData selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(TBEquityData record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(TBEquityData record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public List<TBEquityData> selectDataByAddtime(String sectime) {
		// TODO Auto-generated method stub
		return mapper.selectDataByAddtime(sectime);
	}

	@Override
	public List<TBEquityData> selectEquityAndResultData(EquityDataPage page) {
		// TODO Auto-generated method stub
		return mapper.selectEquityAndResultData(page);
	}

	@Override
	public Integer selectDataAllCount() {
		// TODO Auto-generated method stub
		return mapper.selectDataAllCount();
	}
	
}
