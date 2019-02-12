package com.mtpt.aliservice.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtpt.alibean.TBRecord;
import com.mtpt.alibean.page.TBRecordPage;
import com.mtpt.alidao.TBRecordMapper;
import com.mtpt.aliservice.ITBRecordService;
@Service("tbrecord")
public class TBRecordService implements ITBRecordService{
	@Autowired
	TBRecordMapper mapper;
	
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(id);
	}

	public int insert(com.mtpt.alibean.TBRecord record) {
		// TODO Auto-generated method stub
		return mapper.insert(record);
	}

	public int insertSelective(com.mtpt.alibean.TBRecord record) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(record);
	}

	public com.mtpt.alibean.TBRecord selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(com.mtpt.alibean.TBRecord record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(com.mtpt.alibean.TBRecord record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKey(record);
	}

	public List<TBRecord> selectByRecordPage(TBRecordPage page) {
		// TODO Auto-generated method stub
		return mapper.selectByRecordPage(page);
	}

	public Integer selectAllCount(TBRecordPage page) {
		// TODO Auto-generated method stub
		return mapper.selectAllCount(page);
	}

	@Override
	public List<TBRecord> selectTaskByAddTime(TBRecordPage page) {
		// TODO Auto-generated method stub
		return mapper.selectTaskByAddTime(page);
	}	
}
