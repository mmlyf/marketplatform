package com.mtpt.alidao;

import java.util.List;

import com.mtpt.alibean.TBSceneData;
import com.mtpt.alibean.TBSceneDataKey;
import com.mtpt.bean.page.SceneDataPage;

public interface TBSceneDataMapper {
    int deleteByPrimaryKey(TBSceneDataKey key);

    int insert(TBSceneData record);

    int insertSelective(TBSceneData record);

    TBSceneData selectByPrimaryKey(TBSceneDataKey key);

    int updateByPrimaryKeySelective(TBSceneData record);

    int updateByPrimaryKey(TBSceneData record);
    
    int insertIntoSceneDataByList(List<TBSceneData> list);
    
    int updateIntoSceneDataByList(List<TBSceneData> list);
    
    int deleteSceneDataByLabel(String labelId);
    
    List<TBSceneData> selectDataByLabel(String labelId);
    
    List<TBSceneData> selectDataByPage(SceneDataPage page);
    
    Integer selectDataCountByPage(SceneDataPage page);
    
    
}