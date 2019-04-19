package com.mtpt.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONException;
import org.json.JSONObject;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mtpt.bean.FTPDetails;
import com.mtpt.bean.TBUsers;
import com.mtpt.bean.page.AlipayPage;
import com.mtpt.extend.HttpRequest;
import com.mtpt.extend.OtherMethod;
import com.mtpt.extend.OutputFile;
import com.mtpt.service.IAlipayBindUserManageService;
import com.mtpt.service.IFTPDetailsService;


@Controller
@RequestMapping("/alipayuser")
public class AliPayUserManagerController {
	private Logger log = Logger.getLogger(AliPayUserManagerController.class);
	@Resource
	private IAlipayBindUserManageService alipaybindusermanageService;
	
	/**
	 * 
	 * @param page
	 * @param response
	 * 根据alipage查询出支付宝绑定用户的数据
	 * 并通过json数据格式传至前端，并由前端显示
	 * 
	 */
	@RequestMapping(value="/selectbypage",method = {RequestMethod.POST,RequestMethod.GET})
	private void selectByAlipayPage(AlipayPage page,HttpServletResponse response,HttpServletRequest request) {
		response.setContentType("text/html;charset=utf-8");
		JSONObject jsonmap = alipaybindusermanageService.selectAlipayBindUserByPage(page, request);
		OtherMethod.PrintFlush(response, jsonmap);
	}
	
	/**
	 * 
	 * @param page
	 * @param response
	 * 查看流量赠送的状态，并通过json的格式返回至前端
	 * 
	 */
	@RequestMapping(value="/selectdetail",method = {RequestMethod.POST,RequestMethod.GET})
	private void selectFtpDetailsByPage(AlipayPage page,HttpServletResponse response,HttpServletRequest request) {
		response.setContentType("text/html;charset=utf-8");
		JSONObject jsonmap = alipaybindusermanageService.selectAlipayBindUserSendFlowDetailByPage(page, request);
		OtherMethod.PrintFlush(response, jsonmap);
	}
	
	@RequestMapping(value="/selectungift",method = {RequestMethod.POST,RequestMethod.GET})
	private void selectUnGiftFlowUser(AlipayPage page,HttpServletResponse response,HttpServletRequest request) {
		response.setContentType("text/html;charset=utf-8");
		JSONObject jsonmap = alipaybindusermanageService.selectAlipayBindUserUnGiftFlowByPage(page, request);
		OtherMethod.PrintFlush(response, jsonmap);
	}
	
	/**
	 * 
	 * @param phonenum
	 * @param flow
	 * @param response
	 * 用于流量赠送的功能
	 * 
	 */
	@RequestMapping(value="/flowgift",method = {RequestMethod.POST,RequestMethod.GET})
	private void submitFlowGift(String phonenum,String flow,HttpServletResponse response,HttpServletRequest request) {
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("realname");
		log.info(name+"执行赠送流量操作！为号码为："+phonenum+"赠送"+flow+"MB");
		JSONObject json = alipaybindusermanageService.submitAlipayBindUserGiftFlow(phonenum, flow);
		OtherMethod.PrintFlush(response, json);
	}
	
	@RequestMapping(value="/output",method = {RequestMethod.POST,RequestMethod.GET})
	private void outputAlipayUser(HttpServletResponse response,HttpServletRequest request) {
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("realname");
		log.info(name+"导出支付宝绑定用户的支付宝ID和号码");
		String path = alipaybindusermanageService.outputAlipayBindUserInfo();
		log.debug("当前的文件的路径是："+path);
		try {
			File file = new File(path);
			// 取得文件名。
			String filename = file.getName();
			// 以流的形式下载文件。
			InputStream fis = new BufferedInputStream(new FileInputStream(path));
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
			if(file.delete()) {
				log.debug("缓存文件删除！");
			}else {
				log.debug("缓存文件删除失败！");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
 	}
}
