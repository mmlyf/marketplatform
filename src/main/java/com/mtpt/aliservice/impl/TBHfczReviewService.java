package com.mtpt.aliservice.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtpt.alibean.TBHfczReview;
import com.mtpt.alibean.page.HfczReviewPage;
import com.mtpt.alidao.TBHfczReviewMapper;
import com.mtpt.aliservice.ITBHfczReviewService;

@Service("hfczreviewService")
public class TBHfczReviewService  implements ITBHfczReviewService{

	@Autowired
	private TBHfczReviewMapper mapper;
	
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(TBHfczReview record) {
		// TODO Auto-generated method stub
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(TBHfczReview record) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(record);
	}

	@Override
	public TBHfczReview selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(TBHfczReview record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(TBHfczReview record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public List<TBHfczReview> selectReviewDataByPage(HfczReviewPage page) {
		// TODO Auto-generated method stub
		return mapper.selectReviewDataByPage(page);
	}

	@Override
	public Integer selectReviewCountByPage(HfczReviewPage page) {
		// TODO Auto-generated method stub
		return mapper.selectReviewCountByPage(page);
	}

}
