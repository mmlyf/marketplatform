package com.mtpt.extend;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class CustomGlobalStrToDateConverter implements Converter<String, Date> {

	public Date convert(String source) {
		// TODO Auto-generated method stub
	        try {
	        		if (source!=null&&!"".equals(source)) {
	        			Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(source);
	    	            return date;
				}
	            
	        } catch (ParseException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
		return null;
	}
}
