package com.mtpt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtpt.bean.TBPermission;
import com.mtpt.dao.TBPermissionMapper;
import com.mtpt.service.ITBPermissionService;

@Service("permissionservice")
public class TBPermissionService implements ITBPermissionService{

	@Autowired
	private TBPermissionMapper mapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(TBPermission record) {
		// TODO Auto-generated method stub
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(TBPermission record) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(record);
	}

	@Override
	public TBPermission selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(TBPermission record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(TBPermission record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public TBPermission selectByPermissionId(Integer id) {
		// TODO Auto-generated method stub
		return mapper.selectByPermissionId(id);
	}

	@Override
	public List<TBPermission> selectPermissionData() {
		// TODO Auto-generated method stub
		return mapper.selectPermissionData();
	}
}
