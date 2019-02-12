package com.mtpt.service;

import java.util.List;

import com.mtpt.bean.CustomService;
import com.mtpt.bean.page.IceBookPage;

public interface ICustomService {
	int deleteByPrimaryKey(Integer dxId);

    int insert(CustomService record);

    int insertSelective(CustomService record);

    CustomService selectByPrimaryKey(Integer dxId);

    int updateByPrimaryKeySelective(CustomService record);

    int updateByPrimaryKey(CustomService record);

    List<CustomService> selectIceDataByPage(IceBookPage page);

    Integer selectIceDataCountByPage(IceBookPage page);
    
    List<CustomService> selectAllCsDataByPage(IceBookPage page);
}
