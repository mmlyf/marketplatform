package com.mtpt.extend;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mtpt.aliservice.impl.TBReviewService;
import com.mtpt.bean.CustomService;
import com.mtpt.bean.IceDsjOrders;
import com.mtpt.bean.Review;
import com.mtpt.bean.TBDsjDxAll;
import com.mtpt.bean.TBDsjIceAll;
import com.mtpt.bean.TBUsers;
import com.mtpt.config.BaseConfig;
import com.mtpt.config.SpringContextUtil;
import com.mtpt.service.impl.TBDsjDxAllService;
import com.mtpt.service.impl.TBDsjIceAllService;


public class OutputFile {
	private static Logger log = Logger.getLogger(OutputFile.class);
////	private static String alipath = "/Users/lvgordon/Downloads/little/";//测试环境
//	private static String alipath = "D://NEW_HSDTMarket_Platform/aliuserexport/";//支付宝用户导出路径河北
////	private static String modeldatapath = "/Users/lvgordon/Downloads/little/";//测试环境
//	private static String modeldatapath = "D://NEW_HSDTMarket_Platform/modeldataexport/";//河北维度筛选数据导出
//	private static String scenedatapath = "D://NEW_HSDTMarket_Platform/exportscenedata/";//河北标签数据导出
////	private static String scenedatapath = "/Users/lvgordon/Downloads/little/";
//	private static String icepath = "D://NEW_HSDTMarket_Platform/icedataexport/";//冰激凌数据导出
////	private static String icepath = "/Users/lvgordon/Downloads/little/";//ceshi
	private static TBDsjDxAllService dxService = (TBDsjDxAllService) SpringContextUtil.getBean("dxservice");
	private static TBDsjIceAllService iceService = (TBDsjIceAllService) SpringContextUtil.getBean("iceservice");
	private static TBReviewService reviewService = (TBReviewService) SpringContextUtil.getBean("reservice");
	
