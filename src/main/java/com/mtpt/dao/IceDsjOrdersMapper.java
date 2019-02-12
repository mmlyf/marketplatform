package com.mtpt.dao;

import java.util.List;

import com.mtpt.bean.IceDsjOrders;
import com.mtpt.bean.page.IceBookPage;
import com.mtpt.bean.page.TotalPage;

public interface IceDsjOrdersMapper {
    int deleteByPrimaryKey(Integer dxId);

    int insert(IceDsjOrders record);

    int insertSelective(IceDsjOrders record);

    IceDsjOrders selectByPrimaryKey(Integer dxId);

    int updateByPrimaryKeySelective(IceDsjOrders record);

    int updateByPrimaryKey(IceDsjOrders record);
    
    Integer selectCountByPage(TotalPage page);
    
    List<IceDsjOrders> selectIceDataByPage(IceBookPage page);
    
    Integer selectIceDataCountByPage(IceBookPage page);
    
    List<IceDsjOrders> selectIceDataAllByPage(IceBookPage page);//查询所有数据不分页
}