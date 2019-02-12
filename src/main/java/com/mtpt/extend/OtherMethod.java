package com.mtpt.extend;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.persistence.criteria.CriteriaBuilder.Case;

public class OtherMethod {
	public static Date addDay(Date date, int num) {
		Calendar startDT = Calendar.getInstance();
		startDT.setTime(date);
		startDT.add(Calendar.DAY_OF_MONTH, num);
		return startDT.getTime();
	}
	
	public static Date reduceDay(Date date,int num) {
		Calendar startDT = Calendar.getInstance();
		startDT.setTime(date);
		int year = startDT.get(Calendar.YEAR);
		int month = startDT.get(Calendar.MONTH);
		int day = startDT.get(Calendar.DATE)-num;
		startDT.set(year, month, day);
		return startDT.getTime();
	}
	
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
	
	public static void main(String[] args) throws ParseException {
		//20180731102901
		//1564540141
		//1564540141000
		String times = "2018-07-31 10:29:01";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = sdf.parse(times);
		String timestr = addYearTime(date);
		System.out.println(timestr);
		sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String string = "H"+sdf.format(date)+"-"+(11+(int)(Math.random()*89))+"20180730"+(1+(int)(Math.random()*9));
		System.out.println(string);
	}
}
