package com.mtpt.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mtpt.bean.page.IceBookPage;
import com.mtpt.bean.page.OrdersPage;
import com.mtpt.extend.OtherMethod;
import com.mtpt.service.IBusinessModuleManageService;


@Controller
@RequestMapping("/buscontro")
public class BusinessModuleController {
	private Logger log = Logger.getLogger(BusinessModuleController.class);

	@Resource
	private IBusinessModuleManageService businessModuleManageService;
	HttpSession session = null;
	SimpleDateFormat sdf = null;
	/**
	 * 
	 * @param page
	 * @param response
	 * @param request
	 * 查找订购列表中的数据，或者根据一些条件进行查询
	 * 
	 */
	@RequestMapping(value="/selectbypage",method = {RequestMethod.POST,RequestMethod.GET})
	private void selectOrdersByPage(OrdersPage page,HttpServletResponse response,HttpServletRequest request) {
		response.setContentType("text/html;charset=utf-8");
		session = request.getSession();
		String name = (String) session.getAttribute("realname");
		log.info(name+"访问订单列表！");
		JSONObject jsonmap = businessModuleManageService.selectAllOrdersDataByPage(page);
		OtherMethod.PrintFlush(response, jsonmap);
	}

	/**
	 * 
	 * @param phonenum
	 * @param response
	 * @param request
	 * 根据号码查询订购列表和详细档位等数据
	 * 
	 */
	@RequestMapping(value="/selectdndetails",method = {RequestMethod.POST,RequestMethod.GET})
	private void selectByPhoneForDetails(String phonenum,HttpServletResponse response,HttpServletRequest request) {
		response.setContentType("text/html;charset=utf-8");
		session = request.getSession();
		String name = (String) session.getAttribute("realname");
		log.info(name+"查找号码"+phonenum+"的详细信息(档位、订单等信息)");
		JSONObject jsonmap = businessModuleManageService.selectOrdersDetailByPhoneNum(phonenum);
		OtherMethod.PrintFlush(response, jsonmap);
	}

	/**
	 * 
	 * @param id
	 * @param phonenum
	 * @param actioncode
	 * @param request
	 * @param response
	 * 用于为号码进行订购操作。有订购和补订以及退订操作
	 * 
	 */
	@RequestMapping(value="/orderud",method = {RequestMethod.POST,RequestMethod.GET})
	private void UnsubscripeAndMakeup(String id,String phonenum,Integer actioncode,HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		session = request.getSession();
		String name = (String) session.getAttribute("realname");
		log.info(name+"对订单编号id="+id+"进行操作，actioncode="+actioncode);
		JSONObject json = businessModuleManageService.ordersOperationForOrder(id, phonenum, actioncode);
		OtherMethod.PrintFlush(response, json);
	}


	/**
	 * 
	 * @param iceBookPage
	 * @param response
	 * @param request
	 * 根据前端传过来的条件进行筛选冰激凌预约数据
	 * 
	 */
	@RequestMapping(value="/icebook",method= {RequestMethod.GET,RequestMethod.POST})
	private void getIceDsjDataBook(IceBookPage iceBookPage,HttpServletResponse response,HttpServletRequest request) {
		response.setContentType("text/html;charset=utf-8");
		session = request.getSession();
		String name = (String) session.getAttribute("realname");
		log.info(name+"查看冰淇淋预约数据");
		JSONObject json = businessModuleManageService.selectDsjIceBookDataDetailByPage(iceBookPage);
		OtherMethod.PrintFlush(response, json);
	}

	
	/**
	 * 
	 * @param page
	 * @param request
	 * @param response
	 * 根据icebookpage对象的数据查找用户预约客服介绍的数据
	 * 
	 */
	@RequestMapping(value="/getcsdata",method= {RequestMethod.GET,RequestMethod.POST})
	private void selectCustomServiceBookData(IceBookPage page,HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		session = request.getSession();
		String name = (String) session.getAttribute("realname");
		log.info(name+"查看冰淇淋客服回访预约数据");
		JSONObject jsonmap = businessModuleManageService.selectIceCustomServiceBookDataByPage(page);
		OtherMethod.PrintFlush(response, jsonmap);
	}

	/**
	 * 
	 * @param page
	 * @param response
	 * @param request
	 * 根据icebookpage对象的数据的值查找用户预约客服介绍的数据
	 * 并保存在Excel表格中
	 * 并根据查询的结果和文件写入的情况返回code和文件位置json数据
	 * code:0成功；1：失败
	 * 
	 */
	@RequestMapping(value="/outputice",method= {RequestMethod.GET,RequestMethod.POST})
	private void outputIceBookData(IceBookPage page,HttpServletResponse response,HttpServletRequest request) {
		response.setContentType("text/html;charset=utf-8");
		session = request.getSession();
		String name = (String) session.getAttribute("realname");
		log.info(name+"导出冰淇淋预约数据");
		JSONObject json = businessModuleManageService.outputDsjIceDataByPage(page);
		OtherMethod.PrintFlush(response, json);
	}
	
	/**
	 * 
	 * @param page
	 * @param response
	 * @param request
	 * 根据icebookpage对象的数据的值查找用户预约客服介绍的数据
	 * 并保存在Excel表格中
	 * 并根据查询的结果和文件写入的情况返回code和文件位置json数据
	 * code:0成功；1：失败
	 * 
	 */
	@RequestMapping(value="/outputcs",method= {RequestMethod.GET,RequestMethod.POST})
	private void outputCsBookData(IceBookPage page,HttpServletResponse response,HttpServletRequest request) {
		response.setContentType("text/html;charset=utf-8");
		session = request.getSession();
		String name = (String) session.getAttribute("realname");
		log.info(name+"导出冰淇淋客服回访预约数据");
		JSONObject json = businessModuleManageService.outputIceCustomServiceDataByPage(page);
		OtherMethod.PrintFlush(response, json);
	}

	
	/**
	 * 
	 * @param filepath
	 * @param response
	 * @param request
	 * 导出文件下载至本地
	 * 
	 */
	@RequestMapping(value="/download",method= {RequestMethod.GET})
	private void downloadOutputIceDataFile(String filepath,HttpServletResponse response,HttpServletRequest request) {
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
	
	/**
	 * 
	 * @param phone
	 * @param response
	 * 请求查询当前手机号的详细内容
	 * 
	 */
	@RequestMapping(value="/selectdninfo",method= {RequestMethod.POST,RequestMethod.GET})
	private void selectOrdersPhoneInfo(String phone,HttpServletResponse response,HttpServletRequest request) {
		response.setContentType("application/json;charset=utf-8");
		session = request.getSession();
		String name = (String) session.getAttribute("realname");
		log.info(name+"查询手机号"+phone+"的详细数据信息");
		JSONObject jsonvalue = businessModuleManageService.selectOrdersPhoneForDetail(phone);
		OtherMethod.PrintFlush(response, jsonvalue);
	}
}
