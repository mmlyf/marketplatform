package com.mtpt.timework;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.mtpt.alibean.TBEquityData;
import com.mtpt.alibean.page.EquityDataPage;
import com.mtpt.aliservice.ITBEquityDataService;
import com.mtpt.aliservice.impl.TBEquityDataService;
import com.mtpt.bean.enumerate.SendMailType;
import com.mtpt.config.BaseConfig;
import com.mtpt.config.SpringContextUtil;
import com.mtpt.extend.SendMail;

import javafx.scene.chart.PieChart.Data;

@Component("outputEquity")
public class TimeToOutputEquityData {
	private Logger log = Logger.getLogger(TimeToOutputEquityData.class);
	@Resource
	private ITBEquityDataService service;
	public void outputEquityData() {
		log.debug("开始执行定时任务outputequity");
		EquityDataPage page = new EquityDataPage();
		log.debug(service.selectEquityDataWithResult(page));
//		log.debug("执行文件上传ftp的定时任务");
//		Date date = new Date();
//		long datetime = date.getTime() - 18*60*60*1000;
//		date = new Date(datetime);
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		String sectime = sdf.format(date);
//		List<TBEquityData> list = service.selectDataByAddtime(sectime);
//		sdf = new SimpleDateFormat("yyyyMMdd");
//		String filename = sdf.format(date)+".txt";
//		String filepath = ExtendMethod.outputDataToFTP(list,filename);
//		String message = "Today's equity data already in FTP Service,It Service path is in "+filepath;
//		log.debug(message);
//		SendMail.sendMailForCommon(message, "", SendMailType.DEVELOP);
	}
}
