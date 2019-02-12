package com.mtpt.alidao;

import java.util.List;

import com.mtpt.alibean.MutidayTotal;
import com.mtpt.alibean.page.PublicPage;

public interface MutidayTotalMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MutidayTotal record);

    int insertSelective(MutidayTotal record);

    MutidayTotal selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MutidayTotal record);

    int updateByPrimaryKey(MutidayTotal record);
    
    List<MutidayTotal> selectAllDataByPage(PublicPage page);
    
    Integer selectAllDataCount();
    
    List<MutidayTotal> selectAllData();
}