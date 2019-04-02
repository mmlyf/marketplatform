package com.mtpt.aliservice.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtpt.alibean.TBLabel;
import com.mtpt.alibean.TBSceneData;
import com.mtpt.alibean.TBSceneJob;
import com.mtpt.alibean.page.SceneJobPage;
import com.mtpt.alidao.TBLabelMapper;
import com.mtpt.alidao.TBSceneDataMapper;
import com.mtpt.alidao.TBSceneJobMapper;
import com.mtpt.aliservice.ISceneMarketJobService;
import com.mtpt.bean.TBMssage;
import com.mtpt.bean.TBState;
import com.mtpt.dao.TBMssageMapper;
import com.mtpt.dao.TBStateMapper;
import com.mtpt.extend.OutputFile;
import com.mtpt.methodforsend.SendFileIn;
import com.mtpt.methodforsend.SendSceneLabel;
import com.mtpt.methodforsend.SendTimeWorkFileIn;

@Service("sceneMarketJobService")
public class SceneMarketJobService implements ISceneMarketJobService{

	private Logger log = Logger.getLogger(SceneMarketJobService.class);
	@Autowired 
	private TBSceneJobMapper scenejobMapper;
	@Autowired
	private TBSceneDataMapper scenedataMapper;
	@Autowired
	private TBLabelMapper labelMapper;
	@Autowired 
	private TBMssageMapper mssageMapper;
	@Autowired
	private TBStateMapper stateMapper;
	
	private SimpleDateFormat sdf = null;
	
	@Override
	public JSONObject insertSceneMarketJob(TBSceneJob tbSceneJob) {
		// TODO Auto-generated method stub
		String[] bqs = tbSceneJob.getSceneBq().split(",");
		List<String> reslist = new ArrayList<>();
		for(int i = 0 ; i< bqs.length;i++) {
			List<TBSceneData> listdata = scenedataMapper.selectDataByLabel(bqs[i]);
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
		int result = scenejobMapper.insertSelective(tbSceneJob);
		JSONObject json = new JSONObject();
		if (result>0) {
			json.put("code", 0);
		}else {
			json.put("code", 1);
		}
		return json;
	}

	@Override
	public JSONObject selectSceneJobData(SceneJobPage page) {
		// TODO Auto-generated method stub
		int totals = scenejobMapper.selectSceneJobCountByPage(page);
		page.setTotalRecord(totals);
		List<TBSceneJob> listjob = scenejobMapper.selectSceneJobByPage(page);
		JSONObject jsonmap = new JSONObject();
		List<JSONObject> jsonlist = new ArrayList<>();
		for(TBSceneJob tbSceneJob : listjob) {
			JSONObject json = new JSONObject();
			json.put("id", tbSceneJob.getId());
			String[] labels = tbSceneJob.getSceneBq().split(",");
			StringBuilder sb = new  StringBuilder();
			for(int i = 0;i<labels.length;i++) {
				TBLabel tbLabel = labelMapper.selectByPrimaryKey(Integer.parseInt(labels[i]));
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
			TBMssage tbMssage = mssageMapper.selectByPrimaryKey(tbSceneJob.getMisId());
			if (tbMssage==null) {
				json.put("misid", "");
				json.put("miscontent", "");
			}else {
				json.put("misid", tbMssage.getMisTitle());
				json.put("miscontent", tbMssage.getMisContent());
			}
			json.put("last_opera", tbSceneJob.getLastOpera());
			json.put("review_man", tbSceneJob.getReviewMan());
			json.put("addman", tbSceneJob.getAddMan());
			TBState tbState = stateMapper.selectByState(tbSceneJob.getState());
			json.put("state", tbState.getStatename());
			String addtime = sdf.format(tbSceneJob.getAddTime());
			json.put("addtime", addtime);
			jsonlist.add(json);
		}
		jsonmap.put("code", 0);
		jsonmap.put("msg", "");
		jsonmap.put("data", jsonlist);
		jsonmap.put("count", totals);
		return jsonmap;
	}

	@Override
	public JSONObject updateSceneJobForState(TBSceneJob tbSceneJob) {
		// TODO Auto-generated method stub
		int result = scenejobMapper.updateByPrimaryKeySelective(tbSceneJob);
		JSONObject json = new JSONObject();
		if(result>0) {
			json.put("code", 0);
		}else {
			json.put("code", 1);
		}
		return json;
	}

	@Override
	public JSONObject stopSendSceneJob() {
		// TODO Auto-generated method stub
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
				scenejobMapper.updateByPrimaryKeySelective(sceneJob);
			}
		}
		return json;
	}

	@Override
	public void startSendSceneJob() {
		// TODO Auto-generated method stub
		SendFileIn.isStop = false;
		TBSceneJob sceneJob = new TBSceneJob();
		for(int id:SendSceneLabel.waitsend) {
			if(id==SendSceneLabel.getJob_id()) {
				sceneJob.setId(id);
				sceneJob.setState(3);
				scenejobMapper.updateByPrimaryKeySelective(sceneJob);
			}else {
				sceneJob.setId(id);
				sceneJob.setState(5);
				scenejobMapper.updateByPrimaryKeySelective(sceneJob);
			}
		}
	}

	@Override
	public JSONObject EndSendSceneJob() {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		if(SendSceneLabel.waitsend.isEmpty()) {
			log.info("当前无群发任务");
			json.put("code", 1);
		}else {
			SendSceneLabel.isEnd = true;//设置停止任务的标志为true
			json.put("code", 0);
		}
		return json;
	}

	@Override
	public JSONObject deleteSendSceneJob(Integer jobid) {
		// TODO Auto-generated method stub
		int result = scenejobMapper.deleteByPrimaryKey(jobid);
		JSONObject json = new JSONObject();
		if(result>0) {
			json.put("code", 0);
		}else {
			json.put("code", 1);
		}
		return json;
	}

	@Override
	public JSONObject outputSceneJobData(TBSceneJob tbSceneJob) {
		// TODO Auto-generated method stub
		String[] labels = tbSceneJob.getSceneBq().split(",");
		List<String> resultlist = new ArrayList<>();
		for(int i = 0 ; i< labels.length; i++) {
			List<TBSceneData> scenedatalist = scenedataMapper.selectDataByLabel(labels[i]);
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
		return json;
	}

	@Override
	public void sendMessageSceneJob(Integer jobid,String worker) {
		// TODO Auto-generated method stub
		TBSceneJob tbSceneJob = scenejobMapper.selectByPrimaryKey(jobid);
		TBSceneJob sceneJob = new TBSceneJob();
		if(tbSceneJob.getIsTimework().equals("是")) {
			log.debug("当前为定时任务");
			sceneJob.setId(tbSceneJob.getId());
			sceneJob.setState(101);
			sceneJob.setLastOpera(worker);
			scenejobMapper.updateByPrimaryKeySelective(sceneJob);//更新当前状态
			//执行定时任务的操作
			SendTimeWorkFileIn.addSceneTaskId(jobid);
		}else {
			SendSceneLabel.waitsend.add(jobid);
			sceneJob.setId(tbSceneJob.getId());
			sceneJob.setState(5);
			sceneJob.setLastOpera(worker);
			scenejobMapper.updateByPrimaryKeySelective(sceneJob);
			SendSceneLabel.setJob_id(jobid);
		}
	}

}
