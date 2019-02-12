package com.mtpt.alidao;

import java.util.List;

import com.mtpt.alibean.TBLabel;

public interface TBLabelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TBLabel record);

    int insertSelective(TBLabel record);

    TBLabel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TBLabel record);

    int updateByPrimaryKey(TBLabel record);
    
    List<TBLabel> selectAllData();
}