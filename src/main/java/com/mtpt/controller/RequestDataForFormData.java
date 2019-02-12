package com.mtpt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mtpt.bean.TBMssage;
import com.mtpt.bean.TBProd;
import com.mtpt.bean.TBProdDw;
import com.mtpt.bean.TBProdLx;
import com.mtpt.service.ITBMssageService;
import com.mtpt.service.ITBProService;
import com.mtpt.service.ITBProdDwService;
import com.mtpt.service.ITBProdLxService;

@Controller
@RequestMapping("/requestdata")
public class RequestDataForFormData {
	private Logger log = Logger.getLogger(RequestDataForFormData.class);
	
	@Resource
	ITBProdDwService dwservice;
	@Resource
	ITBProdLxService lxservice;
	@Resource
	ITBProService proservice;
	@Resource
	ITBMssageService mssageservice;

	@RequestMapping(value="/selectprod",method = {RequestMethod.POST,RequestMethod.GET})
	private void selectProdData(HttpServletResponse response,Integer prodid,Integer prodlxid) {
		log.debug("proid的值是："+prodid+"以及prodlxid:"+prodlxid);
		List<JSONObject> listjson = new ArrayList<JSONObject>();
		JSONObject datajson = new JSONObject();
		if (prodid==null&&prodlxid==null) {
			List<TBProdLx> lxList = lxservice.selectByAll();
			for(TBProdLx tbProdLx :lxList) {
				JSONObject jsonlx = new JSONObject();
				jsonlx.put("lxid", tbProdLx.getLxid());
				jsonlx.put("lxname", tbProdLx.getLxname());
				jsonlx.put("lxvalue", tbProdLx.getLxvalue());
				listjson.add(jsonlx);
			}
		}else if(prodid==null&&prodlxid!=null) {
			List<TBProd> prolist = proservice.selectByLxid(prodlxid);
			for(TBProd tbProd :prolist) {
				JSONObject jsonpro = new JSONObject();
				jsonpro.put("proid", tbProd.getProid());
				jsonpro.put("proname", tbProd.getProname());
				listjson.add(jsonpro);
			}
		}else if(prodid != null&&prodlxid==null) {
			List<TBProdDw> dwList = dwservice.selectByProid(prodid);
			for(TBProdDw tbProdDw:dwList) {
				JSONObject jsondw = new JSONObject();
				jsondw.put("dw", tbProdDw.getProDw());
				listjson.add(jsondw);
			}
		}
		datajson.put("data", listjson.toArray());
		response.setContentType("text/html; charset=UTF-8");
		try {
			PrintWriter pw = response.getWriter();
			pw.write(datajson.toString());
			pw.flush();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/selectmsg",method = {RequestMethod.POST,RequestMethod.GET})
	private void selectMessageData(HttpServletResponse response) {
		List<JSONObject> jsonlist = new ArrayList<JSONObject>();
		List<TBMssage> msglist = mssageservice.selectByAll();
		JSONObject jsondata = new JSONObject();
		log.debug("msglist的值是："+msglist.get(0).getMisTitle());
		for(int i = 0;i<msglist.size();i++) {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("msgid", msglist.get(i).getMisId());
			jsonmsg.put("msgtitle", msglist.get(i).getMisTitle());
			jsonlist.add(jsonmsg);
		}
		jsondata.put("data", jsonlist);
		response.setContentType("text/html; charset=UTF-8");
		try {
			PrintWriter pw = response.getWriter();
			pw.write(jsondata.toString());
			pw.flush();
			pw.close();pw.write(jsondata.toString());
			pw.flush();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
