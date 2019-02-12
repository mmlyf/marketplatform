package com.mtpt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtpt.bean.TBProdLx;
import com.mtpt.dao.TBProdLxMapper;
import com.mtpt.service.ITBProdLxService;

@Service("tbProdLxService")
public class TBProdLxService implements ITBProdLxService {
	
	@Autowired
	TBProdLxMapper mapper;
	
	public int deleteByPrimaryKey(Integer lxid) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(lxid);
	}

	public int insert(TBProdLx record) {
		// TODO Auto-generated method stub
		return mapper.insert(record);
	}

	public int insertSelective(TBProdLx record) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(record);
	}

	public TBProdLx selectByPrimaryKey(Integer lxid) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(lxid);
	}

	public int updateByPrimaryKeySelective(TBProdLx record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(TBProdLx record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKey(record);
	}

	public List<TBProdLx> selectByAll() {
		// TODO Auto-generated method stub
		return mapper.selectByAll();
	}

}
