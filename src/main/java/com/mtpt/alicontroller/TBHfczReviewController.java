package com.mtpt.alicontroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mtpt.alibean.TBHfczReview;
import com.mtpt.alibean.page.HfczReviewPage;
import com.mtpt.aliservice.ITBHfczReviewService;
import com.mtpt.bean.Zfbcz;
import com.mtpt.extend.OtherMethod;
import com.mtpt.service.IZfbczService;

@Controller
@RequestMapping("hfczreview")
public class TBHfczReviewController {
	@Resource 
	private ITBHfczReviewService hfczReviewService;
	@Resource 
	private IZfbczService zfbczService;
	
	/**
	 * 查询审核数据
	 * @param page
	 * @param response
	 */
	@RequestMapping(value="/selhfczreview",method= {RequestMethod.GET,RequestMethod.POST})
	private void selectHfczReviewDataByPage(HfczReviewPage page,HttpServletResponse response) {
		int totals = hfczReviewService.selectReviewCountByPage(page);
		page.setTotalRecord(totals);
		List<TBHfczReview> list = hfczReviewService.selectReviewDataByPage(page);
		JSONObject json = new JSONObject();
		List<JSONObject> jsonlist = new ArrayList<>();
		for (TBHfczReview tbHfczReview : list) {
			JSONObject value = new JSONObject();
			value.put("id", tbHfczReview.getId());
			value.put("seno", tbHfczReview.getSeNo()!=null?tbHfczReview.getSeNo():"");
			value.put("czdn", tbHfczReview.getCzDn());	
			value.put("czaddtime", tbHfczReview.getCzAddtime());
			value.put("czamount", tbHfczReview.getCzAmount());
			value.put("czreviewer", tbHfczReview.getCzReviewer());
			value.put("czreason", tbHfczReview.getCzReason());
			String state = "";
			switch (tbHfczReview.getCzReviewstate()) {
			case 0:
				state = "未审核";
				break;
			case 1:
				state = "审核通过";
				break;
			case 2:
				state = "审核不通过";
				break;
			default:
				break;
			}
			value.put("reviewstate", state);
			value.put("addman", tbHfczReview.getCzAddman());
			jsonlist.add(value);
		}
		json.put("code", 0);
		json.put("msg", "");
		json.put("count", totals);
		json.put("data", jsonlist);
		OtherMethod.PrintFlush(response, json);
	}
	
	/**
	 * 更新审核状态
	 * @param reviewid
	 * @param state
	 * @param response
	 */
	@RequestMapping(value="uphfczstate",method= {RequestMethod.GET,RequestMethod.POST})
	private void updateHfczReviewState(String reviewid,String state,HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		JSONObject json = new JSONObject();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String py_peid = "H"+sdf.format(date)+"-"+(11+(int)(Math.random()*89))+"20180730"+(1+(int)(Math.random()*9));
		Zfbcz zfbcz = new Zfbcz();
		if (reviewid==null||state==null||reviewid.equals("")||state.equals("")) {
			json.put("code", 1);
			json.put("msg", "缺少重要参数，请重试！");
		}else {
			Integer reid = Integer.parseInt(reviewid);
			Integer restate = Integer.parseInt(state);
			TBHfczReview tbHfczReview = new TBHfczReview();
			tbHfczReview.setCzReviewstate(restate);
			tbHfczReview.setId(reid);
			tbHfczReview.setSeNo(py_peid);
			int upres = hfczReviewService.updateByPrimaryKeySelective(tbHfczReview);
			if (upres>0) {
				if (restate==1) {
					TBHfczReview hfczReview = hfczReviewService.selectByPrimaryKey(reid);
					double totalmoney = hfczReview.getCzAmount();
					String id = OtherMethod.getUuid();
					zfbcz.setPyId(id);
					zfbcz.setPyCreatime(date);
					zfbcz.setPyIfpay(1);
					zfbcz.setPyZfqd(3);
					zfbcz.setPyDn(hfczReview.getCzDn());
					zfbcz.setPyTotalmoey(BigDecimal.valueOf(totalmoney));
					String pyLastday = OtherMethod.addYearTime(date);
					zfbcz.setPyLastday(pyLastday);
					zfbcz.setPySeno(py_peid);
					int result = zfbczService.insertSelective(zfbcz);
					if (result>0) {
						json.put("code", 0);
						json.put("seno", py_peid);
					}else {
						json.put("code", 1);
						json.put("seno", "");
					}
				}else{
					json.put("code", 0);
					json.put("msg", "当前状态为审核不通过！");
				}
			}else {
				json.put("code", 1);
				json.put("msg", "审核失败！");
			}
		}
		OtherMethod.PrintFlush(response, json);
	}
	
}
