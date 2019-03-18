package com.mtpt.aliservice.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtpt.alibean.TBTongjianpfPvcount;
import com.mtpt.alibean.page.PublicPage;
import com.mtpt.alidao.TBTongjianpfPvcountMapper;
import com.mtpt.aliservice.ITBTongjianpfPvcountService;
@Service("tongjianpfPvService")
public class TBTongjianpfPvcountService implements ITBTongjianpfPvcountService{

	@Autowired 
	private TBTongjianpfPvcountMapper mapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(TBTongjianpfPvcount record) {
		// TODO Auto-generated method stub
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(TBTongjianpfPvcount record) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(record);
	}

	@Override
	public TBTongjianpfPvcount selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(TBTongjianpfPvcount record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(TBTongjianpfPvcount record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public List<TBTongjianpfPvcount> selectAllDataByPage(PublicPage page) {
		// TODO Auto-generated method stub
		return mapper.selectAllDataByPage(page);
	}

	@Override
	public Integer selectAllDataCount() {
		// TODO Auto-generated method stub
		return mapper.selectAllDataCount();
	}

}
