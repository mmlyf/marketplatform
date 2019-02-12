package com.mtpt.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sun.org.apache.xml.internal.security.utils.Base64;


/**
 * 
 * @author lvgordon
 * 发送验证码和验证码
 * 
 */
@Controller
@RequestMapping("/send")
public class SendMoMsgAndCode {
	
	private Logger log = Logger.getLogger(SendMoMsgAndCode.class);
	
	@RequestMapping(value="/msgcode",method = {RequestMethod.POST,RequestMethod.GET})
	private void sendMsgAndCode(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("GBK");
		String messageContent = request.getParameter("messageContent");
		String phoneNumber = request.getParameter("phoneNumber");
		log.debug("当前的消息内容是："+messageContent);
		log.debug("字符编码是："+getEncoding(messageContent));
		System.out.println("当前消息的内容是："+messageContent);
		if (messageContent!=null&&phoneNumber!=null) {
			Thread thread = new Thread(new HandlerSend(phoneNumber, messageContent,2));
			thread.start();
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			System.out.println("当前的消息是空的，或者号码是空的");
		}
	}
	
	 public static String getEncoding(String str) {
	        String encode = "GB2312";
	        try {
	            if (str.equals(new String(str.getBytes(encode), encode))) {
	                String s = encode;
	                return s;
	            }
	        } catch (Exception exception) {
	        }
	        encode = "ISO-8859-1";
	        try {
	            if (str.equals(new String(str.getBytes(encode), encode))) {
	                String s1 = encode;
	                return s1;
	            }
	        } catch (Exception exception1) {
	        }
	        encode = "UTF-8";
	        try {
	            if (str.equals(new String(str.getBytes(encode), encode))) {
	                String s2 = encode;
	                return s2;
	            }
	        } catch (Exception exception2) {
	        }
	        encode = "GBK";
	        try {
	            if (str.equals(new String(str.getBytes(encode), encode))) {
	                String s3 = encode;
	                return s3;
	            }
	        } catch (Exception exception3) {
	        }
	        return "";
	    }
}
	
	class HandlerSend implements Runnable{
		private Logger log = Logger.getLogger(HandlerSend.class);
		
		private String phoneNumber ;
		private String messageContent;
		private int re_id;
		//产品接入号
//		String spNumber="10655883";
		String spNumber = "1065572778";//河北接入号
		//业务代码
		String servcieType="90860230";
		//linkId
		String linkId = "MOODDDS";
		char reportflag = '1';
		public HandlerSend(String phoneNumber,String messageContent,int re_id) {
			this.phoneNumber = phoneNumber;
			this.messageContent = messageContent;
			this.re_id = re_id;
		}
		public void run() {
			// TODO Auto-generated method stub
			String strxml ="{<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
					+ "<gwsmip>\n" + "  <message_header>\n"
					+ "    <command_id>0x3</command_id>\n"
					+ "    <sequence_number/>\n" + "  </message_header>\n"
					+ "  <message_body>\n" + "    <pk_total>1</pk_total>\n"
					+ "    <pk_number>1</pk_number>\n" + "    <user_numbers>\n"
					+ "<user_number>"+phoneNumber+"</user_number>"
					+ "    </user_numbers>\n"
					+ "    <sp_number>"+spNumber+"</sp_number>\n"
					+ "    <service_type>"+servcieType+"</service_type>\n"
					+ "    <link_id>"+linkId+"</link_id>\n"
					+ "    <message_content>" + Base64.encode(messageContent.getBytes())
					+ "</message_content>\n"
					+ "    <report_flag>"+reportflag+"</report_flag>\n"
					+ "   </message_body>\n" + "</gwsmip>\n}";
			System.out.println("strxml的值是："+strxml);
//			DataInDB.sendPhoneInDB(listphone, 1, re_id);//将已发送的号码存入数据表
			try {
				Socket socket = new Socket("127.0.0.1", 8805);
				OutputStream out = socket.getOutputStream();
				out.write(strxml.getBytes());
				out.flush();
				out.close();
				
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
