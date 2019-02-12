package com.mtpt.alicontroller;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mtpt.alibean.TBLabel;
import com.mtpt.alibean.TBSceneData;
import com.mtpt.aliservice.ITBLabelService;
import com.mtpt.aliservice.ITBSceneDataService;
import com.mtpt.bean.page.SceneDataPage;
import com.mtpt.scenemarket.SceneMarketExtendMethod;

/**
 * 
 * @author lvgordon
 * 场景营销数据处理
 *
 */

@Controller
@RequestMapping("/scenemarket")
public class SceneMarketController {
	private Logger log = Logger.getLogger(SceneMarketController.class);
	@Resource
	ITBLabelService labelService;
	@Resource
	ITBSceneDataService sceneDataService;

	private SimpleDateFormat sdf = null;
//	private String filepath = "/Users/lvgordon/Downloads/FILEIN/";//测试
	private String filepath = "D://NEW_HSDTMarket_Platform/upload/scenedataup";//河北正式环境

	/**
	 * 
	 * @param response
	 * @param request
	 * 查找标签内容并通过json数据传至jsp页面
	 * 
	 */
	@RequestMapping(value="/selectlabel",method= {RequestMethod.POST,RequestMethod.GET})
	private void selectLabelDataAll(HttpServletResponse response,HttpServletRequest request) {
		response.setContentType("text/html;charset=utf-8");
		List<TBLabel> labellist = labelService.selectAllData();
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
	 * @param file
	 * @param bq
	 * @param realname
	 * @param request
	 * @param response
	 * 上传文件，将文件读取出来写入服务器中的文件，并在后台读取数据存入数据库
	 * 
	 */
	@RequestMapping(value="/sceneupload",method= {RequestMethod.GET,RequestMethod.POST})
	private void uploadFileSceneMarket(@RequestParam("file") MultipartFile file,
			String bq,
			String realname,
			String opera,
			HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		log.debug("当前标签的值是："+bq);
		String[] arrbq = bq.split(",");
		for(String bqid:arrbq) {
			TBLabel tbBq = labelService.selectByPrimaryKey(Integer.parseInt(bqid));
			log.info(realname+"导入标签数据，标签为："+tbBq.getBqName());
		}
		String filename = file.getOriginalFilename();
		String houzhui = filename.substring(filename.indexOf(".")+1, filename.length());
		log.debug("后缀名是："+filename);
		Date date = new Date();
		sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		String newfilename = sdf.format(date);
		String allpath = filepath+newfilename+houzhui;
		log.debug("当前场景营销的数据存储位置是："+allpath);
		try {
			OutputStream out = new FileOutputStream(allpath);
			InputStream in = file.getInputStream();
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
	 * @param bqValue
	 * @param request
	 * @param response
	 * 添加标签数据
	 * 
	 */
	@RequestMapping(value="/insertbq",method = {RequestMethod.GET,RequestMethod.POST})
	private void addBqData(String bqValue,HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		TBLabel tbLabel = new TBLabel();
		tbLabel.setBqName(bqValue);
		int result = labelService.insertSelective(tbLabel);
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
	 * @param page
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/selectscene",method= {RequestMethod.GET,RequestMethod.POST})
	private void selectSceneDataByPage(SceneDataPage page,HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		List<String> phonelist = new ArrayList<>();
		List<String> resultlist = new ArrayList<>();
		List<JSONObject> jsonlist = new ArrayList<>();
		JSONObject jsonmap = new JSONObject();
		int totalcount = 0;
		if (page.getBq()==null||page.getBq().equals("")) {
			totalcount = sceneDataService.selectDataCountByPage(page);
			System.out.println("当前数量是："+totalcount);
			page.setTotalRecord(totalcount);
			List<TBSceneData> list = sceneDataService.selectDataByPage(page);
			int count = 1;
			for(TBSceneData tbSceneData : list) {
				JSONObject json = new JSONObject();
				json.put("id", count);
				json.put("dn", tbSceneData.getSceneDn());
				TBLabel tbLabel = labelService.selectByPrimaryKey(Integer.parseInt(tbSceneData.getLabelId()));
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
					int totals = sceneDataService.selectDataCountByPage(page);
					page.setTotalRecord(totals);
					List<TBSceneData> list = sceneDataService.selectDataByPage(page);
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
					TBLabel tbLabel = labelService.selectByPrimaryKey(Integer.parseInt(id));
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
					int totals = sceneDataService.selectDataCountByPage(page);
					page.setTotalRecord(totals);
					List<TBSceneData> list = sceneDataService.selectDataByPage(page);
					listscene.addAll(list);
				}
				if (!listscene.isEmpty()) {
					count = 1;
					for(TBSceneData tbSceneData : listscene) {
						JSONObject json = new JSONObject();
						json.put("id", count);
						json.put("dn", tbSceneData.getSceneDn());
						TBLabel tbLabel = labelService.selectByPrimaryKey(Integer.parseInt(tbSceneData.getLabelId()));
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
}
