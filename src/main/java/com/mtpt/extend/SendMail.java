package com.mtpt.extend;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.apache.log4j.Logger;

import com.mtpt.bean.enumerate.SendMailType;
import com.sun.mail.util.MailSSLSocketFactory;
public class SendMail {
	private static Logger log = Logger.getLogger(SendMail.class);
	private static String host = "smtp.mobile99.cn";
	private static String hostname = "yingy@mobile99.cn";
	private static String hostPassword = "hsdt@2018";
	private static Address[] sendDataMailTo ;
	private static Address[] mailTo;
	private static Address[] developTo;
	static {
		try {
			mailTo = InternetAddress.parse("32223815@qq.com,"
					+ "zhaoq@mobile99.cn,"
					+ "liuzw@mobile99.cn,"
					+ "benl@mobile99.cn");//,32223815@qq.com运营数据统计
//			mailTo = InternetAddress.parse("1453806177@qq.com");//测试环境
			sendDataMailTo = InternetAddress.parse("32223815@qq.com,yingy@mobile99.cn");//运营
			developTo = InternetAddress.parse("yingy@mobile99.cn");
//			sendDataMailTo = InternetAddress.parse("1453806177@qq.com");//测试环境
		}catch(Exception e) {
			log.error("解析发送邮箱群组失败");
		}
		
	}
	private static Properties setProperties() {
		Properties properties = new Properties();
		properties.setProperty("mail.debug", "true");
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.host", host);
		properties.setProperty("mail.transport.protocol", "smtp");
		try {
			MailSSLSocketFactory factory = new MailSSLSocketFactory();
			factory.setTrustAllHosts(true);
			properties.put("mail.smtp.ssl.enable", "true");
			properties.put("mail.smtp.ssl.socketFactory", factory);
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return properties;
	}
	
	public static boolean sendMailForCommon(String msg,String filepath,SendMailType type) {
		Properties properties = setProperties();
		Session session = Session.getInstance(properties);
		Message message = new MimeMessage(session);
		try {
			message.setSubject("智能营销平台");
			message.setFrom(new InternetAddress(hostname, "汇视达通网络科技有限公司"));
			Multipart part = new MimeMultipart();
			BodyPart bodyPart = new MimeBodyPart();
			bodyPart.setContent(msg, "text/html;charset=utf-8");
			part.addBodyPart(bodyPart);
			if (!"".equals(filepath)&&filepath!=null) {
				BodyPart filebody = new MimeBodyPart();
				FileDataSource fSource = new FileDataSource(filepath);
				filebody.setDataHandler(new DataHandler(fSource));
				String filename = MimeUtility.encodeWord(fSource.getFile().getName());
				filebody.setFileName(filename);
				part.addBodyPart(filebody);
			}
			message.setContent(part);
			Transport transport = session.getTransport();
			transport.connect(host, hostname, hostPassword);
			switch (type) {
			case ALL:
				transport.sendMessage(message, mailTo);
				break;
			case YUNYING:
				transport.sendMessage(message, sendDataMailTo);
				break;
			case DEVELOP:
				transport.sendMessage(message, developTo);
				break;
			default:
				break;
			}
			transport.close();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean sendMailForToMailAddress(String msg,String filepath,String mailaddress) {
		Properties properties = setProperties();
		Session session = Session.getInstance(properties);
		Message message = new MimeMessage(session);
		try {
			message.setSubject("智能营销平台");
			message.setFrom(new InternetAddress(hostname, "汇视达通网络科技有限公司"));
			Multipart part = new MimeMultipart();
			BodyPart bodyPart = new MimeBodyPart();
			bodyPart.setContent(msg, "text/html;charset=utf-8");
			part.addBodyPart(bodyPart);
			if (!"".equals(filepath)&&filepath!=null) {
				BodyPart filebody = new MimeBodyPart();
				FileDataSource fSource = new FileDataSource(filepath);
				filebody.setDataHandler(new DataHandler(fSource));
				String filename = MimeUtility.encodeWord(fSource.getFile().getName());
				filebody.setFileName(filename);
				part.addBodyPart(filebody);
			}
			message.setContent(part);
			Transport transport = session.getTransport();
			transport.connect(host, hostname, hostPassword);
			transport.sendMessage(message, new Address[] {new InternetAddress(mailaddress)});
			transport.close();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
