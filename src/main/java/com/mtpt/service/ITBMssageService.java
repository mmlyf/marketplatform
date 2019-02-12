package com.mtpt.service;

import java.util.List;

import com.mtpt.bean.TBMssage;
import com.mtpt.bean.page.TBMessagePage;

public interface ITBMssageService {
	int deleteByPrimaryKey(Integer misId);

    int insert(TBMssage record);

    int insertSelective(TBMssage record);

    TBMssage selectByPrimaryKey(Integer misId);

    int updateByPrimaryKeySelective(TBMssage record);

    int updateByPrimaryKeyWithBLOBs(TBMssage record);

    int updateByPrimaryKey(TBMssage record);
    
    List<TBMssage> selectByCustom(TBMessagePage page);
    
    List<TBMssage> selectByAll();
    
    Integer selectByCount(TBMessagePage page);
}
