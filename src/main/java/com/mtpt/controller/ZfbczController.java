package com.mtpt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mtpt.bean.Zfbcz;
import com.mtpt.bean.page.PublicLocalPage;
import com.mtpt.bean.page.ZfbczPage;
import com.mtpt.extend.OtherMethod;
import com.mtpt.service.IZfbczManageService;

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

@Controller
@RequestMapping("/zfbcz")
public class ZfbczController {
	@Resource
	private IZfbczManageService zfbczService;
	@RequestMapping(value="/selectall",method= {RequestMethod.POST,RequestMethod.GET})
	private void selectAllDataByPage(ZfbczPage page,HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		JSONObject json = zfbczService.selectAllDataByPage(page);
		OtherMethod.PrintFlush(response, json);
	}
}
