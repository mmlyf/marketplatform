package com.mtpt.timework;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mtpt.bean.enumerate.SendMailType;
import com.mtpt.extend.SendMail;

@Component("dowmload")
public class DownloadTimeTask {
	private Logger log = Logger.getLogger(DownloadTimeTask.class);
 	/**
	 * 下载运营数据并通过邮件的方式发送至运营人员的手机上
	 */
	public void downloadFtpFile() {
		log.debug("开始下载ftp服务器上的文件！");
		ExtendMethod.downloadFtpFile();
		SendMail.sendMailForCommon("ftp下载服务器数据下载成功，并发送成功", "", SendMailType.DEVELOP);
	}
}
