package com.mtpt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtpt.alibean.TBReview;
import com.mtpt.bean.Review;
import com.mtpt.bean.TBDsjIceAll;
import com.mtpt.dao.TBDsjIceAllMapper;
import com.mtpt.service.ITBDsjIceAllService;
@Service("iceservice")
public class TBDsjIceAllService implements ITBDsjIceAllService{
	
	@Autowired
	TBDsjIceAllMapper mapper;
	
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(id);
	}

	public int insert(TBDsjIceAll record) {
		// TODO Auto-generated method stub
		return mapper.insert(record);
	}

	public int insertSelective(TBDsjIceAll record) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(record);
	}

	public TBDsjIceAll selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(TBDsjIceAll record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(TBDsjIceAll record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKey(record);
	}

	public List<TBDsjIceAll> selectByReview(Review review) {
		// TODO Auto-generated method stub
		return mapper.selectByReview(review);
	}

	public Integer selectCountByReview(Review review) {
		// TODO Auto-generated method stub
		return mapper.selectCountByReview(review);
	}

}
