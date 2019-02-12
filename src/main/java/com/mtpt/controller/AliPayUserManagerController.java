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
import com.mtpt.extend.OutputFile;
import com.mtpt.service.IFTPDetailsService;
import com.mtpt.service.ITBUsersService;

import sun.util.logging.resources.logging;

@Controller
@RequestMapping("/alipayuser")
public class AliPayUserManagerController {
	private Logger log = Logger.getLogger(AliPayUserManagerController.class);
	@Resource
	ITBUsersService userService;
	@Resource
	IFTPDetailsService detailService;
	
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
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("realname");
		log.info(name+"访问支付宝绑定用户列表！");
		 int totals = userService.selectByCount(page);
		 page.setTotalRecord(totals);
		 List<TBUsers> userslist = userService.selectByAlipayPage(page);
		 List<JSONObject> jsonlist = new ArrayList<JSONObject>();
		 JSONObject jsonmap = new JSONObject();
		 int count = 1;
		 for(TBUsers tbUsers : userslist) {
			 JSONObject map = new JSONObject();
			 map.put("id", count);
			 map.put("uid", tbUsers.getId());
			 map.put("dn", tbUsers.getMobile());
			 map.put("openId", tbUsers.getOpenid());
			 jsonlist.add(map);
			 count++;
		 }
		 jsonmap.put("code", 0);
		 jsonmap.put("msg", "");
		 jsonmap.put("count", totals);
		 jsonmap.put("data", jsonlist);
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
	 * @param page
	 * @param response
	 * 查看流量赠送的状态，并通过json的格式返回至前端
	 * 
	 */
	@RequestMapping(value="/selectdetail",method = {RequestMethod.POST,RequestMethod.GET})
	private void selectFtpDetailsByPage(AlipayPage page,HttpServletResponse response,HttpServletRequest request) {
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("realname");
		log.info(name+"访问赠送流量结果的列表。");
		int totals = detailService.selectByCount(page);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		page.setTotalRecord(totals);
		List<FTPDetails> ftplist = detailService.selectByAlipayPage(page);
		JSONObject jsonmap = new JSONObject();
		List<JSONObject> jsonlist = new ArrayList<JSONObject>();
		for(FTPDetails ftpDetails : ftplist) {
			JSONObject map = new JSONObject();
			map.put("id", ftpDetails.getId());
			map.put("dn", ftpDetails.getMobile());
			map.put("amount", ftpDetails.getAmount());
			if(ftpDetails.getDatastate()==0) {
				map.put("state", "赠送成功");
			}else {
				map.put("state", "赠送失败");
			}
			if (ftpDetails.getModifytime()!=null) {
				String modifystr = sdf.format(ftpDetails.getModifytime());
				map.put("modifytime", modifystr);
			}else {
				map.put("modifytime", "");
			}
			jsonlist.add(map);
		}
		jsonmap.put("code", 0);
		jsonmap.put("msg", "");
		jsonmap.put("data", jsonlist);
		jsonmap.put("count", totals);
		response.setContentType("text/html;charset=utf-8");
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
	
	@RequestMapping(value="/selectungift",method = {RequestMethod.POST,RequestMethod.GET})
	private void selectUnGiftFlowUser(AlipayPage page,HttpServletResponse response,HttpServletRequest request) {
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("realname");
		log.info(name+"访问当前绑定用户漏赠用户的列表");
		int totals = detailService.selectByCount(page);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		page.setTotalRecord(totals);
		List<TBUsers> ftplist = userService.selectUnGiftFlowUser(page);
		JSONObject jsonmap = new JSONObject();
		List<JSONObject> jsonlist = new ArrayList<JSONObject>();
		int count = 0;
		for(TBUsers tbUsers : ftplist) {
			JSONObject map = new JSONObject();
			 map.put("id", count);
			 map.put("uid", tbUsers.getId());
			 map.put("dn", tbUsers.getMobile());
			 map.put("openId", tbUsers.getOpenid());
			jsonlist.add(map);
			count++;
		}
		jsonmap.put("code", 0);
		jsonmap.put("msg", "");
		jsonmap.put("data", jsonlist);
		jsonmap.put("count", totals);
		response.setContentType("text/html;charset=utf-8");
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
	 * @param phonenum
	 * @param flow
	 * @param response
	 * 用于流量赠送的功能
	 * 
	 */
	@RequestMapping(value="/flowgift",method = {RequestMethod.POST,RequestMethod.GET})
	private void submitFlowGift(String phonenum,String flow,HttpServletResponse response,HttpServletRequest request) {
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("realname");
		log.info(name+"执行赠送流量操作！为号码为："+phonenum+"赠送"+flow+"MB");
		String path = "http://mobile99.uninforun.com/unicom-hb/api/Unicom/PresendFlow";
		JSONObject paramjson = new JSONObject();
		List<JSONObject> listjson  = new ArrayList<JSONObject>();
		paramjson.put("PhoneNum", phonenum);
		paramjson.put("Amount", flow);
		paramjson.put("FlowType", "ZFGZ");
		listjson.add(paramjson);
		log.debug(listjson.toString());
		String resultstr = HttpRequest.sendPostJson(path, listjson.toString());
		log.debug("赠送流量请求的数据："+resultstr);
		JSONObject json = new JSONObject();
		if(resultstr.equals("")) {
			json.put("code", 0);
		}else {
			json.put("code", 1);
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
	
	@RequestMapping(value="/output",method = {RequestMethod.POST,RequestMethod.GET})
	private void outputAlipayUser(HttpServletResponse response,HttpServletRequest request) {
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("realname");
		log.info(name+"导出支付宝绑定用户的支付宝ID和号码");
		List<TBUsers> userlist = userService.selectAllAlipayUser();
		String path = OutputFile.outputAilPay(userlist);
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
