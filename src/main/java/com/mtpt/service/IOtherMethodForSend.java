package com.mtpt.service;

import java.util.List;

import com.mtpt.alibean.TBRecord;
import com.mtpt.alibean.TBReview;
import com.mtpt.alibean.page.TBRecordPage;
import com.mtpt.bean.DataTotal;
import com.mtpt.bean.Products;
import com.mtpt.bean.RepeatOpera;
import com.mtpt.bean.Review;
import com.mtpt.bean.TBDsjDxAll;
import com.mtpt.bean.TBDsjIceAll;

public interface IOtherMethodForSend {
	List<String> selectThreeday(RepeatOpera repeat);
	
	List<TBDsjDxAll> selectDxByReview(Review review);
	
	List<TBDsjIceAll> selectIceByReview(Review review);
	
	List<TBRecord> selectRecordTaskByAddTime(TBRecordPage page);
	
	List<TBReview>  selectReviewTaskByAddTime(TBRecordPage page);
	
	DataTotal selectDataTotalByAddTime(String addtime);
	
	Products selectProductsDataById(String id);
}
