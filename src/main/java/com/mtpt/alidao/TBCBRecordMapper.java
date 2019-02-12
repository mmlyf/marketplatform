package com.mtpt.alidao;

import java.util.List;

import com.mtpt.alibean.TBCBRecord;
import com.mtpt.bean.page.TotalPage;

public interface TBCBRecordMapper {
	int deleteByPrimaryKey(Integer cdId);

    int insert(TBCBRecord record);

    int insertSelective(TBCBRecord record);

    TBCBRecord selectByPrimaryKey(Integer cdId);

    int updateByPrimaryKeySelective(TBCBRecord record);

    int updateByPrimaryKey(TBCBRecord record);
    
    Integer selectCountByPage(TotalPage page);
    
    int insertByList(List<TBCBRecord> list);
}