package com.mtpt.controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mtpt.bean.TBBlackList;
import com.mtpt.bean.page.BlackPage;
import com.mtpt.service.ITBBlackListService;
import com.sun.org.apache.bcel.internal.generic.INEG;

@Controller
@RequestMapping("blackmana")
public class BlackListManageController {
	private Logger log = Logger.getLogger(BlackListManageController.class);
	@Resource 
	ITBBlackListService blackservice;
	SimpleDateFormat sdf = null;
	private HttpSession session = null;
	@RequestMapping(value="/selectall",method = {RequestMethod.POST,RequestMethod.GET})
	 private void selectAllByLimit(BlackPage page,HttpServletResponse response,HttpServletRequest request) {
		session = request.getSession();
		String name = (String) session.getAttribute("realname");
		log.info(name+"查看黑名单列表！");
		int totals = blackservice.selectByCount(page);
		List<TBBlackList> blackList = blackservice.selectByBlackPage(page);
		JSONObject jsonmap = new JSONObject();
		List<JSONObject> jsonlist = new ArrayList<JSONObject>();
		response.setContentType("text/html;charset=utf-8");
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for(int i =0;i<blackList.size();i++) {
			JSONObject map = new JSONObject();
			map.put("id", blackList.get(i).getId());
			map.put("dn", blackList.get(i).getDn());
			String addtime = sdf.format(blackList.get(i).getAdTime());
			map.put("addtime", addtime);
			jsonlist.add(map);
		}
		jsonmap.put("code", 0);
		jsonmap.put("count", totals);
		jsonmap.put("msg", "");
		jsonmap.put("data", jsonlist);
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
	
	@RequestMapping(value="/insert",method = {RequestMethod.POST,RequestMethod.GET})
	private void insertBlackPhone(TBBlackList tbBlackList,HttpServletResponse response,HttpServletRequest request) {
		session = request.getSession();
		String name = (String) session.getAttribute("realname");
		log.info(name+"执行插入黑名单的操作！插入的号码是："+tbBlackList.getDn());
		int result = blackservice.insertSelective(tbBlackList);
		JSONObject json = new JSONObject();
		if(result>0) {
			json.put("code", 1);
		}else {
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
	
	@RequestMapping(value="/delete",method = {RequestMethod.POST,RequestMethod.GET})
	private void deleteBlackPhone(int id,HttpServletResponse response,HttpServletRequest request) {
		session = request.getSession();
		String name = (String) session.getAttribute("realname");
		log.info(name+"执行删除黑名单操作，删除黑名单的ID是："+id);
		int result = blackservice.deleteByPrimaryKey(id);
		JSONObject json = new JSONObject();
		if (result>0) {
			json.put("code", 0);
		}else {
			json.put("code", 1);
		}
		json.put("msg", "");
		json.put("data", "");
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
	@RequestMapping(value="uploadfile",method = {RequestMethod.POST,RequestMethod.GET})
	private void uploadBlackFile(@RequestParam("file") MultipartFile file,
			HttpServletResponse response,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("realname");
		log.info(name+"执行黑名单导入操作。导入的黑名单的文件是："+file.getName());
		int result = 0;
		try {
			InputStream in =  file.getInputStream();
			InputStreamReader isr = new InputStreamReader(in);
			BufferedReader reader = new BufferedReader(isr);
			List<String> phonelist = new ArrayList<String>();
			String line = "";
			while((line=reader.readLine())!=null) {
				phonelist.add(line);
			}
			result = blackservice.insertByList(phonelist);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject json = new JSONObject();
		if (result > 0) {
			json.put("code", 0);
		}else {
			json.put("code", 1);
		}
		json.put("msg", "");
		json.put("data", "");
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
