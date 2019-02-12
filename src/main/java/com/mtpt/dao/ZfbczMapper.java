package com.mtpt.dao;

import java.util.List;

import com.mtpt.bean.Zfbcz;
import com.mtpt.bean.page.PublicLocalPage;
import com.mtpt.bean.page.ZfbczPage;

public interface ZfbczMapper {
    int insert(Zfbcz record);

    int insertSelective(Zfbcz record);
    
    List<Zfbcz> selectAllData(ZfbczPage page);
    
    Integer selectAllDataCount();
}