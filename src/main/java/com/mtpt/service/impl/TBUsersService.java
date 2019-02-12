package com.mtpt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtpt.bean.TBUsers;
import com.mtpt.bean.page.AlipayPage;
import com.mtpt.dao.TBUsersMapper;
import com.mtpt.service.ITBUsersService;
@Service("usersService")
public class TBUsersService implements ITBUsersService{
	
	@Autowired
	TBUsersMapper mapper;
	
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(id);
	}

	public int insert(TBUsers record) {
		// TODO Auto-generated method stub
		return mapper.insert(record);
	}

	public int insertSelective(TBUsers record) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(record);
	}

	public TBUsers selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(TBUsers record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(TBUsers record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKey(record);
	}

	public List<TBUsers> selectByAlipayPage(AlipayPage page) {
		// TODO Auto-generated method stub
		return mapper.selectByAlipayPage(page);
	}

	public int selectByCount(AlipayPage page) {
		// TODO Auto-generated method stub
		return mapper.selectByCount(page);
	}

	public List<TBUsers> selectAllAlipayUser() {
		// TODO Auto-generated method stub
		return mapper.selectAllAlipayUser();
	}

	public List<TBUsers> selectUnGiftFlowUser(AlipayPage page) {
		// TODO Auto-generated method stub
		return mapper.selectUnGiftFlowUser(page);
	}

}
