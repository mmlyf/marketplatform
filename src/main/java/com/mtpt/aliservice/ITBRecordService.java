package com.mtpt.aliservice;

import java.util.List;

import com.mtpt.alibean.TBRecord;
import com.mtpt.alibean.page.TBRecordPage;

public interface ITBRecordService {
	int deleteByPrimaryKey(Integer id);

    int insert(TBRecord record);

    int insertSelective(TBRecord record);

    TBRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TBRecord record);

    int updateByPrimaryKey(TBRecord record);
    
    List<TBRecord> selectByRecordPage(TBRecordPage page);
    
    Integer selectAllCount(TBRecordPage page);
    
    List<TBRecord> selectTaskByAddTime(TBRecordPage page);
}
