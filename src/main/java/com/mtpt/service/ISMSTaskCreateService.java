package com.mtpt.service;

import org.springframework.web.multipart.MultipartFile;

import com.mtpt.alibean.TBRecord;
import com.mtpt.alibean.TBReview;
import com.mtpt.bean.Review;

public interface ISMSTaskCreateService {
	String smsTaskFileIn(MultipartFile file_stu,TBRecord tbRecord,Integer migId1);
	
	String smsTaskModelIn(TBReview tbReview,Review review);
	
	
}
