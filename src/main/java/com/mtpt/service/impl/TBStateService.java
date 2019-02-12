package com.mtpt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtpt.bean.TBState;
import com.mtpt.dao.TBStateMapper;
import com.mtpt.service.ITBStateService;
@Service("stateservice")
public class TBStateService implements ITBStateService {
	@Autowired
	TBStateMapper mapper;
	
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(id);
	}

	public int insert(TBState record) {
		// TODO Auto-generated method stub
		return mapper.insert(record);
	}

	public int insertSelective(TBState record) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(record);
	}

	public TBState selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(TBState record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(TBState record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKey(record);
	}

	public TBState selectByState(int state) {
		// TODO Auto-generated method stub
		return mapper.selectByState(state);
	}

}
