package com.mtpt.alidao;

import java.util.List;

import com.mtpt.alibean.TBTongjianpfPvcount;
import com.mtpt.alibean.page.PublicPage;

public interface TBTongjianpfPvcountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TBTongjianpfPvcount record);

    int insertSelective(TBTongjianpfPvcount record);

    TBTongjianpfPvcount selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TBTongjianpfPvcount record);

    int updateByPrimaryKey(TBTongjianpfPvcount record);
    
    List<TBTongjianpfPvcount> selectAllDataByPage(PublicPage page);
    
    Integer selectAllDataCount();
}