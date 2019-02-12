package com.mtpt.service;

import java.util.List;

import com.mtpt.bean.TBUsers;
import com.mtpt.bean.page.AlipayPage;

public interface ITBUsersService {
	int deleteByPrimaryKey(String id);

    int insert(TBUsers record);

    int insertSelective(TBUsers record);

    TBUsers selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TBUsers record);

    int updateByPrimaryKey(TBUsers record);
    
    List<TBUsers> selectByAlipayPage(AlipayPage page);
    
    int selectByCount(AlipayPage page);
    
    List<TBUsers> selectAllAlipayUser();
    
    List<TBUsers> selectUnGiftFlowUser(AlipayPage page);
}
