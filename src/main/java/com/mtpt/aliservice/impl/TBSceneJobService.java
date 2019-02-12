package com.mtpt.aliservice.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtpt.alibean.TBSceneJob;
import com.mtpt.alibean.page.SceneJobPage;
import com.mtpt.alidao.TBSceneJobMapper;
import com.mtpt.aliservice.ITBSceneJobService;
@Service("tbscenejobservice")
public class TBSceneJobService implements ITBSceneJobService{

	@Autowired
	private TBSceneJobMapper mapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(TBSceneJob record) {
		// TODO Auto-generated method stub
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(TBSceneJob record) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(record);
	}

	@Override
	public TBSceneJob selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(TBSceneJob record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(TBSceneJob record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public List<TBSceneJob> selectSceneJobByPage(SceneJobPage page) {
		// TODO Auto-generated method stub
		return mapper.selectSceneJobByPage(page);
	}

	@Override
	public Integer selectSceneJobCountByPage(SceneJobPage page) {
		// TODO Auto-generated method stub
		return mapper.selectSceneJobCountByPage(page);
	}
}
