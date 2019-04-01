package com.mtpt.controller;

import java.text.SimpleDateFormat;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mtpt.alibean.TBRecord;
import com.mtpt.alibean.TBReview;
import com.mtpt.bean.Review;
import com.mtpt.service.ISMSTaskCreateService;
/**
 * 
 * @author lvgordon
 * 任务添加控制器
 * 
 * 
 */
@Controller
@RequestMapping("/smscreate")
public class SMSTaskCreateController {
	private Logger log = Logger.getLogger(SMSTaskCreateController.class);
	
	@Resource
	private ISMSTaskCreateService ismsTaskCreateService;
	
	private HttpSession session = null;
	SimpleDateFormat sdf = null;
	/**
	 * 
	 * @param file_stu
	 * @param title
	 * @param istimework
	 * @param runtime
	 * @param reviewman
	 * @param upname
	 * @return
	 * 将文件导入方式的一些基础信息保存至表中，此表为文件导入的用户群组表
	 * 
	 */
	@RequestMapping(value="/filein",method = {RequestMethod.POST,RequestMethod.GET})
	private String smsTaskFileIn(@RequestParam MultipartFile file_stu,
			TBRecord tbRecord,
			HttpServletResponse response,HttpServletRequest request,Integer migId1) {
		response.setContentType("text/html; charset=UTF-8");
		log.info(tbRecord.getAddman()+"创建文件导入的任务为"+tbRecord.getGroupname());
		String sucres = ismsTaskCreateService.smsTaskFileIn(file_stu, tbRecord, migId1);
		return sucres;
	}
	
	/**
	 * 
	 * @param tbReview
	 * @return
	 * 保存数据维度的数据
	 * 
	 */
	@RequestMapping(value="/modelIn",method = {RequestMethod.POST,RequestMethod.GET})
	private String smsTaskModelIn(TBReview tbReview,Review review) {
		
		log.info(tbReview.getRdUser()+"创建维度筛选任务");
		String sucres = ismsTaskCreateService.smsTaskModelIn(tbReview, review);
		return sucres;
	}
}


