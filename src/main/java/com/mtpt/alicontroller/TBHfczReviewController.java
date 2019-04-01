package com.mtpt.alicontroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mtpt.alibean.TBHfczReview;
import com.mtpt.alibean.page.HfczReviewPage;
import com.mtpt.aliservice.ITBHfczReviewService;
import com.mtpt.extend.OtherMethod;

@Controller
@RequestMapping("hfczreview")
public class TBHfczReviewController {
	private Logger log = Logger.getLogger(TBHfczReviewController.class);
	@Resource 
	private ITBHfczReviewService hfczReviewService;
	
	/**
	 * 查询审核数据
	 * @param page
	 * @param response
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/selhfczreview",method= {RequestMethod.GET,RequestMethod.POST})
	private void selectHfczReviewDataByPage(HfczReviewPage page,HttpServletResponse response) throws UnsupportedEncodingException {
		response.setContentType("text/html;charset=utf-8");
		JSONObject json = hfczReviewService.selectHfczReviewDataByPage(page);
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
		JSONObject json = hfczReviewService.updateHfczReviewState(reviewid, state);
		OtherMethod.PrintFlush(response, json);
	}
	
}
