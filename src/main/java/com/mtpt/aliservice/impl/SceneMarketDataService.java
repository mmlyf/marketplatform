package com.mtpt.aliservice.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mtpt.alibean.TBLabel;
import com.mtpt.alibean.TBSceneData;
import com.mtpt.alibean.TBSceneJob;
import com.mtpt.alibean.page.SceneJobPage;
import com.mtpt.alidao.TBLabelMapper;
import com.mtpt.alidao.TBSceneDataMapper;
import com.mtpt.alidao.TBSceneJobMapper;
import com.mtpt.aliservice.ISceneMarketDataService;
import com.mtpt.bean.TBMssage;
import com.mtpt.bean.TBState;
import com.mtpt.bean.page.SceneDataPage;
import com.mtpt.config.BaseConfig;
import com.mtpt.dao.TBMssageMapper;
import com.mtpt.dao.TBStateMapper;
import com.mtpt.extend.OutputFile;
import com.mtpt.methodforsend.SendFileIn;
import com.mtpt.methodforsend.SendSceneLabel;
import com.mtpt.methodforsend.SendTimeWorkFileIn;
import com.mtpt.scenemarket.SceneMarketExtendMethod;

@Service("sceneMarketDataService")
public class SceneMarketDataService implements ISceneMarketDataService{

	private Logger log = Logger.getLogger(SceneMarketDataService.class);
	@Autowired
	private TBLabelMapper labelMapper;
	@Autowired 
	private TBSceneDataMapper sceneDataMapper;
	@Autowired 
	private TBSceneJobMapper sceneJobMapper;
	@Autowired 
	private TBStateMapper stateMapper;
	@Autowired 
	private TBMssageMapper mssageMapper;
	
	private SimpleDateFormat sdf = null;
	
	@Override
	public JSONObject selectAllLabelData() {
		// TODO Auto-generated method stub
		List<TBLabel> labellist = labelMapper.selectAllData();
		JSONObject jsonmap = new JSONObject();
		List<JSONObject> jsonlist = new ArrayList<>();
		if (labellist!=null) {
			for (TBLabel tbLabel : labellist) {
				JSONObject map = new JSONObject();
				map.put("id", tbLabel.getId());
				map.put("name", tbLabel.getBqName());
				jsonlist.add(map);
			}
		}
		jsonmap.put("code", 0);
		jsonmap.put("data", jsonlist);
		jsonmap.put("msg", "");
		jsonmap.put("count", jsonlist.size());
		return jsonmap;
	}

