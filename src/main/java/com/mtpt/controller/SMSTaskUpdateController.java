package com.mtpt.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.json.Json;
import javax.print.attribute.standard.MediaSize.Other;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.HTTP;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mtpt.alibean.TBRecord;
import com.mtpt.alibean.TBReview;
import com.mtpt.alibean.page.TBRecordPage;
import com.mtpt.alibean.page.TBReviewPage;
import com.mtpt.aliservice.ITBRecordService;
import com.mtpt.aliservice.ITBReviewService;
import com.mtpt.bean.Review;
import com.mtpt.bean.TBMssage;
import com.mtpt.bean.TBProd;
import com.mtpt.bean.TBProdLx;
import com.mtpt.bean.TBState;
import com.mtpt.extend.OtherMethod;
import com.mtpt.extend.OutputFile;
import com.mtpt.service.ISMSTaskUpdateService;


@Controller
@RequestMapping("/smsupdate")
public class SMSTaskUpdateController {
	private Logger log = Logger.getLogger(SMSTaskUpdateController.class);

	@Resource
	private ISMSTaskUpdateService ismsTaskUpdateService;

	SimpleDateFormat sdf = null;
	/**
	 * 
	 * @param response
	 * 读取文件导入时创建的用户群组，此方法包括读取全部的数据以及通过群组名称进行筛选的群组值
	 * @throws UnsupportedEncodingException 
	 * 
	 */
	@RequestMapping(value="/getfiledata",method = {RequestMethod.POST,RequestMethod.GET})
	private void getFileInData(TBRecordPage tbRecordPage,
			HttpServletResponse response,
			HttpServletRequest request) throws UnsupportedEncodingException {
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("realname");
		log.info(name+"访问下发管理中的\"文件导入\"任务列表");
		if (tbRecordPage.getKeyword()!=null&&!tbRecordPage.getKeyword().equals("")) {
			String decodekeyword = URLDecoder.decode(tbRecordPage.getKeyword(),"utf-8");
			log.debug("keyword的值是："+decodekeyword);
			tbRecordPage.setKeyword(decodekeyword);
		}
		if (tbRecordPage.getKeyid()!=null&&!tbRecordPage.getKeyid().equals("")) {
			String decodekeyid = URLDecoder.decode(tbRecordPage.getKeyid(),"utf-8");
			log.debug("keyid的值是："+decodekeyid);
			tbRecordPage.setKeyid(decodekeyid);
		}
		JSONObject jsonmap = ismsTaskUpdateService.getFileInData(tbRecordPage);
		
		OtherMethod.PrintFlush(response, jsonmap);
	}

	/**
	 * 
	 * @param tbReviewPage
	 * @param response
	 * 获取维度筛选的数据
	 * 并以json的数据返回至前端页面
	 * @throws UnsupportedEncodingException 
	 * 
	 */
	@RequestMapping(value="/getmodeldata",method = {RequestMethod.POST,RequestMethod.GET})
	private void getModelInData(TBRecordPage tbRecordPage,HttpServletResponse response,HttpServletRequest request) throws UnsupportedEncodingException {
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("realname");
		log.info(name+"访问了下发管理的\"维度筛选\"任务列表");
		if (tbRecordPage.getKeyword()!=null&&!tbRecordPage.getKeyword().equals("")) {
			String decodekeyword = URLDecoder.decode(tbRecordPage.getKeyword(),"utf-8");
			log.debug("keyword的值是："+decodekeyword);
			tbRecordPage.setKeyword(decodekeyword);
		}
		if (tbRecordPage.getKeyid()!=null&&!tbRecordPage.getKeyid().equals("")) {
			String decodekeyid = URLDecoder.decode(tbRecordPage.getKeyid(),"utf-8");
			log.debug("keyid的值是："+decodekeyid);
			tbRecordPage.setKeyid(decodekeyid);
		}
		JSONObject jsonmap = ismsTaskUpdateService.getModelInData(tbRecordPage);
	
		OtherMethod.PrintFlush(response, jsonmap);
	}


	/**
	 * 
	 * @param taskid
	 * @param response
	 * 根据群组的ID删除指定的用户群组
	 * 
	 */
	@RequestMapping(value="/deltask",method = {RequestMethod.POST,RequestMethod.GET})
	private void deleteFileTask(int taskid,HttpServletResponse response,HttpServletRequest request) {
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("realname");
		log.info(name+"删除文件导入的用户群组！删除的群组ID为："+taskid);
		JSONObject json = ismsTaskUpdateService.deleteFileTask(taskid);
		OtherMethod.PrintFlush(response, json);
	}


