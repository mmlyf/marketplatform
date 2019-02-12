package com.mtpt.timework;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mtpt.alibean.TBRecord;
import com.mtpt.alibean.TBReview;
import com.mtpt.aliservice.ITBCbRecordService;
import com.mtpt.aliservice.ITBRecordService;
import com.mtpt.aliservice.ITBReviewService;
import com.mtpt.bean.DataTotal;
import com.mtpt.bean.enumerate.DataTotalType;
import com.mtpt.bean.enumerate.SendMailType;
import com.mtpt.bean.page.TotalPage;
import com.mtpt.extend.SendMail;
import com.mtpt.extend.TimeTaskMethod;
import com.mtpt.methodforsend.SendFileIn;
import com.mtpt.methodforsend.SendModelIn;
import com.mtpt.service.IDataTotalService;
import com.mtpt.service.IIceDsjOrdersService;
import com.mtpt.service.IMoReceiveService;
import com.mtpt.service.IOrdersService;
import com.mysql.cj.util.TimeUtil;
/*
 * cron的格式
 * “0 0 12 * * ?” 每天中午十二点触发 
 * “0 15 10 ? * *” 每天早上10：15触发 
 * “0 15 10 * * ?” 每天早上10：15触发 
 * “0 15 10 * * ? *” 每天早上10：15触发 
 * “0 15 10 * * ? 2005” 2005年的每天早上10：15触发 
 * “0 * 14 * * ?” 每天从下午2点开始到2点59分每分钟一次触发 
 * “0 0/5 14 * * ?” 每天从下午2点开始到2：55分结束每5分钟一次触发 
 * “0 0/5 14,18 * * ?” 每天的下午2点至2：55和6点至6点55分两个时间段内每5分钟一次触发 
 * “0 0-5 14 * * ?” 每天14:00至14:05每分钟一次触发 
 * “0 10,44 14 ? 3 WED” 三月的每周三的14：10和14：44触发 
 * “0 15 10 ? * MON-FRI” 每个周一、周二、周三、周四、周五的10：15触发
 */
/**
 * 
 * @author lvgordon
 * 定时任务类，用于执行每天固定时间的任务
 */
@Component("totalcount")
public class TotalCountTimerTask {

