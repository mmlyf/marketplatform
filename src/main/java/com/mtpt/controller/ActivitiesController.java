package com.mtpt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mtpt.alibean.page.TBRecordPage;
import com.mtpt.bean.ActivityList;
import com.mtpt.bean.TBHfcs;
import com.mtpt.service.IActivityListService;
import com.mtpt.service.ITBBlackListService;
import com.mtpt.service.ITBHfcsService;

import javafx.css.PseudoClass;
import sun.util.logging.resources.logging_fr;

@Controller
@RequestMapping("/reqact")
public class ActivitiesController {
	
	@Resource
	IActivityListService activiService;
	@Resource
	ITBHfcsService hfcsService;
	
	private SimpleDateFormat sdf = null;
	
	/**
	 * 查找活动并分页并将信息返回值前端
	 * 
	 * @param page
	 * @param response
	 */
	@RequestMapping(value="/selectpage",method = {RequestMethod.POST,RequestMethod.GET})
	private void selectActByRecordPage(TBRecordPage page,HttpServletResponse response) {
		int totals = activiService.selectActiCount(page);
		page.setTotalRecord(totals);
		List<ActivityList> actlist = activiService.selectActiByPage(page);
		JSONObject jsonmap = new JSONObject();
		List<JSONObject> jsonlist = new ArrayList<>();
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for(ActivityList tbActivilist : actlist) {
			JSONObject map = new JSONObject();
			map.put("actiid", tbActivilist.getId());
			map.put("actname", tbActivilist.getActName());
			map.put("starttime", sdf.format(tbActivilist.getActStarttime()));
			map.put("endtime", sdf.format(tbActivilist.getActEndtime()));
			map.put("addtime", sdf.format(tbActivilist.getAddtime()));
			map.put("actpage", tbActivilist.getActPage());
			jsonlist.add(map);
		}
		jsonmap.put("code", 0);
		jsonmap.put("msg", "");
		jsonmap.put("count", totals);
		jsonmap.put("data", jsonlist);
		response.setContentType("text/html;charset=utf-8");
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
	@RequestMapping(value="/addact",method = {RequestMethod.GET,RequestMethod.POST})
	private void addActivity(ActivityList actlist,HttpServletResponse response,HttpServletRequest request) {
		int result = activiService.insertSelective(actlist);
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
}
