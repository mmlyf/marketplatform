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
import javax.print.attribute.standard.MediaSize.Other;
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
import com.mtpt.aliservice.ISceneMarketDataService;
import com.mtpt.aliservice.ITBLabelService;
import com.mtpt.bean.page.SceneDataPage;
import com.mtpt.extend.OtherMethod;
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
	private ISceneMarketDataService sceneMarketDataService;


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
		JSONObject jsonmap = sceneMarketDataService.selectAllLabelData();
		OtherMethod.PrintFlush(response, jsonmap);
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
		JSONObject json = sceneMarketDataService.submitSceneMarketDataAndUploadFile(file, bq, realname, opera);
		OtherMethod.PrintFlush(response, json);
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
		JSONObject json = sceneMarketDataService.insertNewLabelData(bqValue);
		OtherMethod.PrintFlush(response, json);
	}
	/**
	 * 分页查询所有标签下的数据
	 * @param page
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/selectscene",method= {RequestMethod.GET,RequestMethod.POST})
	private void selectSceneDataByPage(SceneDataPage page,HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		JSONObject jsonmap = sceneMarketDataService.selectSceneMarketDataByPage(page);
		OtherMethod.PrintFlush(response, jsonmap);
	}
}
