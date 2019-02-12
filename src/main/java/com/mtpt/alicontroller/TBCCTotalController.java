package com.mtpt.alicontroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mtpt.alibean.TBCCTotal;
import com.mtpt.alibean.page.PublicPage;
import com.mtpt.aliservice.ITBCCTotalService;

@Controller
@RequestMapping("/tbccTotal")
public class TBCCTotalController {
	@Resource
	private ITBCCTotalService totalService;
	@RequestMapping(value="/selectall",method= {RequestMethod.POST,RequestMethod.GET})
	private void selectAllDataByPage(PublicPage page,HttpServletRequest request,HttpServletResponse response) {
		Integer totals = totalService.selectAllDataCount(page);
		page.setTotalRecord(totals);
		List<TBCCTotal> list = totalService.selectAllDataByPage(page);
		JSONObject json = new JSONObject();
		List<JSONObject> jsonlist = new ArrayList<>();
		int count = 1;
		for(TBCCTotal tbccTotal: list) {
			JSONObject value = new JSONObject();
			value.put("id", count);
			value.put("b_pv", tbccTotal.getbPv());
			value.put("li_allbc", tbccTotal.getLiAllbc());
			value.put("tan_seebc", tbccTotal.getTanSeebc());
			value.put("tan_seeicepv", tbccTotal.getTanSeeicepv());
			value.put("tan_seedxpv", tbccTotal.getTanSeedxpv());
			value.put("tanli_icebc", tbccTotal.getTanliIcebc());
			value.put("tanli_dxbc", tbccTotal.getTanliDxbc());
			value.put("ccc_pv", tbccTotal.getCccPv());
			value.put("li_npccpv", tbccTotal.getLiNpccpv());
			value.put("see_icepv", tbccTotal.getSeeIcepv());
			value.put("see_dxpv", tbccTotal.getSeeDxpv());
			value.put("see_llbpv", tbccTotal.getSeeLlbpv());
			value.put("ice_bookc", tbccTotal.getIceBookc());
			value.put("dx_ordersucc", tbccTotal.getDxOrdersucc());
			value.put("dx_ordersunsucc", tbccTotal.getDxOrdersunsucc());
			value.put("six_orderc", tbccTotal.getSixOrderc());
			value.put("nine_orderc", tbccTotal.getNineOrderc());
			value.put("sm_sendc", tbccTotal.getSmSendc());
			value.put("muti_bc", tbccTotal.getMutiBc());
			value.put("tehui_bc", tbccTotal.getTehuiBc());
			value.put("addtime", tbccTotal.getAddtime());
			jsonlist.add(value);
			count++;
		}
		json.put("code", 0);
		json.put("msg", "");
		json.put("count", totals);
		json.put("data", jsonlist);
		try {
			PrintWriter pw = response.getWriter();
			pw.write(json.toString());
			pw.flush();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/selecttotal",method= {RequestMethod.POST,RequestMethod.GET})
	private void selectAllDataTotal(HttpServletResponse response) {
		List<TBCCTotal> list = totalService.selectAllData();
		int bpv = 0;
		int liallbc = 0;
		int tanseebc = 0;
		int 	tanseeicepv = 0;
		int tanseedxpv = 0;
		int tanliicebc = 0;
		int tanlidxbc = 0;
		int cccpv = 0;
		int linpccpv = 0;
		int seeicepv = 0;
		int seedxpv = 0;
		int seellbpv = 0;
		int icebookc = 0;
		int dxordersucc = 0;
		int dxordersunsucc = 0;
		int sixorderc = 0;
		int nineorderc = 0;
		int smsendc = 0;
		int mutibc = 0;
		int tehuibc = 0;
		JSONObject json = new JSONObject();
		for (TBCCTotal tbccTotal: list) {
			bpv += tbccTotal.getbPv();
			liallbc += tbccTotal.getLiAllbc();
			tanseebc += tbccTotal.getTanSeebc();
			tanseeicepv += tbccTotal.getTanSeeicepv();
			tanseedxpv += tbccTotal.getTanSeedxpv();
			tanliicebc += tbccTotal.getTanliIcebc();
			tanlidxbc += tbccTotal.getTanliDxbc();
			cccpv += tbccTotal.getCccPv();
			linpccpv += tbccTotal.getLiNpccpv();
			seeicepv += tbccTotal.getSeeIcepv();
			seedxpv += tbccTotal.getSeeDxpv();
			seellbpv += tbccTotal.getSeeLlbpv();
			icebookc += tbccTotal.getIceBookc();
			dxordersucc += tbccTotal.getDxOrdersucc();
			dxordersunsucc += tbccTotal.getDxOrdersunsucc();
			sixorderc += tbccTotal.getSixOrderc();
			nineorderc += tbccTotal.getNineOrderc();
			smsendc += tbccTotal.getSmSendc();
			mutibc += tbccTotal.getMutiBc();
			tehuibc += tbccTotal.getTehuiBc();
		}
		json.put("bpv", bpv);
		json.put("liallbc", liallbc);
		json.put("tanseebc", tanseebc);
		json.put("tanseeicepv", tanseeicepv);
		json.put("tanseedxpv", tanseedxpv);
		json.put("tanliicebc", tanliicebc);
		json.put("tanlidxbc", tanlidxbc);
		json.put("cccpv", cccpv);
		json.put("linpccpv", linpccpv);
		json.put("seeicepv", seeicepv);
		json.put("seedxpv", seedxpv);
		json.put("seellbpv", seellbpv);
		json.put("icebookc", icebookc);
		json.put("dxordersucc", dxordersucc);
		json.put("dxorderunsucc", dxordersunsucc);
		json.put("sixorderc", sixorderc);
		json.put("nineorderc", nineorderc);
		json.put("smsendc", smsendc);
		json.put("mutibc", mutibc);
		json.put("tehuibc", tehuibc);
		try {
			PrintWriter pw = response.getWriter();
			pw.write(json.toString());
			pw.flush();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
