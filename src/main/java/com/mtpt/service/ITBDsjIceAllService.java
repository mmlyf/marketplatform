package com.mtpt.service;

import java.util.List;

import com.mtpt.alibean.TBReview;
import com.mtpt.bean.Review;
import com.mtpt.bean.TBDsjIceAll;

public interface ITBDsjIceAllService {
	int deleteByPrimaryKey(Integer id);

    int insert(TBDsjIceAll record);

    int insertSelective(TBDsjIceAll record);

    TBDsjIceAll selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TBDsjIceAll record);

    int updateByPrimaryKey(TBDsjIceAll record);
    
    List<TBDsjIceAll> selectByReview(Review review);
    
    Integer selectCountByReview(Review review);
}
