package com.mtpt.service.impl;

import java.io.LineNumberReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.*;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mtpt.alibean.TBRecord;
import com.mtpt.alibean.TBReview;
import com.mtpt.alidao.TBRecordMapper;
import com.mtpt.alidao.TBReviewMapper;
import com.mtpt.bean.Review;
import com.mtpt.bean.TBProd;
import com.mtpt.bean.TBProdLx;
import com.mtpt.dao.ProduceMapper;
import com.mtpt.dao.ProductsMapper;
import com.mtpt.dao.TBProdLxMapper;
import com.mtpt.dao.TBProdMapper;
import com.mtpt.service.ISMSTaskCreateService;

@Service("smsTaskCreateService")
public class SMSTaskCreateService implements ISMSTaskCreateService{

	private Logger log = Logger.getLogger(SMSTaskCreateService.class);
	
	@Autowired
	private TBRecordMapper recordMapper;
	@Autowired
	private ProduceMapper produceMapper;
	@Autowired 
	private TBProdMapper prodMapper;
	@Autowired
	private TBReviewMapper reviewMapper;
	@Autowired
	private TBProdLxMapper prodLxMapper;
	
	SimpleDateFormat sdf = null;
	@Override
	public String smsTaskFileIn(MultipartFile file_stu, TBRecord tbRecord, Integer migId1) {
		// TODO Auto-generated method stub
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
		int result = recordMapper.insert(tbRecord);
		if (result>0) {
			sucres = "delimana";
		}else {
			sucres = "smsmana";
		}
		return sucres;
	}

	@Override
	public String smsTaskModelIn(TBReview tbReview, Review review) {
		// TODO Auto-generated method stub
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
		TBProd tbProd = prodMapper.selectByPrimaryKey(Integer.parseInt(tbReview.getProduct()));
		tbReview.setProduct(tbProd.getProname());
		review.setProduct(tbProd.getProname());
		log.debug("时间是："+tbReview.getSecTime());
		TBProdLx tbProdLx = prodLxMapper.selectByPrimaryKey(Integer.parseInt(tbReview.getPrelx()));
		tbReview.setPrelx(tbProdLx.getLxvalue());
		log.debug("当前prelx的值是："+tbReview.getPrelx());
		review.setDbname(tbProdLx.getLxvalue());
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		log.debug("当前的时间是"+sdf.format(tbReview.getSecTime()));
		review.setSecTime(sdf.format(tbReview.getSecTime()));
		int count = produceMapper.selectModelCount(review);
		log.debug("当前的维度的筛选的数量是:"+count);
		tbReview.setCount(count);
		tbReview.setState(0);
		int result = reviewMapper.insert(tbReview);
		if(result>0) {
			sucres = "delimana";
		}else {
			sucres = "smsmana";
		}
		return null;
	}

}
