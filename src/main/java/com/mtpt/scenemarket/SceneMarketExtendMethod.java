package com.mtpt.scenemarket;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.mtpt.alibean.TBSceneData;
import com.mtpt.aliservice.ITBSceneDataService;
import com.mtpt.config.BaseConfig;
import com.mtpt.config.SpringContextUtil;


public class SceneMarketExtendMethod {
	private static Logger log = Logger.getLogger(SceneMarketExtendMethod.class);
	private static ITBSceneDataService sceneDataService = (ITBSceneDataService) SpringContextUtil.getBean("tbsceneService");
	
	public static boolean uploadFileInput(String filepath,String bq,String opera) {
		int result = 0;
		File file = new File(filepath);
		List<TBSceneData> list = new ArrayList<>();
		List<String> phonelist = new ArrayList<>();
		String[] temp = null;
		try {
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
			InputStreamReader in = new InputStreamReader(bis);
			BufferedReader reader = new BufferedReader(in);
			String line = "";
			while((line = reader.readLine())!=null) {
				boolean ismatch = Pattern.matches(BaseConfig.REGEX_MOBILE_EXACT, line);
				if(!ismatch) {
					log.debug(line+"号码格式不正确！");
					continue;
				}else {
					phonelist.add(line);
				}
				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] bqarray = bq.split(",");
		for(int i = 0 ;i<bqarray.length;i++) {
			for(String phone : phonelist) {
				TBSceneData tbSceneData = new TBSceneData();
				tbSceneData.setLabelId(bqarray[i]);
				tbSceneData.setSceneDn(phone);
				list.add(tbSceneData);
			}
			if (opera.equals("add")) {
				result = sceneDataService.insertIntoSceneDataByList(list);
			}else if(opera.equals("update")) {
				List<TBSceneData> scenelist = sceneDataService.selectDataByLabel(bqarray[i]);
				if (scenelist.isEmpty()) {
					result = sceneDataService.updateIntoSceneDataByList(list);
				}else {
					result = sceneDataService.deleteSceneDataByLabel(bqarray[i]);
					if (result>0) {
						result = sceneDataService.updateIntoSceneDataByList(list);
					}else {
						log.debug("更新失败");
						result = 0;
					}
				}
			}
		}
		
		if (result>0) {
			return true;
		}else {
			return false;
		}
	}
}
