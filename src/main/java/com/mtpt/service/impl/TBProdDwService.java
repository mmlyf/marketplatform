package com.mtpt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtpt.bean.TBProdDw;
import com.mtpt.dao.TBProdDwMapper;
import com.mtpt.service.ITBProdDwService;

@Service("tbProdDwService")
public class TBProdDwService implements ITBProdDwService {
	
	@Autowired
	TBProdDwMapper mapper;
	
	public int deleteByPrimaryKey(Integer dwId) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(dwId);
	}

	public int insert(TBProdDw record) {
		// TODO Auto-generated method stub
		return mapper.insert(record);
	}

	public int insertSelective(TBProdDw record) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(record);
	}

	public TBProdDw selectByPrimaryKey(Integer dwId) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(dwId);
	}

	public int updateByPrimaryKeySelective(TBProdDw record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(TBProdDw record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKey(record);
	}

	public List<TBProdDw> selectByProid(int proid) {
		// TODO Auto-generated method stub
		return mapper.selectByProid(proid);
	}

}
