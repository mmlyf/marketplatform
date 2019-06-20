package com.mtpt.hkalidao;

import com.mtpt.hkalibean.TBCouponAddr;

public interface TBCouponAddrMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TBCouponAddr record);

    int insertSelective(TBCouponAddr record);

    TBCouponAddr selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TBCouponAddr record);

    int updateByPrimaryKeyWithBLOBs(TBCouponAddr record);

    int updateByPrimaryKey(TBCouponAddr record);
}