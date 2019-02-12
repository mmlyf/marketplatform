package com.mtpt.service;

import java.util.List;

import com.mtpt.bean.Zfbcz;
import com.mtpt.bean.page.PublicLocalPage;
import com.mtpt.bean.page.ZfbczPage;

public interface IZfbczService {
	int insert(Zfbcz record);

	int insertSelective(Zfbcz record);

	List<Zfbcz> selectAllData(ZfbczPage page);

	Integer selectAllDataCount();
}
