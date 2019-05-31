package com.mtpt.alidao;

import java.util.List;

import com.mtpt.alibean.TBDxThirdPageTotal;
import com.mtpt.alibean.page.PublicPage;

public interface TBDxThirdPageTotalMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TBDxThirdPageTotal record);

    int insertSelective(TBDxThirdPageTotal record);

    TBDxThirdPageTotal selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TBDxThirdPageTotal record);

    int updateByPrimaryKey(TBDxThirdPageTotal record);
    
    List<TBDxThirdPageTotal> selectDxThirdDataByPage(PublicPage page);
    
    Integer selectDxThirdCountByPage(PublicPage page);
}