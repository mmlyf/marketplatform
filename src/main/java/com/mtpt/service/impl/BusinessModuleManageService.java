package com.mtpt.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtpt.bean.CustomService;
import com.mtpt.bean.IceDsjOrders;
import com.mtpt.bean.Orders;
import com.mtpt.bean.Products;
import com.mtpt.bean.TBDsjDxAll;
import com.mtpt.bean.TBDsjIceAll;
import com.mtpt.bean.page.IceBookPage;
import com.mtpt.bean.page.OrdersPage;
import com.mtpt.config.BaseConfig;
import com.mtpt.dao.CustomServiceMapper;
import com.mtpt.dao.IceDsjOrdersMapper;
import com.mtpt.dao.OrdersMapper;
import com.mtpt.dao.ProduceMapper;
import com.mtpt.dao.ProductsMapper;
import com.mtpt.extend.HttpRequest;
import com.mtpt.extend.OutputFile;
import com.mtpt.extend.ProductNameType;
import com.mtpt.service.IBusinessModuleManageService;
import com.mysql.cj.x.protobuf.MysqlxCrud.Order;

@Service("budinessModuleManageService")
public class BusinessModuleManageService implements IBusinessModuleManageService{
	private Logger log = Logger.getLogger(BusinessModuleManageService.class);
	@Autowired
	private OrdersMapper ordersMapper;
	@Autowired 
	private ProductsMapper productsMapper;
	@Autowired 
	private ProduceMapper produceMapper;
	@Autowired
	private IceDsjOrdersMapper icedsjOrdersMapper;
	@Autowired
	private CustomServiceMapper customServiceMapper;
	private SimpleDateFormat sdf = null;
	
	/**
	 * 查找订购列表中的数据，或者根据一些条件进行查询
	 */
	@Override
	public JSONObject selectAllOrdersDataByPage(OrdersPage page) {
		// TODO Auto-generated method stub
		log.debug(page.getDn());
		int totals = ordersMapper.selectByOrdersCount(page);
		log.info("当前数量的统计是："+totals);
		page.setTotalRecord(totals);
		JSONObject jsonmap = new JSONObject();
		List<JSONObject> jsonlist = new ArrayList<JSONObject>();
		List<Orders> listorders = ordersMapper.selectByOrdersPage(page);
		log.info("订单的内容是："+listorders.size());
		int i = 1;
		for(Orders orders:listorders) {
			JSONObject map = new JSONObject();
			map.put("id", i);
			map.put("dn", orders.getMobile());
			Products products = productsMapper.selectByPrimaryKey(orders.getProductid());
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
		return jsonmap;
	}

	/**
	 * 根据号码查询订购列表和详细档位等数据
	 */
	@Override
	public JSONObject selectOrdersDetailByPhoneNum(String phone) {
		// TODO Auto-generated method stub
		Integer dxcount = produceMapper.selectDxCount(phone);
		if (dxcount==null) {
			dxcount = 0;
		}
		Integer icecount = produceMapper.selectIceCount(phone);
		if (icecount==null) {
			icecount = 0;
		}
		List<TBDsjDxAll> dxList = produceMapper.selectDxByPhoneDetails(phone);
		List<TBDsjIceAll> icelist = produceMapper.selectIceByPhoneDetails(phone);
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
					Products products = productsMapper.selectByPrimaryKey(orders.getProductid());
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
		return jsonmap;
	}

	/**
	 * 用于为号码进行订购操作。有订购和补订以及退订操作
	 */
	@Override
	public JSONObject ordersOperationForOrder(String id, String phonenum, Integer actioncode) {
		// TODO Auto-generated method stub
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
		Orders orders = ordersMapper.selectByPrimaryKey(id);
		Products products = productsMapper.selectByPrimaryKey(orders.getProductid());
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
		int uporder = ordersMapper.updateByPrimaryKeySelective(upOrders);
		if(uporder>0) {
			code = 0;
		}else {
			code = 1;
		}
		JSONObject json = new JSONObject();
		json.put("code", code);
		return json;
	}

	/**
	 * 根据前端传过来的条件进行筛选冰激凌预约数据
	 */
	@Override
	public JSONObject selectDsjIceBookDataDetailByPage(IceBookPage page) {
		// TODO Auto-generated method stub
		int totals = icedsjOrdersMapper.selectIceDataCountByPage(page);
		page.setTotalRecord(totals);
		List<IceDsjOrders> icedatalist = icedsjOrdersMapper.selectIceDataByPage(page);
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
		return jsonmap;
	}

	/**
	 * 根据icebookpage对象的数据查找用户预约客服介绍的数据
	 */
	@Override
	public JSONObject selectIceCustomServiceBookDataByPage(IceBookPage page) {
		// TODO Auto-generated method stub
		int totals = customServiceMapper.selectIceDataCountByPage(page);
		page.setTotalRecord(totals);
		List<CustomService> csList = customServiceMapper.selectIceDataByPage(page);
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
		return jsonmap;
	}

	/**
	 * 根据icebookpage对象的数据的值查找用户预约客服介绍的数据
	 * 并保存在Excel表格中
	 * 并根据查询的结果和文件写入的情况返回code和文件位置json数据
	 * code:0成功；1：失败
	 */
	@Override
	public JSONObject outputDsjIceDataByPage(IceBookPage page) {
		// TODO Auto-generated method stub
		List<IceDsjOrders> icelist = icedsjOrdersMapper.selectIceDataAllByPage(page);
		JSONObject  json = new JSONObject();
		
		if(!icelist.isEmpty()) {
			String filepath = OutputFile.outputIceBookData(icelist,0);
			json.put("code", 0);
			json.put("path", filepath);
		}else {
			json.put("code", 1);
		}
		return json;
	}

	/**
	 * 根据icebookpage对象的数据的值查找用户预约客服介绍的数据
	 * 并保存在Excel表格中
	 * 并根据查询的结果和文件写入的情况返回code和文件位置json数据
	 * code:0成功；1：失败
	 */
	@Override
	public JSONObject outputIceCustomServiceDataByPage(IceBookPage page) {
		// TODO Auto-generated method stub
		List<CustomService> cslist = customServiceMapper.selectAllCsDataByPage(page);
		JSONObject  json = new JSONObject();
		if(!cslist.isEmpty()) {
			String filepath = OutputFile.outputIceBookData(cslist,1);
			json.put("code", 0);
			json.put("path", filepath);
		}else {
			json.put("code", 1);
		}
		return json;
	}

	
	/**
	 * 请求查询当前手机号的详细内容
	 */
	@Override
	public JSONObject selectOrdersPhoneForDetail(String phone) {
		// TODO Auto-generated method stub
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
		return jsonvalue;
	}

	/**
	 * 导出订单数据并返回数据的文件地址
	 */
	@Override
	public JSONObject outputOrdersDataByPage(OrdersPage page) {
		// TODO Auto-generated method stub
		List<Orders> list = ordersMapper.selectByOrdersPageNoLimit(page);
		String filepath = OutputFile.outputOrdersData(list);
		JSONObject json = new JSONObject();
		json.put("path", filepath);
		json.put("code", 0);
		return json;
	}
}
