package com.mtpt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtpt.bean.TBBlackList;
import com.mtpt.bean.page.BlackPage;
import com.mtpt.dao.TBBlackListMapper;
import com.mtpt.service.ITBBlackListService;
@Service("balckservice")
public class TBBlackListService implements ITBBlackListService{
	
	@Autowired
	TBBlackListMapper mapper;

	
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(id);
	}

	public int insert(TBBlackList record) {
		// TODO Auto-generated method stub
		return mapper.insert(record);
	}

	public int insertSelective(TBBlackList record) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(record);
	}

	public TBBlackList selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(TBBlackList record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(TBBlackList record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKey(record);
	}

	public List<TBBlackList> selectByAll() {
		// TODO Auto-generated method stub
		return mapper.selectByAll();
	}

	public List<TBBlackList> selectByBlackPage(BlackPage page) {
		// TODO Auto-generated method stub
		return mapper.selectByBlackPage(page);
	}

	public Integer selectByCount(BlackPage page) {
		// TODO Auto-generated method stub
		return mapper.selectByCount(page);
	}

	public int insertByList(List<String> list) {
		// TODO Auto-generated method stub
		return mapper.insertByList(list);
	}

}