	/**
	 * 
	 * @param userlist
	 * @return
	 * 导出支付宝绑定用户的支付宝ID和号码
	 * 
	 */
	public static String outputAilPay(List<TBUsers> userlist) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String namestr = sdf.format(date);
		String filename = namestr +".xlsx";
		String alifilepath = BaseConfig.ALIPATH + filename;
		File file = new File(alifilepath);
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet();
		XSSFRow rowzero = sheet.createRow(0);
		Cell cell1 = rowzero.createCell(0);
		Cell cell2 = rowzero.createCell(1);
		cell1.setCellValue("号码");
		cell2.setCellValue("支付宝ID");
		int i = 1;
		for(TBUsers tbUsers : userlist) {
			XSSFRow row = sheet.createRow(i);
			Cell rowcell1 = row.createCell(0);
			Cell rowcell2 = row.createCell(1);
			rowcell1.setCellValue(tbUsers.getMobile());
			rowcell2.setCellValue(tbUsers.getOpenid());
			i++;
		}
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(file);
			workbook.write(fos);
			workbook.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return alifilepath;
	}
	
	
	/**
	 * 
	 * @param re_id
	 * @return
	 * 导出维度数据中的号码
	 * 
	 */
	public static String exportModelData(Review review) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String namestr = sdf.format(date);
		String allpath = BaseConfig.MODELPATH + namestr +".txt";
		File file = new File(allpath);
		if (!file.exists()) {
			try {
				log.debug("创建新文件");
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			FileWriter fw = new FileWriter(file);
			BufferedWriter bWriter = new BufferedWriter(fw);
			if (review.getPrelx().equals("dsj_dx_all")) {
				List<TBDsjDxAll> dxlist = dxService.selectByReview(review);
				if (dxlist==null) {
					bWriter.write("空");
				}else {
					for(TBDsjDxAll tbDsjDxAll:dxlist) {
						bWriter.write(tbDsjDxAll.getDxDn());
						bWriter.newLine();
					}
				}
				bWriter.flush();
				bWriter.close();
			}else {
				List<TBDsjIceAll> icelist = iceService.selectByReview(review);
				if(icelist==null) {
					bWriter.write("空");
				}else {
					for (TBDsjIceAll	 tbDsjIceAll:icelist) {
						bWriter.write(tbDsjIceAll.getDxDn());
						bWriter.newLine();
					}
				}
				bWriter.flush();
				bWriter.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allpath;
	}
	
	/**
	 * 
	 * @param <T>
	 * @param icedatalist
	 * @return
	 * 将冰激凌预约的数据保存至Excel表格中。并返回保存的位置信息
	 * flag:0：冰激凌预约数据
	 * 1：客服服务预约数据
	 * 
	 */
	public static <T> String outputIceBookData(List<T> list,int flag) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String namestr = sdf.format(date);
		String filename = namestr +".xlsx";
		String alifilepath = BaseConfig.ICEPATH + filename;
		File file = new File(alifilepath);
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet();
		XSSFRow rowzero = sheet.createRow(0);
		Cell cell1 = rowzero.createCell(0);
		Cell cell2 = rowzero.createCell(1);
		Cell cell3 = rowzero.createCell(2);
		Cell cell4 = rowzero.createCell(3);
		Cell cell5 = rowzero.createCell(4);
		Cell cell6 = rowzero.createCell(5);
		Cell cell7 = rowzero.createCell(6);
		Cell cell8 = rowzero.createCell(7);
		Cell cell9 = rowzero.createCell(8);
		Cell cell10 = rowzero.createCell(9);
		cell1.setCellValue("城市");
		cell2.setCellValue("号码");
		cell3.setCellValue("平均ap");
		cell4.setCellValue("网段");
		cell5.setCellValue("套餐");
		cell6.setCellValue("融合");
		cell7.setCellValue("融合类型");
		cell8.setCellValue("低消");
		cell9.setCellValue("产品");
		cell10.setCellValue("档位");
		int i = 1;
		for( T t : list) { 
			XSSFRow row = sheet.createRow(i);
			Cell rowcell1 = row.createCell(0);
			Cell rowcell2 = row.createCell(1);
			Cell rowcell3 = row.createCell(2);
			Cell rowcell4 = row.createCell(3);
			Cell rowcell5 = row.createCell(4);
			Cell rowcell6 = row.createCell(5);
			Cell rowcell7 = row.createCell(6);
			Cell rowcell8 = row.createCell(7);
			Cell rowcell9 = row.createCell(8);
			Cell rowcell10 = row.createCell(9);
			if(flag==0) {
				IceDsjOrders iceDsjOrders = (IceDsjOrders)t;
				rowcell1.setCellValue(iceDsjOrders.getDxCity());
				rowcell2.setCellValue(iceDsjOrders.getDxDn());
				rowcell3.setCellValue(iceDsjOrders.getDxAp());
				rowcell4.setCellValue(iceDsjOrders.getDxSys());
				rowcell5.setCellValue(iceDsjOrders.getDxInn());
				rowcell6.setCellValue(iceDsjOrders.getDxRh());
				rowcell7.setCellValue(iceDsjOrders.getDxRhlx());
				rowcell8.setCellValue(iceDsjOrders.getDxIfdx());
				rowcell9.setCellValue(iceDsjOrders.getDxFirp());
				rowcell10.setCellValue(iceDsjOrders.getDxFirdw());
			}else if(flag == 1) {
				CustomService iceDsjOrders = (CustomService) t;
				rowcell1.setCellValue(iceDsjOrders.getDxCity());
				rowcell2.setCellValue(iceDsjOrders.getDxDn());
				rowcell3.setCellValue(iceDsjOrders.getDxAp());
				rowcell4.setCellValue(iceDsjOrders.getDxSys());
				rowcell5.setCellValue(iceDsjOrders.getDxInn());
				rowcell6.setCellValue(iceDsjOrders.getDxRh());
				rowcell7.setCellValue(iceDsjOrders.getDxRhlx());
				rowcell8.setCellValue(iceDsjOrders.getDxIfdx());
				rowcell9.setCellValue(iceDsjOrders.getDxFirp());
				rowcell10.setCellValue(iceDsjOrders.getDxFirdw());
			}
			i++;
		}
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(file);
			workbook.write(fos);
			workbook.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return alifilepath;
	}
	
	/**
	 * 
	 * @param list
	 * @return
	 * 
	 * 
	 */
	public static String outputSceneLabelData(List<String> list) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String namestr = sdf.format(date);
		String filename = namestr +".xlsx";
		String scenefilepath = BaseConfig.SCENEPATH + filename;
		File file = new File(scenefilepath);
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet();
		XSSFRow rowzero = sheet.createRow(0);
		Cell cell1 = rowzero.createCell(0);
		cell1.setCellValue("号码");
		int i = 1;
		for(String phone : list) {
			XSSFRow row = sheet.createRow(i);
			Cell rowcell1 = row.createCell(0);
			rowcell1.setCellValue(phone);
			i++;
		}
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(file);
			workbook.write(fos);
			workbook.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return scenefilepath;
	}
}
