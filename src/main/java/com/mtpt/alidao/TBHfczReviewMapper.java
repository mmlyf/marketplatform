package com.mtpt.alidao;

import java.util.List;

import com.mtpt.alibean.TBHfczReview;
import com.mtpt.alibean.page.HfczReviewPage;

public interface TBHfczReviewMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TBHfczReview record);

    int insertSelective(TBHfczReview record);

    TBHfczReview selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TBHfczReview record);

    int updateByPrimaryKey(TBHfczReview record);
    
    List<TBHfczReview> selectReviewDataByPage(HfczReviewPage page);

	Integer selectReviewCountByPage(HfczReviewPage page);
}