	/**
	 * 
	 * @param taskid
	 * @param response
	 * 删除维度筛选的数据
	 * 
	 */
	@RequestMapping(value="/delmodel",method = {RequestMethod.POST,RequestMethod.GET})
	private void deleteModelTask(int taskid,HttpServletResponse response,HttpServletRequest request) {
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("realname");
		log.info(name+"删除维度筛选任务组！删除的任务ID为："+taskid);
		JSONObject json = ismsTaskUpdateService.deleteModelTask(taskid);
		OtherMethod.PrintFlush(response, json);
	}
	/**
	 * 
	 * @param tbRecord
	 * @param response
	 * 点击审核之后访问的地址
	 * 用于文件导入的状态
	 * 
	 */
	@RequestMapping(value="/upstatefile",method = {RequestMethod.POST,RequestMethod.GET})
	private void updateStateFileTask(TBRecord tbRecord,HttpServletResponse response) {
		log.debug("当前的审核的状态是："+tbRecord.getState());
		log.info(tbRecord.getReviewman()+"执行文件导入任务审核，对任务ID为："+tbRecord.getId()+"审核。\n审核状态为："+tbRecord.getState());
		JSONObject json = ismsTaskUpdateService.updateStateFileTask(tbRecord);
		OtherMethod.PrintFlush(response, json);
	}

	/**
	 * 
	 * @param tbReview
	 * @param response
	 * 点击审核之后访问的地址
	 * 用于维度筛选的状态
	 * 
	 */
	@RequestMapping(value="/upstatemodel",method = {RequestMethod.POST,RequestMethod.GET})
	private void updateStateModel(TBReview tbReview,HttpServletResponse response) {
		log.info(tbReview.getReUser()+"执行维度筛选任务审核，对任务ID为："+tbReview.getId()+"审核。\n审核状态为："+tbReview.getState());
		JSONObject json = ismsTaskUpdateService.updateStateModel(tbReview);
		OtherMethod.PrintFlush(response, json);
	}


	/**
	 * 
	 * @param tbRecord
	 * @param file_stu
	 * @param response
	 * 运营人员重新提交数据
	 * 
	 */
	@RequestMapping(value="/upsmsfile",method = {RequestMethod.POST,RequestMethod.GET})
	private void updateSmsTaskFileIn(TBRecord tbRecord,HttpServletResponse response) {
		log.debug("tbrecord id is "+tbRecord.getId());
		log.info(tbRecord.getAddman()+"执行修改文件导入任务;任务ID是："+tbRecord.getId());
		JSONObject json = ismsTaskUpdateService.updateSmsTaskFileIn(tbRecord);
		OtherMethod.PrintFlush(response, json);
	}


	/**
	 * 
	 * @param re_id
	 * @param response
	 * @throws Exception
	 * 用于导出维度筛选中的号码 
	 * 
	 */
	@RequestMapping(value="/exportdata",method = {RequestMethod.POST,RequestMethod.GET})
	private void exportModelData(Review review,HttpServletResponse response,HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("realname");
		response.setContentType("text/html;charset=utf-8");
		log.info(name+"执行导出维度筛选任务中的数据。导出维度数据");
		JSONObject json = ismsTaskUpdateService.exportModelData(review);
		OtherMethod.PrintFlush(response, json);
	}

	/**
	 * 
	 * @param filepath
	 * @param request
	 * @param response
	 * 下载文件
	 * 
	 */
	@RequestMapping(value="/downfile",method= {RequestMethod.GET,RequestMethod.POST})
	private void OutputFileByPathToStream(String filepath,HttpServletRequest request,HttpServletResponse response) {
		try {
			File file = new File(filepath);
			// 取得文件名。
			String filename = file.getName();
			// 以流的形式下载文件。
			InputStream fis = new BufferedInputStream(new FileInputStream(filepath));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// 清空response
			response.reset();
			// 设置response的Header
			response.addHeader("Content-Disposition", "attachment;filename="
					+ new String(filename.getBytes()));
			response.addHeader("Content-Length", "" + file.length());
			OutputStream toClient = new BufferedOutputStream(
					response.getOutputStream());
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
			if (file.delete()) {
				log.info("当前文件已删除");
			}else {
				log.info("删除失败");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