	private Logger log = Logger.getLogger(TotalCountTimerTask.class);
	@Resource 
	IOrdersService orderService;
	@Resource 
	IMoReceiveService morecService;
	@Resource 
	ITBCbRecordService tbcbService;
	@Resource 
	IIceDsjOrdersService icedsjoService;
	@Resource 
	IDataTotalService datatotalService;
	@Resource 
	ITBReviewService reviewService;
	@Resource 
	ITBRecordService recordService;
	/**
	 * 每天23点59启动任务
	 * 统计订单、上行和下行的总数据
	 */
	public void totalCount()
	{
		log.debug("开始执行定时任务！");
		Date date = new Date();
		long time = date.getTime() - 24*60*60*1000;
		Date rundate = new Date(time);
		TotalPage page = new TotalPage();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		log.debug("当前执行时间是:"+sdf.format(rundate));
		String datestr = sdf.format(rundate);
		page.setAdtime(datestr);
		Integer moall = morecService.selectCountAllByPage(page);//上行总
		Integer mtall = tbcbService.selectCountByPage(page);//下行总（不分下行方式）
		Integer orderall = orderService.selectCountAllByPage(page);//订单总
		Integer icereserve = icedsjoService.selectCountByPage(page);//冰激凌预约数
		page.setState(1);
		Integer moable = morecService.selectCountAllByPage(page);//上行有效
		Integer ordersuc = orderService.selectCountAllByPage(page);//订单成功数
		Integer mtsuc = tbcbService.selectCountByPage(page);//下行成功
		page.setState(2);
		Integer moabledx = morecService.selectCountAllByPage(page);//上行低消有效
		Integer ordersucdx = orderService.selectCountAllByPage(page);//低消订购成功
		page.setState(3);
		Integer moableice = morecService.selectCountAllByPage(page);//上行冰激凌有效
		Integer orderdissucdx = orderService.selectCountAllByPage(page);//低消订购不成功
		log.debug("从数据库中查询数据已经完成");
		int modisable = moall - moable;//上行无效指令数
		int mtdissuc = mtall - mtsuc;//下行不成功数 
		int moablellb = moable - moabledx - moableice;//流量包上行有效指令
		int orderdissuc = orderall - ordersuc;//订购不成功数
		int ordersucllb = ordersuc - ordersucdx;//流量包订购成功过数
		int orderdissucllb = orderdissuc - orderdissucdx;//流量包订购不成功数
		log.debug("数据进行减法运算已完成");
		float mtrate = 0;
		float morate = 0;
		float modxrate = 0;
		float moicerate = 0;
		float mollbrate = 0;
		float resorderrate = 0;
		float orderrate = 0;
		float ordersucrate = 0;
		float ordersucdxrate = 0;
		float ordersucllbrate = 0;
		if(mtall!=0) {
			mtrate = Float.parseFloat(String.format("%.7f",((mtsuc*1.0)/mtall)));//下行率
		}
		if(mtsuc!=0) {
			morate = Float.parseFloat(String.format("%.7f",((moall*1.0)/mtsuc)));//上行率
		}
		if(moall!=0) {
			modxrate = Float.parseFloat(String.format("%.7f",((moabledx*1.0)/moall)));//低消上行率
			moicerate = Float.parseFloat(String.format("%.7f",((moableice*1.0)/moall)));//冰激凌上行率
			mollbrate = Float.parseFloat(String.format("%.7f",((moablellb*1.0)/moall)));//流量包上行率
			resorderrate = Float.parseFloat(String.format("%.7f",((moable*1.0)/moall)));//回复订购率
		}
		if(moable!=0) {
			orderrate = Float.parseFloat(String.format("%.7f",((orderall*1.0)/moable)));//订购率
		}
		if(orderall!=0) {
			ordersucrate = Float.parseFloat(String.format("%.7f",((ordersuc*1.0)/orderall)));//订购成功率
		}
		if (moabledx!=0) {
			ordersucdxrate = Float.parseFloat(String.format("%.7f",((ordersucdx*1.0)/moabledx)));//低消订购成功率
		}
		if (moablellb!=0) {
			ordersucllbrate = Float.parseFloat(String.format("%.7f",((ordersucllb*1.0)/moablellb)));//流量包订购成功率
		}
		log.debug("当前数据运算已经完成！");
		DataTotal dataTotal = new DataTotal();
		dataTotal.setMtall(mtall);
		dataTotal.setMtsuc(mtsuc);
		dataTotal.setMtdissuc(mtdissuc);
		dataTotal.setMoall(moall);
		dataTotal.setMoable(moable);
		dataTotal.setMoabledx(moabledx);
		dataTotal.setMoableice(moableice);
		dataTotal.setMoablellb(moablellb);
		dataTotal.setModisable(modisable);
		dataTotal.setIcesuc(icereserve);
		dataTotal.setOrderall(orderall);
		dataTotal.setOrdersuc(ordersuc);
		dataTotal.setOrdersucdx(ordersucdx);
		dataTotal.setOrdersucllb(ordersucllb);
		dataTotal.setOrderdissuc(orderdissuc);
		dataTotal.setOrderdissucdx(orderdissucdx);
		dataTotal.setOrderdissucllb(orderdissucllb);
		dataTotal.setMtrate(mtrate);
		dataTotal.setMorate(morate);
		dataTotal.setModxrate(modxrate);
		dataTotal.setMoicerate(moicerate);
		dataTotal.setMollbrate(mollbrate);
		dataTotal.setOrderrate(orderrate);
		dataTotal.setOrdersucrate(ordersucrate);
		dataTotal.setOrdersucdxrate(ordersucdxrate);
		dataTotal.setOrdersucllbrate(ordersucllbrate);
		dataTotal.setResorderrate(resorderrate);
		dataTotal.setAddTime(datestr);
		String msg = ExtendMethod.getMessageStr(dataTotal);
		log.debug("当前发送的消息是："+msg);
		SendMail.sendMailForCommon(msg, "",SendMailType.DEVELOP);
		int res = datatotalService.insertSelective(dataTotal);
		log.debug("当前数据存储的结果是："+res);
		if(res>0) {
			SendMail.sendMailForCommon("统计数据存储成功", "", SendMailType.DEVELOP);
		}
		
	}
}


