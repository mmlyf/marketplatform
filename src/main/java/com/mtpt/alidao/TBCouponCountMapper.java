package com.mtpt.alidao;

import com.mtpt.alibean.TBCouponCount;

public interface TBCouponCountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TBCouponCount record);

    int insertSelective(TBCouponCount record);

    TBCouponCount selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TBCouponCount record);

    int updateByPrimaryKey(TBCouponCount record);
}