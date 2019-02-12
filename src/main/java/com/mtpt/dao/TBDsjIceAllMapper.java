package com.mtpt.dao;

import java.util.List;

import com.mtpt.bean.Review;
import com.mtpt.bean.TBDsjIceAll;

public interface TBDsjIceAllMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TBDsjIceAll record);

    int insertSelective(TBDsjIceAll record);

    TBDsjIceAll selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TBDsjIceAll record);

    int updateByPrimaryKey(TBDsjIceAll record);
    
    List<TBDsjIceAll> selectByReview(Review review);
    
    Integer selectCountByReview(Review review);
}