	@Override
	public JSONObject submitSceneMarketDataAndUploadFile(MultipartFile scenefile, String bq, String realname,
			String opera) {
		// TODO Auto-generated method stub
		log.debug("当前标签的值是："+bq);
		String[] arrbq = bq.split(",");
		for(String bqid:arrbq) {
			TBLabel tbBq = labelMapper.selectByPrimaryKey(Integer.parseInt(bqid));
			log.info(realname+"导入标签数据，标签为："+tbBq.getBqName());
		}
		String filename = scenefile.getOriginalFilename();
		String houzhui = filename.substring(filename.indexOf(".")+1, filename.length());
		log.debug("后缀名是："+filename);
		Date date = new Date();
		sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		String newfilename = sdf.format(date);
		String allpath = BaseConfig.SCENE_MARKET_FILEPATH+newfilename+houzhui;
		log.debug("当前场景营销的数据存储位置是："+allpath);
		try {
			OutputStream out = new FileOutputStream(allpath);
			InputStream in = scenefile.getInputStream();
			int value;
			while((value=in.read())!=-1) {
				out.write(value);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean result = SceneMarketExtendMethod.uploadFileInput(allpath, bq,opera);
		JSONObject json = new JSONObject();

		if (result) {
			log.debug("添加成功!");
			json.put("code", 0);
		}else {
			json.put("code", 1);
		}
		return json;
	}

	@Override
	public JSONObject insertNewLabelData(String labelvalue) {
		// TODO Auto-generated method stub
		TBLabel tbLabel = new TBLabel();
		tbLabel.setBqName(labelvalue);
		int result = labelMapper.insertSelective(tbLabel);
		JSONObject json = new JSONObject();
		if(result>0) {
			json.put("code", 0);
		}else {
			json.put("code", 1);
		}
		return json;
	}

	@Override
	public JSONObject selectSceneMarketDataByPage(SceneDataPage page) {
		// TODO Auto-generated method stub
		List<String> phonelist = new ArrayList<>();
		List<String> resultlist = new ArrayList<>();
		List<JSONObject> jsonlist = new ArrayList<>();
		JSONObject jsonmap = new JSONObject();
		int totalcount = 0;
		if (page.getBq()==null||page.getBq().equals("")) {
			totalcount = sceneDataMapper.selectDataCountByPage(page);
			System.out.println("当前数量是："+totalcount);
			page.setTotalRecord(totalcount);
			List<TBSceneData> list = sceneDataMapper.selectDataByPage(page);
			int count = 1;
			for(TBSceneData tbSceneData : list) {
				JSONObject json = new JSONObject();
				json.put("id", count);
				json.put("dn", tbSceneData.getSceneDn());
				TBLabel tbLabel = labelMapper.selectByPrimaryKey(Integer.parseInt(tbSceneData.getLabelId()));
				json.put("bq", tbLabel.getBqName());
				jsonlist.add(json);
				count++;
			}
			jsonmap.put("count", totalcount);
			jsonmap.put("msg", "");
			jsonmap.put("code", 0);
			jsonmap.put("data", jsonlist);
		}else {
			String[] labelidarray = page.getBq().split(",");	
			int count;
			switch (page.getOpera()) {
			case "1"://交集
				System.out.println("执行交集");
				String labelstr = "";
				for(int i = 0 ; i<labelidarray.length;i++) {
					String id = labelidarray[i];
					page.setBq(id);//设置需要查询的标签ID
					int totals = sceneDataMapper.selectDataCountByPage(page);
					page.setTotalRecord(totals);
					List<TBSceneData> list = sceneDataMapper.selectDataByPage(page);
					for(TBSceneData tbSceneData:list) {
						phonelist.add(tbSceneData.getSceneDn());
					}
					if(resultlist.isEmpty()) {
						resultlist = phonelist;
					}else {
						if (!phonelist.isEmpty()) {
							resultlist.retainAll(phonelist);
						}
					}
					TBLabel tbLabel = labelMapper.selectByPrimaryKey(Integer.parseInt(id));
					if (i+1<labelidarray.length) {
						labelstr += tbLabel.getBqName() + ",";
					}else {
						labelstr += tbLabel.getBqName();
					}
					
				}
				count = 1;
				for(String phonestr : resultlist) {
					JSONObject json = new JSONObject();
					json.put("id", count);
					json.put("dn", phonestr);
					json.put("bq", labelstr);
					jsonlist.add(json);
					count++;
				}
				jsonmap.put("count", totalcount);
				jsonmap.put("msg", "");
				jsonmap.put("code", 0);
				jsonmap.put("data", jsonlist);
				break;
			case "2"://并集
			case "3":
				System.out.println("执行补集");
				List<TBSceneData> listscene = new ArrayList<>();
				for(int i = 0 ; i<labelidarray.length;i++) {
					String id = labelidarray[i];
					page.setBq(id);//设置需要查询的标签ID
					int totals = sceneDataMapper.selectDataCountByPage(page);
					page.setTotalRecord(totals);
					List<TBSceneData> list = sceneDataMapper.selectDataByPage(page);
					listscene.addAll(list);
				}
				if (!listscene.isEmpty()) {
					count = 1;
					for(TBSceneData tbSceneData : listscene) {
						JSONObject json = new JSONObject();
						json.put("id", count);
						json.put("dn", tbSceneData.getSceneDn());
						TBLabel tbLabel = labelMapper.selectByPrimaryKey(Integer.parseInt(tbSceneData.getLabelId()));
						json.put("bq", tbLabel.getBqName());
						jsonlist.add(json);
					}
				}
				jsonmap.put("count", listscene.size());
				jsonmap.put("msg", "");
				jsonmap.put("code", 0);
				jsonmap.put("data", jsonlist);
				break;
			default:
				break;
			}
		}
		return jsonmap;
	}

	@Override
	public JSONObject insertSceneMarketJobData(TBSceneJob tbSceneJob) {
		// TODO Auto-generated method stub
		String[] bqs = tbSceneJob.getSceneBq().split(",");
		List<String> reslist = new ArrayList<>();
		for(int i = 0 ; i< bqs.length;i++) {
			List<TBSceneData> listdata = sceneDataMapper.selectDataByLabel(bqs[i]);
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
		int result = sceneJobMapper.insertSelective(tbSceneJob);
		JSONObject json = new JSONObject();
		if (result>0) {
			json.put("code", 0);
		}else {
			json.put("code", 1);
		}
		return json;
	}

	@Override
	public JSONObject selectSceneMarketJobDataByPage(SceneJobPage page,HttpServletRequest request) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("realname");
		if(page.getKeytype().equals("add_man")||page.getKeytype().equals("review_man")) {
			page.setKeyword(name);
		}
		int totals = sceneJobMapper.selectSceneJobCountByPage(page);
		page.setTotalRecord(totals);
		List<TBSceneJob> listjob = sceneJobMapper.selectSceneJobByPage(page);
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
			json.put("misid", tbMssage.getMisTitle());
			json.put("miscontent", tbMssage.getMisContent());
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
	public JSONObject updateSceneMarketJobState(TBSceneJob tbSceneJob) {
		// TODO Auto-generated method stub
		log.debug("当前提交的状态是："+tbSceneJob.getState());
		int result = sceneJobMapper.updateByPrimaryKeySelective(tbSceneJob);
		JSONObject json = new JSONObject();
		if(result>0) {
			json.put("code", 0);
		}else {
			json.put("code", 1);
		}
		return json;
	}

	@Override
	public void sendSceneMarketJobDataForMessage(Integer jobid, String worker) {
		// TODO Auto-generated method stub
		TBSceneJob tbSceneJob = sceneJobMapper.selectByPrimaryKey(jobid);
		TBSceneJob sceneJob = new TBSceneJob();
		if(tbSceneJob.getIsTimework().equals("是")) {
			log.debug("当前为定时任务");
			sceneJob.setId(tbSceneJob.getId());
			sceneJob.setState(101);
			sceneJob.setLastOpera(worker);
			sceneJobMapper.updateByPrimaryKeySelective(sceneJob);//更新当前状态
			//执行定时任务的操作
			SendTimeWorkFileIn.addSceneTaskId(jobid);
		}else {
			SendSceneLabel.waitsend.add(jobid);
			sceneJob.setId(tbSceneJob.getId());
			sceneJob.setState(5);
			sceneJob.setLastOpera(worker);
			sceneJobMapper.updateByPrimaryKeySelective(sceneJob);
			SendSceneLabel.setJob_id(jobid);
		}
	}

	@Override
	public JSONObject stopSendSceneMarketJobDataForMessage(HttpServletRequest request) {
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
				sceneJobMapper.updateByPrimaryKeySelective(sceneJob);
			}
		}
		return json;
	}

	@Override
	public void startSendSceneMarketJobDataForMessage(HttpServletRequest request) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("realname");
		log.info(name+"执行\"标签数据下发的\"续发任务！");
		SendFileIn.isStop = false;
		TBSceneJob sceneJob = new TBSceneJob();
		JSONObject json = new JSONObject();
		for(int id:SendSceneLabel.waitsend) {
			if(id==SendSceneLabel.getJob_id()) {
				sceneJob.setId(id);
				sceneJob.setState(3);
				sceneJobMapper.updateByPrimaryKeySelective(sceneJob);
			}else {
				sceneJob.setId(id);
				sceneJob.setState(5);
				sceneJobMapper.updateByPrimaryKeySelective(sceneJob);
			}
		}
	}

