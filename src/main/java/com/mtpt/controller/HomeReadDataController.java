package com.mtpt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mtpt.bean.DataTotal;
import com.mtpt.bean.page.TotalPage;
import com.mtpt.extend.OtherMethod;
import com.mtpt.service.IDataTotalService;
import com.mtpt.service.IProduceService;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.bcel.internal.generic.Select;

import apple.laf.JRSUIConstants.State;
import jdk.nashorn.internal.ir.annotations.Ignore;
import sun.launcher.resources.launcher;
import sun.util.logging.resources.logging;

@Controller
@RequestMapping("/home")
public class HomeReadDataController {

	private Logger log = Logger.getLogger(HomeReadDataController.class);
	@Resource 
	IDataTotalService datatotalService;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd");
	@RequestMapping(value="/getTotal",method = {RequestMethod.POST,RequestMethod.GET})
	private void getTotalData(TotalPage totalPage,HttpServletResponse response) {
		Date date = new Date();
		log.debug("当前的延迟时间是："+totalPage.getDelay());
		List<String> dateday = new ArrayList<String>();
		JSONObject mouser = new JSONObject();
		JSONObject mounuser = new JSONObject();
		JSONObject mtall = new JSONObject();
		JSONObject mtsuc = new JSONObject();
		JSONObject ordersuc = new JSONObject();
		JSONObject orderdis = new JSONObject();
		for(int i=totalPage.getDelay();i>0;i--) {
			dateday.add(sdf1.format(OtherMethod.reduceDay(date, i)));
		}
		for(String datestr:dateday) {
			mouser.put(datestr, 0);
			mounuser.put(datestr, 0);
			mtall.put(datestr, 0);
			mtsuc.put(datestr, 0);
			ordersuc.put(datestr, 0);
			orderdis.put(datestr, 0);
		}
		List<DataTotal> list = datatotalService.selectDataTotalByDelayPage(totalPage);
		for(DataTotal dataTotal:list) {
			try {
				Date adtime = sdf.parse(dataTotal.getAddTime());
				String timestr = sdf1.format(adtime);
				mouser.put(timestr, dataTotal.getMoable());
				mounuser.put(timestr, dataTotal.getModisable());
				mtall.put(timestr, dataTotal.getMtall());
				mtsuc.put(timestr, dataTotal.getMtsuc());
				ordersuc.put(timestr, dataTotal.getOrdersuc());
				ordersuc.put(timestr, dataTotal.getOrderdissuc());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		JSONObject json = new JSONObject();
		json.put("mouser", mouser);
		json.put("mounuser", mounuser);
		json.put("mtall", mtall);
		json.put("mtsuc", mtsuc);
		json.put("ordersuc", ordersuc);
		json.put("orderdis", orderdis);
		json.put("xaix", dateday.toArray());
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
	 * @param totalPage
	 * @param request
	 * @param response
	 * @throws ParseException
	 * 
	 * 
	 */
	@RequestMapping(value="/getTotaldelay",method = {RequestMethod.POST,RequestMethod.GET})
	private void selectDataTotalByDelayforTable(TotalPage totalPage,HttpServletRequest request,
			HttpServletResponse response) throws ParseException {
		List<String> dateday = new ArrayList<>();
		log.info("表格中数据延迟的数据是："+totalPage.getDelay());
		Date date = new Date();
		JSONObject jsonmap = new JSONObject();
		List<JSONObject> jsonlist = new ArrayList<>();
		int rescount = datatotalService.selectCountByDelay(totalPage);
		totalPage.setTotalRecord(rescount);
		List<DataTotal> listdata = datatotalService.selectDataTotalByDelayPage(totalPage);
		for(int i=totalPage.getDelay();i>0;i--) {
			dateday.add(sdf1.format(OtherMethod.reduceDay(date, i)));
		}
		int count = 0;
		for(DataTotal dataTotal:listdata) {
			JSONObject map = new JSONObject();
			Date datadate = sdf.parse(dataTotal.getAddTime());
			map.put("time", sdf1.format(datadate));
			map.put("totalid", dataTotal.getId());
			map.put("mtall", dataTotal.getMtall());
			map.put("mtsuc", dataTotal.getMtsuc());
			map.put("moall", dataTotal.getMoall());
			map.put("moable",dataTotal.getMoable());
			map.put("moabledx", dataTotal.getMoabledx());
			map.put("moableice", dataTotal.getMoableice());
			map.put("moablellb", dataTotal.getMoablellb());
			map.put("orderall", dataTotal.getOrderall());
			map.put("ordersuc", dataTotal.getOrdersuc());
			map.put("ordersucdx", dataTotal.getOrdersucdx());
			map.put("ordersucllb", dataTotal.getOrdersucllb());
			map.put("orderdissuc", dataTotal.getOrderdissuc());
			map.put("orderdissucdx", dataTotal.getOrderdissucdx());
			map.put("orderdissucllb", dataTotal.getOrderdissucllb());
			map.put("icebook", dataTotal.getIcesuc());
			map.put("mtrate", dataTotal.getMtrate());
			map.put("morate", dataTotal.getMorate());
			map.put("modxrate", dataTotal.getModxrate());
			map.put("mollbrate", dataTotal.getMollbrate());
			map.put("moicerate", dataTotal.getMoicerate());
			map.put("orderrate", dataTotal.getOrderrate());
			map.put("resorderrate", dataTotal.getResorderrate());
			map.put("ordersucrate", dataTotal.getOrdersucrate());
			map.put("ordersucdxrate", dataTotal.getOrdersucdxrate());
			map.put("ordersucllbrate", dataTotal.getOrdersucllbrate());
			jsonlist.add(map);
		}
		jsonmap.put("code", 0);
		jsonmap.put("msg", "");
		jsonmap.put("count", rescount);
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
	 * @param month
	 * @param response
	 * 获取统计界面中选择的月份，如果月份为空则展示当前月份的前一个月的数据
	 * 
	 */
	@RequestMapping(value="/selectmonth",method = {RequestMethod.POST,RequestMethod.GET})
	private void selectMonthData(String month,HttpServletResponse response) {
		int days = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String datestr = "";
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int day = calendar.get(Calendar.DATE);

		if (month!=null) {
			log.debug("当前的月份是："+month);
			int intmonth = Integer.parseInt(month);
			days = OtherMethod.getTheMonthOfDays(intmonth);
			calendar.set(year, intmonth-1, day);
		}else {
			int nowmonth = calendar.get(Calendar.MONTH);
			log.debug("当前月份"+nowmonth+",以及当前月份减去一天："+(nowmonth-1));
			days = OtherMethod.getTheMonthOfDays(nowmonth-1);
			calendar.set(year, nowmonth, day);
		}
		datestr = sdf.format(calendar.getTime());
		log.debug("当前日期的datestr的值是："+datestr);
		List<DataTotal> list = datatotalService.selectDataTotalMonth(datestr);
		Map<Integer , Integer> moMap = new HashMap<Integer, Integer>();
		Map<Integer, Integer> ordermap = new HashMap<Integer, Integer>();
		for(int i = 0;i<=days;i++) {
			moMap.put(i, 0);
			ordermap.put(i, 0);
		}
		for(DataTotal dataTotal:list) {
			try {
				Date date = sdf.parse(dataTotal.getAddTime());
				moMap.put(date.getDate(), dataTotal.getMoable());
				ordermap.put(date.getDate(), dataTotal.getOrdersuc());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		JSONObject json = new JSONObject();
		json.put("moTotal", moMap);
		json.put("orderTotal", ordermap);
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
	 * 
	 * @param delay
	 * @param response
	 * 运营人员可以通过选取当前时间的前几周的数据，默认为当前时间的前一周
	 * 
	 */
	@RequestMapping(value="/selectweek",method = {RequestMethod.POST,RequestMethod.GET})
	private void selectWeekData(Integer delay,HttpServletResponse response) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (delay==null) {
			delay = 1;
		}
		List<DataTotal> list = datatotalService.selectDataTotalWeek(delay);
		JSONObject moweekmap = new JSONObject();
		JSONObject orderweekmap = new JSONObject();
		String[] weekstr = {"星期日","星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
		for(int i = 0;i<7;i++) {
			moweekmap.put(weekstr[i], 0);
			orderweekmap.put(weekstr[i], 0);
		}
		log.info("当前delay的值是："+delay);
		for(DataTotal dataTotal:list) {
			try {
				Date date = sdf.parse(dataTotal.getAddTime());
				calendar.setTime(date);
				int week = calendar.get(Calendar.DAY_OF_WEEK)-1;
				log.debug("当前日期是："+dataTotal.getAddTime());
				log.debug("当前的星期编号是："+week);
				moweekmap.put(weekstr[week], dataTotal.getMoable());
				orderweekmap.put(weekstr[week], dataTotal.getOrdersuc());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		response.setContentType("text/html; charset=UTF-8");
		JSONObject json = new JSONObject();
		json.put("weekstr", weekstr);
		json.put("moTotalWeek", moweekmap);
		json.put("orderTotalWeek", orderweekmap);
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

	@RequestMapping(value="/selectnowbefore",method = {RequestMethod.POST,RequestMethod.GET})
	private void selectDataTotalForBrforeNow(String addtime,HttpServletResponse response) {
		DataTotal dataTotal = datatotalService.selectByAddTime(addtime);
		log.debug("当前的datatotal的值是："+dataTotal.getMoable());
		try {
			PrintWriter pw = response.getWriter();
			pw.write("{moable:"+dataTotal.getMoable()+"}");
			pw.flush();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
