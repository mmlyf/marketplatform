package com.mtpt.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtpt.bean.TBMssage;
import com.mtpt.bean.TBProd;
import com.mtpt.bean.TBProdDw;
import com.mtpt.bean.TBProdLx;
import com.mtpt.dao.TBMssageMapper;
import com.mtpt.dao.TBProdDwMapper;
import com.mtpt.dao.TBProdLxMapper;
import com.mtpt.dao.TBProdMapper;
import com.mtpt.service.IRequestDataForFormDataService;

@Service("requestDataForFromDataService")
public class RequestDataForFromDataService implements IRequestDataForFormDataService {

	@Autowired
	private TBProdDwMapper prodDwMapper;
	@Autowired 
	private TBProdLxMapper prodLxMapper;
	@Autowired
	private TBProdMapper prodmapper;
	@Autowired
	private TBMssageMapper tbmssageMapper;
	
	@Override
	public JSONObject selectProdData(Integer prodid, Integer prodlxid) {
		// TODO Auto-generated method stub
		List<JSONObject> listjson = new ArrayList<JSONObject>();
		JSONObject datajson = new JSONObject();
		if (prodid==null&&prodlxid==null) {
			List<TBProdLx> lxList = prodLxMapper.selectByAll();
			for(TBProdLx tbProdLx :lxList) {
				JSONObject jsonlx = new JSONObject();
				jsonlx.put("lxid", tbProdLx.getLxid());
				jsonlx.put("lxname", tbProdLx.getLxname());
				jsonlx.put("lxvalue", tbProdLx.getLxvalue());
				listjson.add(jsonlx);
			}
		}else if(prodid==null&&prodlxid!=null) {
			List<TBProd> prolist = prodmapper.selectByLxid(prodlxid);
			for(TBProd tbProd :prolist) {
				JSONObject jsonpro = new JSONObject();
				jsonpro.put("proid", tbProd.getProid());
				jsonpro.put("proname", tbProd.getProname());
				listjson.add(jsonpro);
			}
		}else if(prodid != null&&prodlxid==null) {
			List<TBProdDw> dwList = prodDwMapper.selectByProid(prodid);
			for(TBProdDw tbProdDw:dwList) {
				JSONObject jsondw = new JSONObject();
				jsondw.put("dw", tbProdDw.getProDw());
				listjson.add(jsondw);
			}
		}
		datajson.put("data", listjson.toArray());
		return datajson;
	}

	@Override
	public JSONObject selectMessageData() {
		// TODO Auto-generated method stub
		List<JSONObject> jsonlist = new ArrayList<JSONObject>();
		List<TBMssage> msglist = tbmssageMapper.selectByAll();
		JSONObject jsondata = new JSONObject();
		for(int i = 0;i<msglist.size();i++) {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("msgid", msglist.get(i).getMisId());
			jsonmsg.put("msgtitle", msglist.get(i).getMisTitle());
			jsonlist.add(jsonmsg);
		}
		jsondata.put("data", jsonlist);
		return jsondata;
	}

}
