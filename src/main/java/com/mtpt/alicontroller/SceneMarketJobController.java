package com.mtpt.alicontroller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mtpt.alibean.TBLabel;
import com.mtpt.alibean.TBRecord;
import com.mtpt.alibean.TBReview;
import com.mtpt.alibean.TBSceneData;
import com.mtpt.alibean.TBSceneJob;
import com.mtpt.alibean.page.SceneJobPage;
import com.mtpt.aliservice.ITBLabelService;
import com.mtpt.aliservice.ITBSceneDataService;
import com.mtpt.aliservice.ITBSceneJobService;
import com.mtpt.bean.TBMssage;
import com.mtpt.bean.TBState;
import com.mtpt.extend.OutputFile;
import com.mtpt.methodforsend.SendFileIn;
import com.mtpt.methodforsend.SendModelIn;
import com.mtpt.methodforsend.SendSceneLabel;
import com.mtpt.methodforsend.SendTimeWorkFileIn;
import com.mtpt.service.ITBMssageService;
import com.mtpt.service.ITBStateService;

/**
 * 
 * @author lvgordon
 * 处理场景营销的任务数据（添加任务数据、修改任务数据和删除任务数据以及发送短信、审核之后的状态的更新）
 *
 */
@Controller
@RequestMapping("/scenejob")
public class SceneMarketJobController {
	private Logger log = Logger.getLogger(SceneMarketJobController.class);
	@Resource
	private ITBSceneDataService sceneDataService;
	@Resource
	private ITBSceneJobService sceneJobService;
	@Resource
	private ITBLabelService labelService;
	@Resource
	private ITBStateService stateService;
	@Resource 
	private ITBMssageService mssageService;
	
	private SimpleDateFormat sdf = null;
	
