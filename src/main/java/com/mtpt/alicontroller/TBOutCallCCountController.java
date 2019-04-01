package com.mtpt.alicontroller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mtpt.alibean.page.PublicPage;
import com.mtpt.aliservice.ITBOutCallCCountService;
import com.mtpt.extend.OtherMethod;

@Controller
@RequestMapping("/outcall")
public class TBOutCallCCountController {
	private Logger log = Logger.getLogger(TBOutCallCCountController.class);
	@Resource
	private ITBOutCallCCountService outCallCCountService;
	
	/**
	 * 查询外呼的信用卡页面浏览数
	 * @param page
	 * @param response
	 */
	@RequestMapping(value="/select",method= {RequestMethod.GET,RequestMethod.POST})
	private void selectOutCallPvCount(PublicPage page,HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		JSONObject json = outCallCCountService.selectOutCallPvCount(page);
		OtherMethod.PrintFlush(response, json);
	}
	
	/**
	 * 获取通建外呼信用卡页面的PV数据
	 * @param page
	 * @param response
	 */
	@CrossOrigin
	@RequestMapping(value="/seltjpfpv",method= {RequestMethod.POST,RequestMethod.GET})
	private void selectTongjianPfPvcountData(PublicPage page,HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		JSONObject json = outCallCCountService.selectTongjianPfPvcountData(page);
		OtherMethod.PrintFlush(response, json);
	}
	
	/**
	 * 获取广发外呼信用卡页面的PV数据
	 * @param page
	 * @param response
	 */
	@CrossOrigin
	@RequestMapping(value="/selgfpv",method= {RequestMethod.POST,RequestMethod.GET})
	private void selectGFCCPvcountData(PublicPage page,HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		JSONObject json = outCallCCountService.selectGFCCPvcountData(page);
		OtherMethod.PrintFlush(response, json);
	}
	
	/**
	 * 颢闵的写字楼项目 MORE东兰活动数据的获取
	 * @param page
	 * @param response
	 */
	@CrossOrigin
	@RequestMapping(value="/caselan",method= {RequestMethod.POST,RequestMethod.GET})
	private void selectMoreCaseLanPvcountData(PublicPage page,HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		JSONObject json = outCallCCountService.selectMoreCaseLanPvcountData(page);
		OtherMethod.PrintFlush(response, json);
	}
	
	/**
	 * 颢明的商业项目 叫 MORE（名品商厦）的页面浏览数据的获取
	 * @param page
	 * @param response
	 */
	@CrossOrigin
	@RequestMapping(value="/casepin",method= {RequestMethod.POST,RequestMethod.GET})
	private void selectMoreCasePinPvcountData(PublicPage page,HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		JSONObject json = outCallCCountService.selectMoreCasePinPvcountData(page);
		OtherMethod.PrintFlush(response, json);
	}
}
