package com.mtpt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtpt.bean.TBProd;
import com.mtpt.dao.TBProdMapper;
import com.mtpt.service.ITBProService;

@Service("tbProService")
public class TBProService implements ITBProService {
	
	@Autowired
	TBProdMapper mapper;
	
	public int deleteByPrimaryKey(Integer proid) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(proid);
	}

	public int insert(TBProd record) {
		// TODO Auto-generated method stub
		return mapper.insert(record);
	}

	public int insertSelective(TBProd record) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(record);
	}

	public TBProd selectByPrimaryKey(Integer proid) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(proid);
	}

	public int updateByPrimaryKeySelective(TBProd record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(TBProd record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKey(record);
	}

	public List<TBProd> selectByLxid(int lxid) {
		// TODO Auto-generated method stub
		return mapper.selectByLxid(lxid);
	}

}
