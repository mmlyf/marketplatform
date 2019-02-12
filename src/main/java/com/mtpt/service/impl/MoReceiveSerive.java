package com.mtpt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtpt.bean.MoReceive;
import com.mtpt.bean.page.TotalPage;
import com.mtpt.dao.MoReceiveMapper;
import com.mtpt.service.IMoReceiveService;

@Service("morecService")
public class MoReceiveSerive implements IMoReceiveService{
	
	@Autowired
	MoReceiveMapper mapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(MoReceive record) {
		// TODO Auto-generated method stub
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(MoReceive record) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(record);
	}

	@Override
	public MoReceive selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(MoReceive record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(MoReceive record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public int updateByPrimaryKey(MoReceive record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public Integer selectCountAllByPage(TotalPage page) {
		// TODO Auto-generated method stub
		return mapper.selectCountAllByPage(page);
	}

	

}
