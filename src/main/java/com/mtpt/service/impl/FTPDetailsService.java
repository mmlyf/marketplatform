package com.mtpt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtpt.bean.FTPDetails;
import com.mtpt.bean.TBUsers;
import com.mtpt.bean.page.AlipayPage;
import com.mtpt.dao.FTPDetailsMapper;
import com.mtpt.service.IFTPDetailsService;
@Service("detailService")
public class FTPDetailsService implements IFTPDetailsService{
	@Autowired
	FTPDetailsMapper mapper;
	

	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(id);
	}

	public int insert(FTPDetails record) {
		// TODO Auto-generated method stub
		return mapper.insert(record);
	}

	public int insertSelective(FTPDetails record) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(record);
	}

	public FTPDetails selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(FTPDetails record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKeyWithBLOBs(FTPDetails record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeyWithBLOBs(record);
	}

	public int updateByPrimaryKey(FTPDetails record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKey(record);
	}

	public List<FTPDetails> selectByAlipayPage(AlipayPage page) {
		// TODO Auto-generated method stub
		return mapper.selectByAlipayPage(page);
	}

	public int selectByCount(AlipayPage page) {
		// TODO Auto-generated method stub
		return mapper.selectByCount(page);
	}

}
