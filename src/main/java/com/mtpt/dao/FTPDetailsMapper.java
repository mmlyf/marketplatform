package com.mtpt.dao;

import java.util.List;

import com.mtpt.bean.FTPDetails;
import com.mtpt.bean.TBUsers;
import com.mtpt.bean.page.AlipayPage;

public interface FTPDetailsMapper {
    int deleteByPrimaryKey(String id);

    int insert(FTPDetails record);

    int insertSelective(FTPDetails record);

    FTPDetails selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FTPDetails record);

    int updateByPrimaryKeyWithBLOBs(FTPDetails record);

    int updateByPrimaryKey(FTPDetails record);
    
 List<FTPDetails> selectByAlipayPage(AlipayPage page);
    
    int selectByCount(AlipayPage page);
}