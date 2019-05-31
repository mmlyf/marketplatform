package com.mtpt.alidao;

import java.util.List;

import com.mtpt.alibean.TBIceThirdPageTotal;
import com.mtpt.alibean.page.PublicPage;

public interface TBIceThirdPageTotalMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TBIceThirdPageTotal record);

    int insertSelective(TBIceThirdPageTotal record);

    TBIceThirdPageTotal selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TBIceThirdPageTotal record);

    int updateByPrimaryKey(TBIceThirdPageTotal record);
    
    List<TBIceThirdPageTotal> selectIceThirdDataPage(PublicPage page);
    
    Integer selectIceThirdCountPage(PublicPage page);
}