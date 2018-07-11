package com.tydic.scda.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.tydic.scda.config.SusConfig;
import com.tydic.scda.model.SusUrlRecord;
import com.tydic.scda.service.SeqService;
import com.tydic.scda.service.SusRecordService;
import com.tydic.scda.utils.HexUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	private final static Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private SeqService seqService;

	@Autowired
	private SusRecordService susRecordService;

	@Autowired
	private SusConfig susConfig;

	@RequestMapping(value="/susErrPage.do")
	public ModelAndView test(HttpServletResponse response) throws IOException{
		return new ModelAndView("home");
	}

	@RequestMapping(value="/getSusShortUrl.do")
	public ModelAndView getShortUrl(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		response.addHeader("Access-Control-Allow-Origin","*");
		response.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		Map<String,Object> resultMap = new HashMap<String,Object>();
		String srcUrl = request.getParameter("srcUrl");
		if(srcUrl == null || srcUrl == ""){
			resultMap.put("flag", 0);
			resultMap.put("msg","srcUrl 参数不正确");
			resultMap.put("data",null);
			response.getWriter().write(gson.toJson(resultMap));
			return null;
		}
		System.out.println("srcUrl:"+srcUrl);

		Long susId = seqService.nextVal("SUS_ID");
		String shortUrl =  HexUtils.convertToSixtyHex(susId);

		if(!srcUrl.startsWith("http")){
			srcUrl = "http://"+srcUrl;
		}

		SusUrlRecord record = new SusUrlRecord();
		record.setId(susId);
		record.setSrcUrl(srcUrl);
		record.setShortUrl(shortUrl);
		record.setCreateTime(new Date());
		try {
			susRecordService.insert(record);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("flag", 0);
			resultMap.put("msg","数据库操作错误，保存失败");
			resultMap.put("data",null);
			response.getWriter().write(gson.toJson(resultMap));
			return null;

		}

		resultMap.put("flag", 1);
		resultMap.put("msg","success");
		resultMap.put("data",susConfig.getSusHostUrl()+shortUrl);
		response.getWriter().write(gson.toJson(resultMap));
		return null;
	}

}