	/**
	 * 
	 * @param tbSceneJob
	 * @param request
	 * @param response
	 * 添加场景营销数据
	 * 
	 */
	@RequestMapping(value="/insertscenejob",method = {RequestMethod.GET,RequestMethod.POST})
	private void insertSceneMarketJob(TBSceneJob tbSceneJob,HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		String[] bqs = tbSceneJob.getSceneBq().split(",");
		List<String> reslist = new ArrayList<>();
		for(int i = 0 ; i< bqs.length;i++) {
			List<TBSceneData> listdata = sceneDataService.selectDataByLabel(bqs[i]);
			System.out.println("listdata的数据是："+listdata.size());
			List<String> liststr = new ArrayList<>();
			for(TBSceneData tbSceneData : listdata) {
				liststr.add(tbSceneData.getSceneDn());
			}
			switch (tbSceneJob.getBqOpera()) {
			case "1"://交集
				if(reslist.isEmpty()) {
					reslist = liststr;
				}else {
					if (!liststr.isEmpty()) {
						reslist.retainAll(liststr);
					}
				}
				break;
			case "2"://并集
				if(reslist.isEmpty()) {
					reslist = liststr;
				}else {
					if (!liststr.isEmpty()) {
						liststr.removeAll(reslist);
						reslist.addAll(liststr);
					}
				}
				break;
			case "3"://无操作
				if(reslist.isEmpty()) {
					reslist = liststr;
				}else {
					if (!liststr.isEmpty()) {
						reslist.addAll(liststr);
					}
				}
				break;
			default:
				break;
			}
		}
		tbSceneJob.setDataCount(reslist.size());
		tbSceneJob.setState(0);
		if(tbSceneJob.getIsDelblack()!=null) {
			tbSceneJob.setIsDelblack("是");
		}else {
			tbSceneJob.setIsDelblack("否");
		}
		if(tbSceneJob.getIsTimework()!=null) {
			tbSceneJob.setIsTimework("是");
		}else {
			tbSceneJob.setIsTimework("否");
			tbSceneJob.setWorkTime(null);
		}
		if (tbSceneJob.getIsDeldays()!=null) {
			tbSceneJob.setIsDeldays("是");
		}else {
			tbSceneJob.setIsDeldays("否");
			tbSceneJob.setDeldays(null);
		}
		int result = sceneJobService.insertSelective(tbSceneJob);
		JSONObject json = new JSONObject();
		if (result>0) {
			json.put("code", 0);
		}else {
			json.put("code", 1);
		}
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
	 * @param request
	 * @param response
	 * 查询获取场景营销任务的数据。并可以根据条件进行查询
	 * 
	 */
	@RequestMapping(value="/selectscenejob",method = {RequestMethod.GET,RequestMethod.POST})
	private void selectSceneJobData(SceneJobPage page,HttpServletRequest request,HttpServletResponse response) {
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("realname");
		if(page.getKeytype().equals("add_man")||page.getKeytype().equals("review_man")) {
			page.setKeyword(name);
		}
		int totals = sceneJobService.selectSceneJobCountByPage(page);
		page.setTotalRecord(totals);
		List<TBSceneJob> listjob = sceneJobService.selectSceneJobByPage(page);
		JSONObject jsonmap = new JSONObject();
		List<JSONObject> jsonlist = new ArrayList<>();
		for(TBSceneJob tbSceneJob : listjob) {
			JSONObject json = new JSONObject();
			json.put("id", tbSceneJob.getId());
			String[] labels = tbSceneJob.getSceneBq().split(",");
			StringBuilder sb = new  StringBuilder();
			for(int i = 0;i<labels.length;i++) {
				TBLabel tbLabel = labelService.selectByPrimaryKey(Integer.parseInt(labels[i]));
				if (sb.length()>0) {
					sb.append(",");
				}
				sb.append(tbLabel.getBqName());
			}
			json.put("label", sb.toString());
			String bqstr = "";
			switch (tbSceneJob.getBqOpera()) {
			case "1":
				bqstr = "交";
				break;
			case "2":
				bqstr = "或";
				break;
			case "3":
				bqstr = "无";
				break;
			default:
				break;
			}
			json.put("label_opera", bqstr);
			json.put("data_count", tbSceneJob.getDataCount());
			json.put("isTimework", tbSceneJob.getIsTimework());
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if (tbSceneJob.getWorkTime()!=null) {
				String timework = sdf.format(tbSceneJob.getWorkTime());
				json.put("timework", timework);
			}else {
				json.put("timework", "");
			}
			json.put("isdelblack", tbSceneJob.getIsDelblack());
			json.put("isdeldays", tbSceneJob.getIsDeldays());
			if (tbSceneJob.getIsDeldays().equals("是")) {
				json.put("deldays", tbSceneJob.getDeldays());
			}else {
				json.put("deldays", "");
			}
			TBMssage tbMssage = mssageService.selectByPrimaryKey(tbSceneJob.getMisId());
			json.put("misid", tbMssage.getMisTitle());
			json.put("miscontent", tbMssage.getMisContent());
			json.put("last_opera", tbSceneJob.getLastOpera());
			json.put("review_man", tbSceneJob.getReviewMan());
			json.put("addman", tbSceneJob.getAddMan());
			TBState tbState = stateService.selectByState(tbSceneJob.getState());
			json.put("state", tbState.getStatename());
			String addtime = sdf.format(tbSceneJob.getAddTime());
			json.put("addtime", addtime);
			jsonlist.add(json);
		}
		jsonmap.put("code", 0);
		jsonmap.put("msg", "");
		jsonmap.put("data", jsonlist);
		jsonmap.put("count", totals);
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
	 * @param tbSceneJob
	 * @param request
	 * @param response
	 * 用于更新场景营销任务的状态请求
	 * 传入的数据有任务ID和状态
	 * 
	 */
	@RequestMapping(value="/updatescenejob",method = {RequestMethod.GET,RequestMethod.POST})
	private void updateSceneJobForState(TBSceneJob tbSceneJob,HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		log.debug("当前提交的状态是："+tbSceneJob.getState());
		int result = sceneJobService.updateByPrimaryKeySelective(tbSceneJob);
		JSONObject json = new JSONObject();
		if(result>0) {
			json.put("code", 0);
		}else {
			json.put("code", 1);
		}
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
	 * @param jobid
	 * @param request
	 * @param response
	 * 发送标签数据的任务
	 * 
	 */
	@RequestMapping(value="/sendscenejob",method= {RequestMethod.GET,RequestMethod.POST})
	private void sendMessageSceneJob(Integer jobid,String worker,HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		log.info(worker+"执行\"标签任务\"发送操作");
		JSONObject json = new JSONObject();
		json.put("code", 0);
		try {
			PrintWriter pw = response.getWriter();
			pw.write(json.toString());
			pw.flush();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TBSceneJob tbSceneJob = sceneJobService.selectByPrimaryKey(jobid);
		TBSceneJob sceneJob = new TBSceneJob();
		if(tbSceneJob.getIsTimework().equals("是")) {
			log.debug("当前为定时任务");
			sceneJob.setId(tbSceneJob.getId());
			sceneJob.setState(101);
			sceneJob.setLastOpera(worker);
			sceneJobService.updateByPrimaryKeySelective(sceneJob);//更新当前状态
			//执行定时任务的操作
			SendTimeWorkFileIn.addSceneTaskId(jobid);
		}else {
			SendSceneLabel.waitsend.add(jobid);
			sceneJob.setId(tbSceneJob.getId());
			sceneJob.setState(5);
			sceneJob.setLastOpera(worker);
			sceneJobService.updateByPrimaryKeySelective(sceneJob);
			SendSceneLabel.setJob_id(jobid);
		}
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * 暂停标签数据下发的短信任务
	 * 
	 */
	@RequestMapping(value="/stopsendjob",method= {RequestMethod.GET,RequestMethod.POST})
	private void stopSendSceneJob(HttpServletRequest request,HttpServletResponse response) {
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("realname");//获取操作用户的真是姓名
		log.info(name+"执行暂停\"标签数据发送\"任务。");
		JSONObject json = new JSONObject();
		if(SendSceneLabel.waitsend.isEmpty()) {//判断当前下发队列中是不是空；若为空则队列无下发任务
			json.put("code", 1);
		}else {
			SendSceneLabel.isStop = true;
			json.put("code", 0);
			for(int id: SendSceneLabel.waitsend) {
				TBSceneJob sceneJob = new TBSceneJob();
				sceneJob.setId(id);
				sceneJob.setState(6);
				sceneJobService.updateByPrimaryKeySelective(sceneJob);
			}
		}
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
	 * @param request
	 * @param response
	 * 继续发送标签任务
	 * 
	 */
	@RequestMapping(value="/startsendjob",method= {RequestMethod.GET,RequestMethod.POST})
	private void startSendSceneJob(HttpServletRequest request,HttpServletResponse response) {
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("realname");
		log.info(name+"执行\"标签数据下发的\"续发任务！");
		SendFileIn.isStop = false;
		TBSceneJob sceneJob = new TBSceneJob();
		for(int id:SendSceneLabel.waitsend) {
			if(id==SendSceneLabel.getJob_id()) {
				sceneJob.setId(id);
				sceneJob.setState(3);
				sceneJobService.updateByPrimaryKeySelective(sceneJob);
			}else {
				sceneJob.setId(id);
				sceneJob.setState(5);
				sceneJobService.updateByPrimaryKeySelective(sceneJob);
			}
			try {
				PrintWriter pw = response.getWriter();
				JSONObject json = new JSONObject();
				json.put("code", 0);
				pw.write(json.toString());
				pw.flush();
				pw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * 停止标签所有的发送任务
	 * 
	 */
	@RequestMapping(value="/endsendjob",method= {RequestMethod.GET,RequestMethod.POST})
	private void EndSendSceneJob(HttpServletRequest request,HttpServletResponse response) {
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("realname");
		log.info(name+"执行停止所有\"标签数据\"下发任务");
		JSONObject json = new JSONObject();
		if(SendSceneLabel.waitsend.isEmpty()) {
			log.info("当前无群发任务");
			json.put("code", 1);
		}else {
			SendSceneLabel.isEnd = true;//设置停止任务的标志为true
			json.put("code", 0);
		}
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
	 * @param jobid
	 * @param request
	 * @param response
	 * 删除可删除的标签任务
	 * 
	 */
	@RequestMapping(value="deletescenejob",method= {RequestMethod.GET,RequestMethod.POST})
	private void deleteSendSceneJob(Integer jobid,HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		int result = sceneJobService.deleteByPrimaryKey(jobid);
		JSONObject json = new JSONObject();
		if(result>0) {
			json.put("code", 0);
		}else {
			json.put("code", 1);
		}
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
	 * @param tbSceneJob
	 * @param request
	 * @param response
	 * 标签数据导出数据
	 * 
	 */
	@RequestMapping(value="/outputjob",method = {RequestMethod.GET,RequestMethod.POST})
	private void outputSceneJobData(TBSceneJob tbSceneJob,HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		String[] labels = tbSceneJob.getSceneBq().split(",");
		List<String> resultlist = new ArrayList<>();
		for(int i = 0 ; i< labels.length; i++) {
			List<TBSceneData> scenedatalist = sceneDataService.selectDataByLabel(labels[i]);
			List<String> phonelist = new ArrayList<>();
			for(TBSceneData tbSceneData:scenedatalist) {
				phonelist.add(tbSceneData.getSceneDn());
			}
			switch (tbSceneJob.getBqOpera()) {
			case "1"://交集
				if (resultlist.isEmpty()) {
					resultlist = phonelist;
				}else {
					if (!phonelist.isEmpty()) {
						resultlist.retainAll(phonelist);
					}
				}
				break;
			case "2"://并集
			case "3"://无
				if (resultlist.isEmpty()) {
					resultlist = phonelist;
				}else {
					if (!phonelist.isEmpty()) {
						phonelist.removeAll(resultlist);
						resultlist.addAll(phonelist);
					}
				}
				break;
			default:
				break;
			}
		}
		String filepath = OutputFile.outputSceneLabelData(resultlist);
		JSONObject json = new JSONObject();
		json.put("filepath", filepath);
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
}
