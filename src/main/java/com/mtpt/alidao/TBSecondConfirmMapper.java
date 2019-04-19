package com.mtpt.alidao;

import com.mtpt.alibean.TBSecondConfirm;

public interface TBSecondConfirmMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TBSecondConfirm record);

    int insertSelective(TBSecondConfirm record);

    TBSecondConfirm selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TBSecondConfirm record);

    int updateByPrimaryKey(TBSecondConfirm record);
    
    TBSecondConfirm selectConfirmDataByAgw(String agw);
}