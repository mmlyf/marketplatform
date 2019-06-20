package com.mtpt.hkalidao;

import java.util.List;

import com.mtpt.alibean.page.PublicPage;
import com.mtpt.hkalibean.TBCouponOrder;

public interface TBCouponOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TBCouponOrder record);

    int insertSelective(TBCouponOrder record);

    TBCouponOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TBCouponOrder record);

    int updateByPrimaryKey(TBCouponOrder record);
    
    List<TBCouponOrder> selectCouponOrderAllDataByPage(PublicPage page);
    
    Integer selectCouponOrderAllCountByPage(PublicPage page);
}