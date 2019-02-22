package com.mtpt.methodforsend;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import org.apache.log4j.Logger;

import com.sun.org.apache.xml.internal.security.utils.Base64;

public class HandlerSend implements Runnable{
	private Logger log = Logger.getLogger(HandlerSend.class);
	private List<String> phonelist;
	private String messageContent;
	private Integer reid;
	private Integer flag;//1:文件导入；2：维度筛选；3:标签数据
	//产品接入号
//	String spNumber="10655883";
	String spNumber = "1065572778";//河北接入号
	//业务代码
	String servcieType="90860230";
	//linkId
	String linkId = "MOODDDS";
	char reportflag = '1';
	public HandlerSend(List<String> phonelist,String messageContent,Integer reid,Integer flag) {
		this.phonelist = phonelist;
		this.messageContent = messageContent;
		this.reid = reid;
		this.flag = flag;
	}

	public synchronized void run() {
		// TODO Auto-generated method stub
		String strxml ="{<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
				+ "<gwsmip>\n" + "  <message_header>\n"
				+ "    <command_id>0x3</command_id>\n"
				+ "    <sequence_number/>\n" + "  </message_header>\n"
				+ "  <message_body>\n" + "    <pk_total>1</pk_total>\n"
				+ "    <pk_number>1</pk_number>\n" + "    <user_numbers>\n";
		for(String dn :phonelist) {
			strxml += "<user_number>"+dn+"</user_number>";
		}		
		strxml += "    </user_numbers>\n"
				+ "    <sp_number>"+spNumber+"</sp_number>\n"
				+ "    <service_type>"+servcieType+"</service_type>\n"
				+ "    <link_id>"+linkId+"</link_id>\n"
				+ "    <message_content>" + Base64.encode(messageContent.getBytes())
				+ "</message_content>\n"
				+ "    <report_flag>"+reportflag+"</report_flag>\n"
				+ "   </message_body>\n" + "</gwsmip>\n}";
		System.out.println("strxml的值是："+strxml);
		boolean res = DataInDB.sendPhoneInDB(phonelist, flag, reid);
		if (res) {
			log.debug("数据已经存入数据表");
		}else {
			log.debug("数据存入失败");
		}
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
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

