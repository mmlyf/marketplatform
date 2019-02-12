package com.mtpt.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtpt.bean.TBSuser;
import com.mtpt.dao.TBSuserMapper;
import com.mtpt.service.ITBSuserService;
@Service("tbsuserService")
public class TBSuserService implements ITBSuserService {
	@Autowired
	TBSuserMapper mapper;
	
	
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(id);
	}

	public int insert(TBSuser record) {
		// TODO Auto-generated method stub
		return mapper.insert(record);
	}

	public int insertSelective(TBSuser record) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(record);
	}

	public TBSuser selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(TBSuser record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(TBSuser record) {
		// TODO Auto-generated method stub
		return updateByPrimaryKey(record);
	}

	public TBSuser selectByNamePaw(Map<String, String> map) {
		// TODO Auto-generated method stub
		return mapper.selectByNamePaw(map);
	}

	@Override
	public List<TBSuser> selectSuserAllData() {
		// TODO Auto-generated method stub
		return mapper.selectSuserAllData();
	}

}
