package com.mtpt.aliservice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import com.mtpt.alibean.TBSceneData;
import com.mtpt.alibean.TBSceneJob;
import com.mtpt.alibean.page.SceneJobPage;
import com.mtpt.bean.page.SceneDataPage;

public interface ISceneMarketDataService {

	/**
	 * 添加标签和上传标签数据
	 */
	//查询当前所有的标签的数据
	JSONObject selectAllLabelData();

	//上传和提交场景营销中对应的标签数据
	JSONObject submitSceneMarketDataAndUploadFile(MultipartFile scenefile,String bq,String realname,String opera);

	//添加新的标签
	JSONObject insertNewLabelData(String labelvalue);

	//分页查询场景营销的数据
	JSONObject selectSceneMarketDataByPage(SceneDataPage page);

	/**
	 * 营销任务的创建
	 */
	//添加营销任务的参数信息
	JSONObject insertSceneMarketJobData(TBSceneJob tbSceneJob);

	//分页查询指定用户的所有营销任务数据
	JSONObject selectSceneMarketJobDataByPage(SceneJobPage page,HttpServletRequest request);

	//更新指定营销任务的状态
	JSONObject updateSceneMarketJobState(TBSceneJob tbSceneJob);

	//发送营销任务
	void sendSceneMarketJobDataForMessage(Integer jobid,String worker);

	//暂停发送营销任务
	JSONObject stopSendSceneMarketJobDataForMessage(HttpServletRequest request);

	//继续发送营销任务
	void startSendSceneMarketJobDataForMessage(HttpServletRequest request);

	//停止发送营销任务
	JSONObject endSendSceneMarketJobDataForMessage(HttpServletRequest request);

	//删除指定的营销任务
	JSONObject deleteSceneMarketJob(Integer jobid);

	//导出营销任务参数下的所有数据
	JSONObject outputSceneMarketJobData(TBSceneJob tbSceneJob);

	TBSceneJob selectByPrimaryKey(Integer id);

	List<TBSceneData> selectDataByLabel(String labelId);

	int updateByPrimaryKeySelective(TBSceneJob tbSceneJob);

	int insertIntoSceneDataByList(List<TBSceneData> list);

	int updateIntoSceneDataByList(List<TBSceneData> list);

	int deleteSceneDataByLabel(String labelId);
}
