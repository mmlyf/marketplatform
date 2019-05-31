package com.mtpt.alidao;

import java.util.List;

import com.mtpt.alibean.TBIcePageTotal;
import com.mtpt.alibean.page.PublicPage;

public interface TBIcePageTotalMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TBIcePageTotal record);

    int insertSelective(TBIcePageTotal record);

    TBIcePageTotal selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TBIcePageTotal record);

    int updateByPrimaryKey(TBIcePageTotal record);
    
    List<TBIcePageTotal> selectIceGodDataByPage(PublicPage page);
    
    Integer selectIceGodCountByPage(PublicPage page);
}