package com.mtpt.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtpt.bean.FTPDetails;
import com.mtpt.bean.TBUsers;
import com.mtpt.bean.page.AlipayPage;
import com.mtpt.dao.FTPDetailsMapper;
import com.mtpt.dao.TBUsersMapper;
import com.mtpt.extend.HttpRequest;
import com.mtpt.extend.OutputFile;
import com.mtpt.service.IAlipayBindUserManageService;
@Service("alipayBindUserManageService")
public class AlipayBindUserManageService implements IAlipayBindUserManageService{

	@Autowired
	private TBUsersMapper usersMapper;
	@Autowired
	private FTPDetailsMapper ftpdetailMapper;
	
	private Logger log = Logger.getLogger(AlipayBindUserManageService.class);
	
	@Override
	public JSONObject selectAlipayBindUserByPage(AlipayPage page,HttpServletRequest request) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("realname");
		log.info(name+"访问支付宝绑定用户列表！");
		 int totals = usersMapper.selectByCount(page);
		 page.setTotalRecord(totals);
		 List<TBUsers> userslist = usersMapper.selectByAlipayPage(page);
		 List<JSONObject> jsonlist = new ArrayList<JSONObject>();
		 JSONObject jsonmap = new JSONObject();
		 int count = 1;
		 for(TBUsers tbUsers : userslist) {
			 JSONObject map = new JSONObject();
			 map.put("id", count);
			 map.put("uid", tbUsers.getId());
			 map.put("dn", tbUsers.getMobile());
			 map.put("openId", tbUsers.getOpenid());
			 jsonlist.add(map);
			 count++;
		 }
		 jsonmap.put("code", 0);
		 jsonmap.put("msg", "");
		 jsonmap.put("count", totals);
		 jsonmap.put("data", jsonlist);
		return jsonmap;
	}

	@Override
	public JSONObject selectAlipayBindUserSendFlowDetailByPage(AlipayPage page,HttpServletRequest request) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("realname");
		log.info(name+"访问赠送流量结果的列表。");
		int totals = ftpdetailMapper.selectByCount(page);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		page.setTotalRecord(totals);
		List<FTPDetails> ftplist = ftpdetailMapper.selectByAlipayPage(page);
		JSONObject jsonmap = new JSONObject();
		List<JSONObject> jsonlist = new ArrayList<JSONObject>();
		for(FTPDetails ftpDetails : ftplist) {
			JSONObject map = new JSONObject();
			map.put("id", ftpDetails.getId());
			map.put("dn", ftpDetails.getMobile());
			map.put("amount", ftpDetails.getAmount());
			if(ftpDetails.getDatastate()==0) {
				map.put("state", "赠送成功");
			}else {
				map.put("state", "赠送失败");
			}
			if (ftpDetails.getModifytime()!=null) {
				String modifystr = sdf.format(ftpDetails.getModifytime());
				map.put("modifytime", modifystr);
			}else {
				map.put("modifytime", "");
			}
			jsonlist.add(map);
		}
		jsonmap.put("code", 0);
		jsonmap.put("msg", "");
		jsonmap.put("data", jsonlist);
		jsonmap.put("count", totals);
		return jsonmap;
	}

	@Override
	public JSONObject selectAlipayBindUserUnGiftFlowByPage(AlipayPage page,HttpServletRequest request) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("realname");
		log.info(name+"访问当前绑定用户漏赠用户的列表");
		int totals = ftpdetailMapper.selectByCount(page);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		page.setTotalRecord(totals);
		List<TBUsers> ftplist = usersMapper.selectUnGiftFlowUser(page);
		JSONObject jsonmap = new JSONObject();
		List<JSONObject> jsonlist = new ArrayList<JSONObject>();
		int count = 0;
		for(TBUsers tbUsers : ftplist) {
			JSONObject map = new JSONObject();
			 map.put("id", count);
			 map.put("uid", tbUsers.getId());
			 map.put("dn", tbUsers.getMobile());
			 map.put("openId", tbUsers.getOpenid());
			jsonlist.add(map);
			count++;
		}
		jsonmap.put("code", 0);
		jsonmap.put("msg", "");
		jsonmap.put("data", jsonlist);
		jsonmap.put("count", totals);
		return jsonmap;
	}

	@Override
	public JSONObject submitAlipayBindUserGiftFlow(String phonenum, String flow,HttpServletRequest request) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("realname");
		log.info(name+"执行赠送流量操作！为号码为："+phonenum+"赠送"+flow+"MB");
		String path = "http://mobile99.uninforun.com/unicom-hb/api/Unicom/PresendFlow";
		JSONObject paramjson = new JSONObject();
		List<JSONObject> listjson  = new ArrayList<JSONObject>();
		paramjson.put("PhoneNum", phonenum);
		paramjson.put("Amount", flow);
		paramjson.put("FlowType", "ZFGZ");
		listjson.add(paramjson);
		log.debug(listjson.toString());
		String resultstr = HttpRequest.sendPostJson(path, listjson.toString());
		log.debug("赠送流量请求的数据："+resultstr);
		JSONObject json = new JSONObject();
		if(resultstr.equals("")) {
			json.put("code", 0);
		}else {
			json.put("code", 1);
		}
		return json;
	}

	@Override
	public String outputAlipayBindUserInfo() {
		// TODO Auto-generated method stub
		List<TBUsers> userlist = usersMapper.selectAllAlipayUser();
		String path = OutputFile.outputAilPay(userlist);
		return path;
	}

}
