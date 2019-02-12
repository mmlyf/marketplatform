package com.mtpt.dao;

import java.util.List;

import com.mtpt.bean.Review;
import com.mtpt.bean.TBDsjDxAll;

public interface TBDsjDxAllMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(TBDsjDxAll record);

	int insertSelective(TBDsjDxAll record);

	TBDsjDxAll selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(TBDsjDxAll record);

	int updateByPrimaryKey(TBDsjDxAll record);

	List<TBDsjDxAll> selectByReview(Review review);

	Integer selectCountByReview(Review review);
}