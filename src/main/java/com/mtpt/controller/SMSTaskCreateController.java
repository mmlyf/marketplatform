package com.mtpt.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import com.mtpt.aliservice.ITBRecordService;
import com.mtpt.aliservice.ITBReviewService;
import com.mtpt.bean.Review;
import com.mtpt.bean.TBProd;
import com.mtpt.bean.TBProdLx;
import com.mtpt.service.IProduceService;
import com.mtpt.service.ITBDsjDxAllService;
import com.mtpt.service.ITBDsjIceAllService;
import com.mtpt.service.ITBProService;
import com.mtpt.service.ITBProdLxService;
import com.mtpt.service.ITBStateService;
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
	ITBRecordService service;
	@Resource
	ITBStateService stateservice;
	@Resource 
	IProduceService pService;
	@Resource
	ITBReviewService reservice;
	@Resource
	ITBDsjDxAllService dxservice;
	@Resource 
	ITBDsjIceAllService iceservice;
	@Resource 
	ITBProdLxService prolxservice;
	@Resource 
	ITBProService proservice;
	
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
		
		log.info(tbRecord.getAddman()+"创建文件导入的任务为"+tbRecord.getGroupname());
		String sucres = "";
		LineNumberReader lnr = null;
		log.debug("是否去除黑名单："+tbRecord.getIsdelblack()+"，是否去除三天以内的数据："+tbRecord.getIsdeldays());
		String filename = file_stu.getOriginalFilename();
		String houzhui = filename.substring(filename.indexOf(".")+1, filename.length());
		log.debug("后缀名是："+filename);
		Date date = new Date();
		sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		String newfilename = sdf.format(date);
		String path = "D://NEW_HSDTMarket_Platform/upload/"+newfilename+"."+houzhui;;//河北
		log.debug("当前文件的存入的位置是："+path);
		try {
			OutputStream out = new FileOutputStream(path);
			InputStream in = file_stu.getInputStream();
			int value;
			while((value=in.read())!=-1) {
				out.write(value);
			}
			File file = new File(path);
			lnr = new LineNumberReader(new FileReader(path));
			int linenums = 0;
			while (lnr.readLine()!=null) {
				linenums+=1;
			}
			tbRecord.setFilenum(linenums);
			tbRecord.setNewfilename(file.getName());
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (migId1!=null) {
			tbRecord.setMigId(migId1);
		}
		tbRecord.setAddtime(new Date());
		tbRecord.setFilename(filename);
		if (tbRecord.getIstimework()!=null&&tbRecord.getIstimework().equals("on")) {
			tbRecord.setIstimework("是");
			
		}else {
			tbRecord.setIstimework("否");
			tbRecord.setWorktime(null);
		}
		if (tbRecord.getIsdelblack()!=null&&tbRecord.getIsdelblack().equals("on")) {
			tbRecord.setIsdelblack("是");
		}else {
			tbRecord.setIsdelblack("否");
		}
		if (tbRecord.getIsdeldays()!=null&&tbRecord.getIsdeldays().equals("on")) {
			tbRecord.setIsdeldays("是");
		}else {
			tbRecord.setIsdeldays("否");
		}
		log.debug("格式转过的时间："+tbRecord.getWorktime());
		tbRecord.setState(0);
		response.setContentType("text/html; charset=UTF-8");
		int result = service.insert(tbRecord);
		if (result>0) {
			sucres = "delimana";
		}else {
			sucres = "smsmana";
		}
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
		String sucres = "";
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		tbReview.setAddTime(new Date());
		if (tbReview.getIsitmework()==null) {
			tbReview.setIsitmework("否");
		}else {
			tbReview.setIsitmework("是");
		}
		if (tbReview.getIsdeldays()!=null&&tbReview.getIsdelblack().equals("on")) {
			tbReview.setIsdelblack("是");
		}else {
			tbReview.setIsdelblack("否");
		}
		if (tbReview.getIsdeldays()!=null&&tbReview.getIsdeldays().equals("on")) {
			tbReview.setIsdeldays("是");
		}else {
			tbReview.setIsdeldays("否");
		}
		log.debug("时间是："+tbReview.getSecTime());
		TBProd tbProd = proservice.selectByPrimaryKey(Integer.parseInt(tbReview.getProduct()));
		tbReview.setProduct(tbProd.getProname());
		review.setProduct(tbProd.getProname());
		log.debug("时间是："+tbReview.getSecTime());
		TBProdLx tbProdLx = prolxservice.selectByPrimaryKey(Integer.parseInt(tbReview.getPrelx()));
		tbReview.setPrelx(tbProdLx.getLxvalue());
		log.debug("当前prelx的值是："+tbReview.getPrelx());
		review.setDbname(tbProdLx.getLxvalue());
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		log.debug("当前的pservice的值是："+pService);
		log.debug("当前的时间是"+sdf.format(tbReview.getSecTime()));
		review.setSecTime(sdf.format(tbReview.getSecTime()));
		int count = pService.selectModelCount(review);
		log.debug("当前的维度的筛选的数量是:"+count);
		tbReview.setCount(count);
		tbReview.setState(0);
		int result = reservice.insert(tbReview);
		if(result>0) {
			sucres = "delimana";
		}else {
			sucres = "smsmana";
		}
		return sucres;
	}
}


