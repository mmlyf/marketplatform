package com.mtpt.service;

import java.util.List;

import com.mtpt.bean.TBPermission;

public interface ITBPermissionService {
	int deleteByPrimaryKey(Integer id);

    int insert(TBPermission record);

    int insertSelective(TBPermission record);

    TBPermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TBPermission record);

    int updateByPrimaryKey(TBPermission record);
    
    TBPermission selectByPermissionId(Integer id);
    
    List<TBPermission> selectPermissionData();
}
