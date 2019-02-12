package com.mtpt.dao;

import java.util.List;

import com.mtpt.bean.RepeatOpera;
import com.mtpt.bean.Review;
import com.mtpt.bean.TBDsjDxAll;
import com.mtpt.bean.TBDsjIceAll;

public interface ProduceMapper {
	Integer selectModelCount(Review review);
	
	List<String> selectThreeday(RepeatOpera repeatOpera);
	
	List<TBDsjDxAll> selectDxByPhoneDetails(String phonenum);
	
	List<TBDsjIceAll> selectIceByPhoneDetails(String phonenum);
	
	Integer selectDxCount(String phonenum);
	
	Integer selectIceCount(String phonenum);
}
