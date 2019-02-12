package com.mtpt.aliservice;

import java.util.List;

import com.mtpt.alibean.TBLabel;

public interface ITBLabelService {
	int deleteByPrimaryKey(Integer id);

    int insert(TBLabel record);

    int insertSelective(TBLabel record);

    TBLabel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TBLabel record);

    int updateByPrimaryKey(TBLabel record);
    
    List<TBLabel> selectAllData();
}
