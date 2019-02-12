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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.json.Json;
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
import com.mtpt.extend.OutputFile;
import com.mtpt.service.ITBDsjDxAllService;
import com.mtpt.service.ITBDsjIceAllService;
import com.mtpt.service.ITBMssageService;
import com.mtpt.service.ITBProService;
import com.mtpt.service.ITBProdLxService;
import com.mtpt.service.ITBStateService;


@Controller
@RequestMapping("/smsupdate")
public class SMSTaskUpdateController {
	private Logger log = Logger.getLogger(SMSTaskUpdateController.class);

	@Resource
	ITBRecordService recordservice;//文件群组的service
	@Resource
	ITBStateService stateservice;//状态表的service
	@Resource 
	ITBReviewService reviewService;//维度筛选模型表的service
	@Resource
	ITBProService proService;//产品标的service
	@Resource
	ITBMssageService mssageService;//消息表的service
	@Resource
	ITBProdLxService prodLxService;
	@Resource
	ITBDsjDxAllService dxAllService;
	@Resource
	ITBDsjIceAllService iceAllService;

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
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("realname");
		log.info(name+"访问下发管理中的\"文件导入\"任务列表");
		if (tbRecordPage.getKeytype().equals("addman")||tbRecordPage.getKeytype().equals("reviewman")) {
			tbRecordPage.setKeyword(name);
		}
		//		if(tbRecordPage.getKeyword()!=null&&!tbRecordPage.getKeyword().equals("")) {
		//			tbRecordPage.setKeyword(new String(tbRecordPage.getKeyword().getBytes("utf-8"),"GBK"));
		//		}else if(tbRecordPage.getKeyid()!=null&&!tbRecordPage.getKeyid().equals("")) {
		//			tbRecordPage.setKeyid(new String(tbRecordPage.getKeyid().getBytes("UTF-8"),"GBK"));
		//		}
		log.debug("当前查询的Word和type是："+tbRecordPage.getKeytype()+"/"+tbRecordPage.getKeyword());
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		JSONObject jsonmap = new JSONObject();
		List<JSONObject> jsonlist = new ArrayList<JSONObject>();
		int totals = recordservice.selectAllCount(tbRecordPage);
		tbRecordPage.setTotalPage(totals);
		List<TBRecord> list = recordservice.selectByRecordPage(tbRecordPage);
		for(TBRecord tbRecord:list) {
			JSONObject map = new JSONObject();
			map.put("id", tbRecord.getId());
			map.put("groupname", tbRecord.getGroupname());
			map.put("filename", tbRecord.getFilename());
			map.put("filenum", tbRecord.getFilenum());
			map.put("addman", tbRecord.getAddman());
			map.put("addtime", sdf.format(tbRecord.getAddtime()));
			map.put("lastwork", tbRecord.getLastwork());
			if (tbRecord.getEndtime()!=null) {
				map.put("endtime", sdf.format(tbRecord.getEndtime()));
			}else {
				map.put("endtime", "");
			}
			map.put("istimework", tbRecord.getIstimework());
			if (tbRecord.getWorktime()!=null) {
				map.put("worktime", sdf.format(tbRecord.getWorktime()));
			}else {
				map.put("worktime", "");
			}
			if (tbRecord.getMigId()!=null) {
				TBMssage tbMssage = mssageService.selectByPrimaryKey(tbRecord.getMigId());
				map.put("msgtitle", tbMssage.getMisTitle());
				map.put("msgcontent", tbMssage.getMisContent());
			}else {
				map.put("msgtitle", "");
				map.put("msgcontent", "");
			}
			map.put("isdelblack", tbRecord.getIsdelblack());
			map.put("isdeldays", tbRecord.getIsdeldays());
			map.put("deldays", tbRecord.getDeldays());
			map.put("reviewman", tbRecord.getReviewman());
			TBState tbState = stateservice.selectByState(tbRecord.getState());
			map.put("state", tbState.getStatename());
			jsonlist.add(map);
		}
		jsonmap.put("code", 0);
		jsonmap.put("msg", "");
		jsonmap.put("count",totals);
		jsonmap.put("data", jsonlist);
		response.setContentType("text/html; charset=UTF-8");
		JSONObject json = new JSONObject();
		json.put("map", jsonmap);
		try {
			PrintWriter pw = response.getWriter();
			pw.write(jsonmap.toString());
			pw.flush();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param tbReviewPage
	 * @param response
	 * 获取维度筛选的数据
	 * 并以json的数据返回至前端页面
	 * 
	 */
	@RequestMapping(value="/getmodeldata",method = {RequestMethod.POST,RequestMethod.GET})
	private void getModelInData(TBRecordPage tbRecordPage,HttpServletResponse response,HttpServletRequest request) {
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("realname");
		log.info(name+"访问了下发管理的\"维度筛选\"任务列表");
		if(tbRecordPage.getKeytype().equals("rd_user")||tbRecordPage.getKeytype().equals("re_user")) {
			tbRecordPage.setKeyword(name);
		}
		List<TBReview> list = reviewService.selectByReviewPage(tbRecordPage);
		int totals = reviewService.selectCountAll(tbRecordPage);
		JSONObject jsonmap = new JSONObject();
		List<JSONObject> jsonlist = new ArrayList<JSONObject>();
		tbRecordPage.setTotalRecord(totals);
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (TBReview tbReview:list) {
			JSONObject map = new JSONObject();
			map.put("id", tbReview.getId());
			map.put("city",tbReview.getCity());
			map.put("source_type", tbReview.getSourceType());
			map.put("product", tbReview.getProduct());
			map.put("dangw", tbReview.getDangw());
			map.put("ifrh", tbReview.getIfrh());
			map.put("ifdx", tbReview.getIfdx());
			map.put("istimework", tbReview.getIsitmework());
			if(tbReview.getIsitmework().equals("是")) {
				String worktimestr = sdf.format(tbReview.getWorktime());
				map.put("worktime", worktimestr);
			}else {
				map.put("worktime", "");
			}
			map.put("isdelblack", tbReview.getIsdelblack());
			map.put("isdeldays", tbReview.getIsdeldays());
			map.put("deldays", tbReview.getDeldays());
			map.put("count", tbReview.getCount());
			TBState tbState = stateservice.selectByState(tbReview.getState());
			map.put("state", tbState.getStatename());
			map.put("rduser", tbReview.getRdUser());
			if (tbReview.getSecTime()!=null) {
				sdf = new SimpleDateFormat("yyyy-MM-dd");
				String secstr  = sdf.format(tbReview.getSecTime());
				map.put("sectime", secstr);
			}else {
				map.put("sectime	", "");
			}
			if (tbReview.getAddTime()!=null) {
				sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String addtimestr = sdf.format(tbReview.getAddTime());
				map.put("createtime", addtimestr);
			}else {
				map.put("createtime", "");
			}
			if (tbReview.getMigId()!=null) {
				TBMssage tbMssage = mssageService.selectByPrimaryKey(tbReview.getMigId());
				map.put("msgtitle", tbMssage.getMisTitle());
				map.put("msgcontent", tbMssage.getMisContent());
			}else {
				map.put("msgtitle", "");
				map.put("msgcontent", "");
			}
			map.put("reuser", tbReview.getReUser());
			jsonlist.add(map);
		}
		jsonmap.put("code", 0);
		jsonmap.put("msg", "");
		jsonmap.put("count",totals);
		jsonmap.put("data", jsonlist);
		response.setContentType("text/html; charset=UTF-8");
		JSONObject json = new JSONObject();
		json.put("map", jsonmap);
		try {
			PrintWriter pw = response.getWriter();
			pw.write(jsonmap.toString());
			pw.flush();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		int result = recordservice.deleteByPrimaryKey(taskid);
		try {
			PrintWriter pw = response.getWriter();
			if (result>0) {
				pw.write("{\"code\":1}");
			}else {
				pw.write("{\"code\":0}");
			}
			pw.flush();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		int result = reviewService.deleteByPrimaryKey(taskid);
		try {
			PrintWriter pw = response.getWriter();
			if (result>0) {
				pw.write("{\"code\":1}");
			}else {
				pw.write("{\"code\":0}");
			}
			pw.flush();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		int result = recordservice.updateByPrimaryKeySelective(tbRecord);
		JSONObject json = new JSONObject();
		if (result>0) {
			json.put("code", 1);
		}else {
			json.put("code", 0);
		}
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
		int result = reviewService.updateByPrimaryKeySelective(tbReview);
		JSONObject json = new JSONObject();
		if(result>0) {
			json.put("code", 1);
		}else {
			json.put("code", 0);
		}
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
		if (tbRecord.getIstimework()!=null&&tbRecord.getIstimework().equals("on")) {
			tbRecord.setIstimework("是");
		}else {
			tbRecord.setIstimework("否");
			tbRecord.setWorktime(null);
		}
		if (tbRecord.getIsdeldays()!=null&&tbRecord.getIsdelblack().equals("on")) {
			tbRecord.setIsdelblack("是");
		}else {
			tbRecord.setIsdelblack("否");

		}
		if (tbRecord.getIsdeldays()!=null&&tbRecord.getIsdeldays().equals("on")) {
			tbRecord.setIsdeldays("是");
		}else {
			tbRecord.setIsdeldays("否");
			tbRecord.setDeldays(null);
		}
		tbRecord.setState(0);
		int result = recordservice.updateByPrimaryKeySelective(tbRecord);
		JSONObject json = new JSONObject();
		if (result>0) {
			json.put("code", 1);
		}else {
			json.put("code",0);
		}
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
	 * 
	 * @param tbReview
	 * @param review
	 * @param response
	 * 更新维度筛选中的某些条件
	 * 
	 */
	//	@RequestMapping(value="/upsmsmodel")
	//	private void updateSmsTaskModelIn(TBReview tbReview,Review review,HttpServletResponse response) {
	//		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//		if (tbReview.getIsitmework()==null) {
	//			tbReview.setIsitmework("否");
	//		}else {
	//			tbReview.setIsitmework("是");
	//		}
	//		if (tbReview.getIsdeldays()!=null&&tbReview.getIsdelblack().equals("on")) {
	//			tbReview.setIsdelblack("是");
	//		}else {
	//			tbReview.setIsdelblack("否");
	//		}
	//		if (tbReview.getIsdeldays()!=null&&tbReview.getIsdeldays().equals("on")) {
	//			tbReview.setIsdeldays("是");
	//		}else {
	//			tbReview.setIsdeldays("否");
	//			tbReview.setDeldays(null);
	//		}	
	//		TBProd tbProd = proService.selectByPrimaryKey(Integer.parseInt(tbReview.getProduct()));
	//		tbReview.setProduct(tbProd.getProname());
	//		TBProdLx tbProdLx = prodLxService.selectByPrimaryKey(Integer.parseInt(tbReview.getPrelx()));
	//		tbReview.setPrelx(tbProdLx.getLxvalue());
	//		log.info("当前prelx的值是："+tbReview.getPrelx());
	//		int count = 0;
	//		if(tbReview.getPrelx().equals("dsj_dx_all")) {
	//			log.info("当前产生的产品");
	//			count = dxAllService.selectCountByReview(review);
	//			log.info("当前dxcount的值是："+count);
	//			tbReview.setCount(count);
	//		}else {
	//			count = iceAllService.selectCountByReview(review);
	//			log.info("当前icecount的值是："+count);
	//			tbReview.setCount(count);
	//		}
	//		tbReview.setState(0);
	//		int result = reviewService.updateByPrimaryKeySelective(tbReview);
	//		JSONObject json = new JSONObject();
	//		if (result>0) {
	//			json.put("code", 1);
	//		}else {
	//			json.put("code", 0);
	//		}
	//		try {
	//			PrintWriter pw = response.getWriter();
	//			pw.write(json.toString());
	//			pw.flush();
	//			pw.close();
	//		} catch (IOException e) {
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		}
	//	}


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
		//		log.debug("当前导出的维度任务ID是："+re_id);
		if(review.getPrelx()!=null) {
			System.out.println("prelx is :"+review.getPrelx());
			int lxid = Integer.parseInt(review.getPrelx());
			TBProdLx tbProdLx = prodLxService.selectByPrimaryKey(lxid);
			review.setPrelx(tbProdLx.getLxvalue());
		}
		JSONObject json = new JSONObject();
		String path = OutputFile.exportModelData(review);
		json.put("code", 0);
		json.put("filepath", path);
		PrintWriter pw = response.getWriter();
		pw.write(json.toString());
		pw.flush();
		pw.close();
//		response.sendRedirect("http://localhost:8085/HSDT_Market_Platform/smsupdate/downfile?filepath="+path);
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
