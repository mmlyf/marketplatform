package com.mtpt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtpt.bean.TBMssage;
import com.mtpt.bean.page.TBMessagePage;
import com.mtpt.dao.TBMssageMapper;
import com.mtpt.service.ITBMssageService;
@Service("tbMssageService")
public class TBMssageService implements ITBMssageService {
	@Autowired
	TBMssageMapper mapper;
	
	public int deleteByPrimaryKey(Integer misId) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(misId);
	}

	public int insert(TBMssage record) {
		// TODO Auto-generated method stub
		return mapper.insert(record);
	}

	public int insertSelective(TBMssage record) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(record);
	}

	public TBMssage selectByPrimaryKey(Integer misId) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(misId);
	}

	public int updateByPrimaryKeySelective(TBMssage record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKeyWithBLOBs(TBMssage record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeyWithBLOBs(record);
	}

	public int updateByPrimaryKey(TBMssage record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKey(record);
	}

	public List<TBMssage> selectByCustom(TBMessagePage page) {
		// TODO Auto-generated method stub
		return mapper.selectByCustom(page);
	}

	public List<TBMssage> selectByAll() {
		// TODO Auto-generated method stub
		return mapper.selectByAll();
	}

	public Integer selectByCount(TBMessagePage page) {
		// TODO Auto-generated method stub
		return mapper.selectByCount(page);
	}

	

}
