package com.mtpt.aliservice;

import java.util.List;

import com.mtpt.alibean.TBSceneJob;
import com.mtpt.alibean.page.SceneJobPage;

public interface ITBSceneJobService {
	int deleteByPrimaryKey(Integer id);

	int insert(TBSceneJob record);

	int insertSelective(TBSceneJob record);

	TBSceneJob selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(TBSceneJob record);

	int updateByPrimaryKey(TBSceneJob record);
	
	List<TBSceneJob> selectSceneJobByPage(SceneJobPage page);
	
	 Integer selectSceneJobCountByPage(SceneJobPage page);
}
