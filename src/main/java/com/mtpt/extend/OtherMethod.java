package com.mtpt.extend;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.persistence.criteria.CriteriaBuilder.Case;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.mtpt.config.BaseConfig;

public class OtherMethod {
	/**
	 * 将指定的时间增加指定的天数
	 * @param date
	 * @param num
	 * @return
	 */
	public static Date addDay(Date date, int num) {
		Calendar startDT = Calendar.getInstance();
		startDT.setTime(date);
		startDT.add(Calendar.DAY_OF_MONTH, num);
		return startDT.getTime();
	}
	
	/**
	 * 将指定的时间减少指定的天数
	 * @param date
	 * @param num
	 * @return
	 */
	public static Date reduceDay(Date date,int num) {
		Calendar startDT = Calendar.getInstance();
		startDT.setTime(date);
		int year = startDT.get(Calendar.YEAR);
		int month = startDT.get(Calendar.MONTH);
		int day = startDT.get(Calendar.DATE)-num;
		startDT.set(year, month, day);
		return startDT.getTime();
	}
	
	/**
	 * 获取指定月份的天数
	 * @param month
	 * @return
	 */
	public static int getTheMonthOfDays(int month) {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			return 31;
		case 4:
		case 6:
		case 9:
		case 11:
			return 30;
		case 2:
			if ((year%4==0 && year%100!=0) || year%400==0) {
				return 29;
			}else {
				return 28;
			}
		default:
			return 0;
		
		}
	}
	
	/**
	 * 获取唯一标识符UUID
	 * @return
	 */
	public static String getUuid() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
	
	/**
	 * 将当前的日期加一年
	 * @param date
	 * @return
	 */
	public static String addYearTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		year += 1;
		calendar.set(Calendar.YEAR, year);
		Date newdate = calendar.getTime();
		long newdatetime = newdate.getTime();
		return String.valueOf(newdatetime);
	}
	
	/**
	 * 用于回传json数据
	 * @param response
	 * @param json
	 */
	public static void PrintFlush(HttpServletResponse response,JSONObject json) {
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
	 * 查询协尔平台进行二次确认的内容
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String selectSecondConfirmForXier(String agwvalue) throws UnsupportedEncodingException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String datestr = simpleDateFormat.format(new Date());
		System.out.println(datestr);
		String pwd = datestr + MD5.md(BaseConfig.XIEER_PASSWORD);
		String mdpwd = MD5.md(pwd);
		String params = "{" + 
				"\"method\": \"order.confirm.result\"," + 
				"\"version\":\"1\"," + 
				"\"timestamp\":\""+datestr+"\"," + 
				"\"app_key\":\""+BaseConfig.XIEER_ACCOUNT+"\"," + 
				"\"app_pwd\":\""+mdpwd+"\"," + 
				"\"transaction_id\": \""+agwvalue+"\"" + 
				"}" ;
		String result = HttpRequest.sendPostForSelectSecConfirm(BaseConfig.XIEER_URL, params);
		MyDesUtils myDesUtils = new MyDesUtils(BaseConfig.XIEER_SCRIPT);
		String dString = myDesUtils.decrypt(result);
		String decodestr = URLDecoder.decode(dString, "utf-8");
		return decodestr;
	}
}
