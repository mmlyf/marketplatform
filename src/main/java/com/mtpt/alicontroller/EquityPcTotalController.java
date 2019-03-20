package com.mtpt.alicontroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mtpt.alibean.TBEquityPcTotal;
import com.mtpt.alibean.TBEquityResult;
import com.mtpt.alibean.page.PublicPage;
import com.mtpt.aliservice.ITBEquityPcTotalService;
import com.mtpt.aliservice.ITBEquityResultService;

/**
 * 
 * @author lvgordon
 * 对抽奖活动的数据统计表格进行展示，并通过json数据回传给前端
 *
 */
@Controller
@RequestMapping("/equityPcTotal")
public class EquityPcTotalController {
	private Logger log = Logger.getLogger(EquityPcTotalController.class);
	@Resource 
	private ITBEquityPcTotalService pcTotalService;
	@Resource 
	private ITBEquityResultService resultService;

	/**
	 * 查询权益统计数据分页展示
	 * @param page
	 * @param response
	 */
	@RequestMapping(value="/selectall",method= {RequestMethod.POST,RequestMethod.GET})
	private void selectAllTotalData(PublicPage page,HttpServletResponse response) {
		Integer totals = pcTotalService.selectAllCount(page);
		page.setTotalRecord(totals);
		List<TBEquityPcTotal> list = pcTotalService.selectAllTotalData(page);
		JSONObject json = new JSONObject();
		List<JSONObject> jsonlist = new ArrayList<>();
		for(TBEquityPcTotal pctotal:list) {
			JSONObject map = new JSONObject();
			map.put("id", pctotal.getId());
			map.put("pvcount", pctotal.getPvCount());
			map.put("bccount", pctotal.getBcCount());
			map.put("youkucount", pctotal.getYoukuCount());
			map.put("aqiycount", pctotal.getAqiyCount());
			map.put("mgcount", pctotal.getMgCount());
			map.put("txcount", pctotal.getTxCount());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String adtime = sdf.format(pctotal.getAddtime());
			map.put("addtime", adtime);
			jsonlist.add(map);
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

	/**
	 * 获取权益统计数据的总和
	 * @param response
	 */
	@RequestMapping(value="/selecttotal",method= {RequestMethod.POST,RequestMethod.GET})
	private void selectAllDataTotal(HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		List<TBEquityPcTotal> list = pcTotalService.selectAllData();
		List<TBEquityResult> resultlist = resultService.selectAllData();
		int pvcounttotal = 0;
		int bccounttotal = 0;
		int ykcounttotal = 0;
		int aqycounttotal = 0 ;
		int mgcounttotal = 0;
		int txcounttotal = 0;

		for(TBEquityPcTotal tbEquityPcTotal:list) {
			pvcounttotal += tbEquityPcTotal.getPvCount();
			bccounttotal += tbEquityPcTotal.getBcCount();
			ykcounttotal += tbEquityPcTotal.getYoukuCount();
			aqycounttotal += tbEquityPcTotal.getAqiyCount();
			mgcounttotal += tbEquityPcTotal.getMgCount();
			txcounttotal += tbEquityPcTotal.getTxCount();
		}
		int ykresultcount = 0;
		int aqiresultcount = 0;
		int mgresultcount = 0;
		int txresultcount = 0;

		for(TBEquityResult tbEquityResult:resultlist) {
			String qynum = tbEquityResult.getQyNum()!=null?tbEquityResult.getQyNum():"";
			if (qynum.equals("")) {
				
			}else {
				switch (qynum) {
				case "1000000000104610"://芒果
					mgresultcount += 1;
					break;
				case "1000000000105003"://腾讯
					txresultcount += 1;
					break;
				case "1000000000105204"://爱奇艺
					aqiresultcount += 1;
					break;
				case "1000000000104609"://优酷
					ykresultcount += 1;
					break;
				default:
					break;
				}
			}
		}

		JSONObject value = new JSONObject();
		value.put("pvtotal", pvcounttotal);
		value.put("bctotal", bccounttotal);
		value.put("yktotal", ykcounttotal);
		value.put("aqytotal", aqycounttotal);
		value.put("mgtotal", mgcounttotal);
		value.put("txtotal", txcounttotal);
		value.put("mgresult", mgresultcount);
		value.put("txresult", txresultcount);
		value.put("aqyresult", aqiresultcount);
		value.put("ykresult", ykresultcount);
		try {
			PrintWriter pw = response.getWriter();
			pw.write(value.toString());
			pw.flush();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
