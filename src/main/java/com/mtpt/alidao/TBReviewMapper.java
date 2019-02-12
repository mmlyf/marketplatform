package com.mtpt.alidao;

import java.util.List;

import com.mtpt.alibean.TBReview;
import com.mtpt.alibean.page.TBRecordPage;

public interface TBReviewMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TBReview record);

    int insertSelective(TBReview record);

    TBReview selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TBReview record);

    int updateByPrimaryKey(TBReview record);
    
    List<TBReview> selectByReviewPage(TBRecordPage page);
    
    Integer selectCountAll(TBRecordPage page);
    
    List<TBReview> selectTaskByAddTime(TBRecordPage page);

}