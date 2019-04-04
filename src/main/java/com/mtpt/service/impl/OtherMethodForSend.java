package com.mtpt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtpt.alibean.TBRecord;
import com.mtpt.alibean.TBReview;
import com.mtpt.alibean.page.TBRecordPage;
import com.mtpt.alidao.TBRecordMapper;
import com.mtpt.alidao.TBReviewMapper;
import com.mtpt.bean.DataTotal;
import com.mtpt.bean.Products;
import com.mtpt.bean.RepeatOpera;
import com.mtpt.bean.Review;
import com.mtpt.bean.TBDsjDxAll;
import com.mtpt.bean.TBDsjIceAll;
import com.mtpt.dao.DataTotalMapper;
import com.mtpt.dao.ProduceMapper;
import com.mtpt.dao.ProductsMapper;
import com.mtpt.dao.TBDsjDxAllMapper;
import com.mtpt.dao.TBDsjIceAllMapper;
import com.mtpt.service.IOtherMethodForSend;

@Service("otherMethodForSend")
public class OtherMethodForSend implements IOtherMethodForSend{

	@Autowired
	private ProduceMapper produceMapper;
	@Autowired
	private TBDsjDxAllMapper dsjdxallMapper;
	@Autowired
	private TBDsjIceAllMapper dsjiceallMapper;
	@Autowired
	private TBRecordMapper recordMapper;
	@Autowired
	private TBReviewMapper reviewMapper;
	@Autowired
	private DataTotalMapper dataTotalMapper;
	@Autowired
	private ProductsMapper productsMapper;
	
	@Override
	public List<String> selectThreeday(RepeatOpera repeat) {
		// TODO Auto-generated method stub
		return produceMapper.selectThreeday(repeat);
	}

	@Override
	public List<TBDsjDxAll> selectDxByReview(Review review) {
		// TODO Auto-generated method stub
		return dsjdxallMapper.selectByReview(review);
	}

	@Override
	public List<TBDsjIceAll> selectIceByReview(Review review) {
		// TODO Auto-generated method stub
		return dsjiceallMapper.selectByReview(review);
	}

	@Override
	public List<TBRecord> selectRecordTaskByAddTime(TBRecordPage page) {
		// TODO Auto-generated method stub
		return recordMapper.selectTaskByAddTime(page);
	}

	@Override
	public List<TBReview> selectReviewTaskByAddTime(TBRecordPage page) {
		// TODO Auto-generated method stub
		return reviewMapper.selectTaskByAddTime(page);
	}

	@Override
	public DataTotal selectDataTotalByAddTime(String addtime) {
		// TODO Auto-generated method stub
		return dataTotalMapper.selectByAddTime(addtime);
	}

	@Override
	public Products selectProductsDataById(String id) {
		// TODO Auto-generated method stub
		return productsMapper.selectByPrimaryKey(id);
	}

}
