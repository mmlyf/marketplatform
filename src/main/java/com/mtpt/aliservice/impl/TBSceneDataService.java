package com.mtpt.aliservice.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtpt.alibean.TBSceneData;
import com.mtpt.alibean.TBSceneDataKey;
import com.mtpt.alidao.TBSceneDataMapper;
import com.mtpt.aliservice.ITBSceneDataService;
import com.mtpt.bean.page.SceneDataPage;

@Service("tbsceneService")
public class TBSceneDataService implements ITBSceneDataService{

	@Autowired
	private TBSceneDataMapper mapper;

	@Override
	public int deleteByPrimaryKey(TBSceneDataKey key) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(key);
	}

	@Override
	public int insert(TBSceneData record) {
		// TODO Auto-generated method stub
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(TBSceneData record) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(record);
	}

	@Override
	public TBSceneData selectByPrimaryKey(TBSceneDataKey key) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(key);
	}

	@Override
	public int updateByPrimaryKeySelective(TBSceneData record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(TBSceneData record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public int insertIntoSceneDataByList(List<TBSceneData> list) {
		// TODO Auto-generated method stub
		return mapper.insertIntoSceneDataByList(list);
	}

	@Override
	public int updateIntoSceneDataByList(List<TBSceneData> list) {
		// TODO Auto-generated method stub
		return mapper.updateIntoSceneDataByList(list);
	}

	@Override
	public int deleteSceneDataByLabel(String labelId) {
		// TODO Auto-generated method stub
		return mapper.deleteSceneDataByLabel(labelId);
	}

	@Override
	public List<TBSceneData> selectDataByLabel(String labelId) {
		// TODO Auto-generated method stub
		return mapper.selectDataByLabel(labelId);
	}

	@Override
	public List<TBSceneData> selectDataByPage(SceneDataPage page) {
		// TODO Auto-generated method stub
		return mapper.selectDataByPage(page);
	}

	@Override
	public Integer selectDataCountByPage(SceneDataPage page) {
		// TODO Auto-generated method stub
		return mapper.selectDataCountByPage(page);
	}

}
