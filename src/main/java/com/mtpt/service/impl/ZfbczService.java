package com.mtpt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtpt.bean.Zfbcz;
import com.mtpt.bean.page.PublicLocalPage;
import com.mtpt.bean.page.ZfbczPage;
import com.mtpt.dao.ZfbczMapper;
import com.mtpt.service.IZfbczService;

@Service("zfbczService")
public class ZfbczService implements IZfbczService{

	@Autowired
	private ZfbczMapper mapper;
	@Override
	public int insert(Zfbcz record) {
		// TODO Auto-generated method stub
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(Zfbcz record) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(record);
	}

	@Override
	public List<Zfbcz> selectAllData(ZfbczPage page) {
		// TODO Auto-generated method stub
		return mapper.selectAllData(page);
	}

	@Override
	public Integer selectAllDataCount() {
		// TODO Auto-generated method stub
		return mapper.selectAllDataCount();
	}
	
}
