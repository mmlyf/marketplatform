package com.mtpt.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.deser.Deserializers.Base;
import com.mtpt.bean.CustomService;
import com.mtpt.bean.IceDsjOrders;
import com.mtpt.bean.Orders;
import com.mtpt.bean.Products;
import com.mtpt.bean.TBDsjDxAll;
import com.mtpt.bean.TBDsjIceAll;
import com.mtpt.bean.page.IceBookPage;
import com.mtpt.bean.page.OrdersPage;
import com.mtpt.config.BaseConfig;
import com.mtpt.extend.HttpRequest;
import com.mtpt.extend.OutputFile;
import com.mtpt.extend.ProductNameType;
import com.mtpt.service.ICustomService;
import com.mtpt.service.IIceDsjOrdersService;
import com.mtpt.service.IOrdersService;
import com.mtpt.service.IProduceService;
import com.mtpt.service.IProductsService;

@Controller
@RequestMapping("/buscontro")
public class BusinessModuleController {
	private Logger log = Logger.getLogger(BusinessModuleController.class);

	@Resource
	IOrdersService orderService;
	@Resource 
	IProductsService productsService;
	@Resource
	IProduceService pdcService;
	@Resource 
	IIceDsjOrdersService iceDsjOrdersService;
	@Resource 
	ICustomService scService;
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
		session = request.getSession();
		String name = (String) session.getAttribute("realname");
		log.info(name+"访问订单列表！");
		log.debug(page.getDn());
		int totals = orderService.selectByOrdersCount(page);
		log.info("当前数量的统计是："+totals);
		page.setTotalRecord(totals);
		JSONObject jsonmap = new JSONObject();
		List<JSONObject> jsonlist = new ArrayList<JSONObject>();
		List<Orders> listorders = orderService.selectByOrdersPage(page);
		log.info("订单的内容是："+listorders.size());
		int i = 1;
		for(Orders orders:listorders) {
			JSONObject map = new JSONObject();
			map.put("id", i);
			map.put("dn", orders.getMobile());
			Products products = productsService.selectByPrimaryKey(orders.getProductid());
			String productname = ProductNameType.getName(products.getVagabondizetype(),
					products.getPackagetype(), products.getEffecttype());
			map.put("productname", productname+"-"+products.getProductname());
			map.put("price", products.getCost());
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			map.put("ordertime", sdf.format(orders.getPurchasetime()));
			map.put("state", orders.getBssstate());
			map.put("agw", orders.getSerialno());
			map.put("source", ProductNameType.getSource(orders.getLsource()));
			jsonlist.add(map);
			i++;
		}
		jsonmap.put("code", 0);
		jsonmap.put("msg", "");
		jsonmap.put("count", totals);
		jsonmap.put("data", jsonlist);
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
	 * @param response
	 * @param request
	 * 根据号码查询订购列表和详细档位等数据
	 * 
	 */
	@RequestMapping(value="/selectdndetails",method = {RequestMethod.POST,RequestMethod.GET})
	private void selectByPhoneForDetails(String phonenum,HttpServletResponse response,HttpServletRequest request) {
		session = request.getSession();
		String name = (String) session.getAttribute("realname");
		log.info(name+"查找号码"+phonenum+"的详细信息(档位、订单等信息)");
		Integer dxcount = pdcService.selectDxCount(phonenum);
		if (dxcount==null) {
			dxcount = 0;
		}
		Integer icecount = pdcService.selectIceCount(phonenum);
		if (icecount==null) {
			icecount = 0;
		}
		List<TBDsjDxAll> dxList = pdcService.selectDxByPhoneDetails(phonenum);
		List<TBDsjIceAll> icelist = pdcService.selectIceByPhoneDetails(phonenum);
		JSONObject jsonmap = new JSONObject();
		List<JSONObject> jsonlist = new ArrayList<>();
		int count = 1;
		if (dxList!=null) {
			for(TBDsjDxAll tbDsjDxAll:dxList) {
				for(Orders orders:tbDsjDxAll.getOrders()) {
					JSONObject map = new JSONObject();
					map.put("id", count);
					map.put("dn", tbDsjDxAll.getDxDn());
					map.put("city", tbDsjDxAll.getDxCity());
					map.put("ap", tbDsjDxAll.getDxAp());
					map.put("sys", tbDsjDxAll.getDxSys());
					map.put("inner", tbDsjDxAll.getDxInn());
					map.put("rh", tbDsjDxAll.getDxRh());
					map.put("rhlx", tbDsjDxAll.getDxRhlx());
					map.put("dxfirp", tbDsjDxAll.getDxFirp());
					map.put("dxfirdw", tbDsjDxAll.getDxFirdw());
					map.put("ifdx", tbDsjDxAll.getDxIfdx());
					map.put("agw", orders.getSerialno());
					sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					map.put("ordertime", sdf.format(orders.getPurchasetime()));
					Products products = productsService.selectByPrimaryKey(orders.getProductid());
					String productname = ProductNameType.getName(products.getVagabondizetype(),
							products.getPackagetype(), products.getEffecttype());
					map.put("price", products.getCost());
					map.put("productname", productname+"-"+products.getProductname());
					map.put("state", orders.getBssstate());
					map.put("source", ProductNameType.getSource(orders.getLsource()));
					jsonlist.add(map);
					count++;
				}
			}
		}
		if (icelist!=null) {
			for(TBDsjIceAll tbDsjIceAll:icelist) {
				JSONObject icemap = new JSONObject();
				icemap.put("id", count);
				icemap.put("dn", tbDsjIceAll.getDxDn());
				icemap.put("city", tbDsjIceAll.getDxCity());
				icemap.put("ap", tbDsjIceAll.getDxAp());
				icemap.put("sys", tbDsjIceAll.getDxSys());
				icemap.put("inner", tbDsjIceAll.getDxInn());
				icemap.put("rh", tbDsjIceAll.getDxRh());
				icemap.put("rhlx", tbDsjIceAll.getDxRhlx());
				icemap.put("dxfirp", tbDsjIceAll.getDxFirp());
				icemap.put("dxfirdw", tbDsjIceAll.getDxFirdw());
				icemap.put("ifdx", tbDsjIceAll.getDxIfdx());
				jsonlist.add(icemap);
				count++;
			}
		}
		jsonmap.put("code", 0);
		jsonmap.put("msg", "");
		jsonmap.put("count", dxcount+icecount);
		jsonmap.put("data", jsonlist);
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
		String getnumdetailurl = "http://mobile99.uninforun.com/unicom-hb/api/Unicom/GetUserInfo";
		String orderurl = "http://mobile99.uninforun.com/unicom-hb/api/Unicom/Order";
		String paramcode = "phonenum="+phonenum;
		String networkType = "";
		String productCode = "";
		String agwCode = "";
		int code ;
		boolean is4G = false;
		String resultjsonstr = HttpRequest.sendGet(getnumdetailurl, paramcode);
		JSONObject resultjson = new JSONObject(resultjsonstr);
		if (resultjson.getBoolean("result")) {
			JSONObject datajson = resultjson.getJSONObject("data");
			networkType = datajson.getString("NetworkType");
		}
		Orders orders = orderService.selectByPrimaryKey(id);
		Products products = productsService.selectByPrimaryKey(orders.getProductid());
		if (networkType.equals("2G")||networkType.equals("3G")) {
			productCode = products.getProductcode23g();
			is4G = false;
		}else if(networkType.equals("4G")) {
			productCode = products.getProductcode4g();
			is4G = true;
		}
		String parameorder = "phoneNum="+phonenum+"&productCode="+productCode+"&actionType="+actioncode+"&is4G="+is4G;
		String resultorder = HttpRequest.sendPost(orderurl, parameorder);
		JSONObject jsonorder = new JSONObject(resultorder);
		if (jsonorder.getBoolean("result")) {
			agwCode = jsonorder.getString("data");
		}
		Orders upOrders = new Orders();
		upOrders.setId(id);
		upOrders.setSerialno(agwCode);
		int uporder = orderService.updateByPrimaryKeySelective(upOrders);
		if(uporder>0) {
			code = 0;
		}else {
			code = 1;
		}
		try {
			PrintWriter pw = response.getWriter();
			pw.write("{code:"+code+"}");
			pw.flush();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		int totals = iceDsjOrdersService.selectIceDataCountByPage(iceBookPage);
		iceBookPage.setTotalRecord(totals);
		List<IceDsjOrders> icedatalist = iceDsjOrdersService.selectIceDataByPage(iceBookPage);
		JSONObject jsonmap = new JSONObject();
		List<JSONObject> jsonlist = new ArrayList<>();
		int count = 1;
		for(IceDsjOrders iceDsjOrders : icedatalist) {
			JSONObject datajson = new JSONObject();
			datajson.put("id", count);
			datajson.put("dataid", iceDsjOrders.getDxId());
			datajson.put("dn", iceDsjOrders.getDxDn());
			datajson.put("city", iceDsjOrders.getDxCity());
			datajson.put("ap", iceDsjOrders.getDxAp());
			datajson.put("systype", iceDsjOrders.getDxSys());
			datajson.put("inn", iceDsjOrders.getDxInn());
			datajson.put("ifrh", iceDsjOrders.getDxRh());
			datajson.put("rhlx", iceDsjOrders.getDxRhlx());
			datajson.put("firp", iceDsjOrders.getDxFirp());
			datajson.put("firdw", iceDsjOrders.getDxFirdw());
			datajson.put("ifdx", iceDsjOrders.getDxIfdx());
			if (iceDsjOrders.getDxQd()==0) {
				datajson.put("qudao", "支付宝");
			}else {
				datajson.put("qudao", "短信");
			}
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String adtimestr = sdf.format(iceDsjOrders.getDxAddtime());
			datajson.put("addtime", adtimestr);
			jsonlist.add(datajson);
			count+=1;
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
	 * @param request
	 * @param response
	 * 根据icebookpage对象的数据查找用户预约客服介绍的数据
	 * 
	 */
	@RequestMapping(value="/getcsdata",method= {RequestMethod.GET,RequestMethod.POST})
	private void selectCustomServiceBookData(IceBookPage page,HttpServletRequest request,HttpServletResponse response) {
		int totals = scService.selectIceDataCountByPage(page);
		page.setTotalRecord(totals);
		List<CustomService> csList = scService.selectIceDataByPage(page);
		JSONObject jsonmap = new JSONObject();
		List<JSONObject> jsonlist = new ArrayList<>();
		int count = 1;
		for(CustomService cs:csList) {
			JSONObject datajson = new JSONObject();
			datajson.put("id", count);
			datajson.put("dataid", cs.getDxId());
			datajson.put("dn", cs.getDxDn());
			datajson.put("city", cs.getDxCity());
			datajson.put("ap", cs.getDxAp());
			datajson.put("systype", cs.getDxSys());
			datajson.put("inn", cs.getDxInn());
			datajson.put("ifrh", cs.getDxRh());
			datajson.put("rhlx", cs.getDxRhlx());
			datajson.put("firp", cs.getDxFirp());
			datajson.put("firdw", cs.getDxFirdw());
			datajson.put("ifdx", cs.getDxIfdx());
			datajson.put("qudao", "支付宝");
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String adtimestr = sdf.format(cs.getDxAddtime());
			datajson.put("addtime", adtimestr);
			jsonlist.add(datajson);
			count+=1;
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
	 * @param request
	 * 根据icebookpage对象的数据的值查找用户预约客服介绍的数据
	 * 并保存在Excel表格中
	 * 并根据查询的结果和文件写入的情况返回code和文件位置json数据
	 * code:0成功；1：失败
	 * 
	 */
	@RequestMapping(value="/outputice",method= {RequestMethod.GET,RequestMethod.POST})
	private void outputIceBookData(IceBookPage page,HttpServletResponse response,HttpServletRequest request) {
		List<IceDsjOrders> icelist = iceDsjOrdersService.selectIceDataAllByPage(page);
		JSONObject  json = new JSONObject();
		
		if(!icelist.isEmpty()) {
			String filepath = OutputFile.outputIceBookData(icelist,0);
			json.put("code", 0);
			json.put("path", filepath);
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
		List<CustomService> cslist = scService.selectAllCsDataByPage(page);
		JSONObject  json = new JSONObject();
		if(!cslist.isEmpty()) {
			String filepath = OutputFile.outputIceBookData(cslist,1);
			json.put("code", 0);
			json.put("path", filepath);
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
	private void selectOrdersPhoneInfo(String phone,HttpServletResponse response) {
		response.setContentType("application/json;charset=utf-8");
		String param = "phoneNum="+phone;
		String result = HttpRequest.sendGet(BaseConfig.DNDETAILPATH, param);
		JSONObject json = new JSONObject(result);
		JSONObject jsonvalue = new JSONObject();
		if (json.getBoolean("result")) {
			JSONObject data = json.getJSONObject("data");
			log.debug("当前的data的值是："+data);
			jsonvalue.put("NetworkType", data.get("NetworkType"));
			jsonvalue.put("ChargeType", data.get("ChargeType"));
			jsonvalue.put("MainComboId", data.get("MainComboId"));
			jsonvalue.put("ProductName", data.get("ProductName"));
			jsonvalue.put("ProductDesc", data.get("ProductDesc"));
			jsonvalue.put("OpenDate", data.get("OpenDate"));
			jsonvalue.put("CityCode", data.get("CityCode"));
		}else {
			
		}
		try {
			PrintWriter pw = response.getWriter();
			pw.write(jsonvalue.toString());
			pw.flush();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
