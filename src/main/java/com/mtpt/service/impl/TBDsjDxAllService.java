package com.mtpt.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtpt.alibean.TBReview;
import com.mtpt.bean.Review;
import com.mtpt.bean.TBDsjDxAll;
import com.mtpt.dao.TBDsjDxAllMapper;
import com.mtpt.service.ITBDsjDxAllService;

import sun.util.logging.resources.logging;
@Service("dxservice")
public class TBDsjDxAllService implements ITBDsjDxAllService {
	private Logger log = Logger.getLogger(TBDsjDxAllService.class);
	static {
		PropertyConfigurator.configure(Thread.currentThread().getContextClassLoader().getResource("log.properties").getPath());
		
	}
	@Autowired
	TBDsjDxAllMapper mapper;

	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(id);
	}

	public int insert(TBDsjDxAll record) {
		// TODO Auto-generated method stub
		return mapper.insert(record);
	}

	public int insertSelective(TBDsjDxAll record) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(record);
	}

	public TBDsjDxAll selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(TBDsjDxAll record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(TBDsjDxAll record) {
		// TODO Auto-generated method stub
		return updateByPrimaryKey(record);
	}

	public List<TBDsjDxAll> selectByReview(Review review) {
		// TODO Auto-generated method stub
		return mapper.selectByReview(review);
	}

	public Integer selectCountByReview(Review review) {
		// TODO Auto-generated method stub
		log.info("service中的时间值是："+review.getSecTime());
		return mapper.selectCountByReview(review);
	}

}
