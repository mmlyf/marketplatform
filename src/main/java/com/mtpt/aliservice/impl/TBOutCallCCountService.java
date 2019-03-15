package com.mtpt.aliservice.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtpt.alibean.TBOutCallCCount;
import com.mtpt.alibean.page.PublicPage;
import com.mtpt.alidao.TBOutCallCCountMapper;
import com.mtpt.aliservice.ITBOutCallCCountService;
@Service("outCallCcountService")
public class TBOutCallCCountService implements ITBOutCallCCountService{

	@Autowired
	private TBOutCallCCountMapper mapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(TBOutCallCCount record) {
		// TODO Auto-generated method stub
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(TBOutCallCCount record) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(record);
	}

	@Override
	public TBOutCallCCount selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(TBOutCallCCount record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(TBOutCallCCount record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public List<TBOutCallCCount> selectAllDataByPage(PublicPage page) {
		// TODO Auto-generated method stub
		return mapper.selectAllDataByPage(page);
	}

	@Override
	public Integer selectAllDataCountByPage() {
		// TODO Auto-generated method stub
		return mapper.selectAllDataCountByPage();
	}

}