	@Override
	public JSONObject endSendSceneMarketJobDataForMessage(HttpServletRequest request) {
		// TODO Auto-generated method stub
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
		return json;
	}

	@Override
	public JSONObject deleteSceneMarketJob(Integer jobid) {
		// TODO Auto-generated method stub
		int result = sceneJobMapper.deleteByPrimaryKey(jobid);
		JSONObject json = new JSONObject();
		if(result>0) {
			json.put("code", 0);
		}else {
			json.put("code", 1);
		}
		return json;
	}

	@Override
	public JSONObject outputSceneMarketJobData(TBSceneJob tbSceneJob) {
		// TODO Auto-generated method stub
		String[] labels = tbSceneJob.getSceneBq().split(",");
		List<String> resultlist = new ArrayList<>();
		for(int i = 0 ; i< labels.length; i++) {
			List<TBSceneData> scenedatalist = sceneDataMapper.selectDataByLabel(labels[i]);
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
	public TBSceneJob selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return sceneJobMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<TBSceneData> selectDataByLabel(String labelId) {
		// TODO Auto-generated method stub
		return sceneDataMapper.selectDataByLabel(labelId);
	}

	@Override
	public int updateByPrimaryKeySelective(TBSceneJob tbSceneJob) {
		// TODO Auto-generated method stub
		return sceneJobMapper.updateByPrimaryKeySelective(tbSceneJob);
	}

	@Override
	public int insertIntoSceneDataByList(List<TBSceneData> list) {
		// TODO Auto-generated method stub
		return sceneDataMapper.insertIntoSceneDataByList(list);
	}

	@Override
	public int updateIntoSceneDataByList(List<TBSceneData> list) {
		// TODO Auto-generated method stub
		return sceneDataMapper.updateIntoSceneDataByList(list);
	}

	@Override
	public int deleteSceneDataByLabel(String labelId) {
		// TODO Auto-generated method stub
		return sceneDataMapper.deleteSceneDataByLabel(labelId);
	}

}
