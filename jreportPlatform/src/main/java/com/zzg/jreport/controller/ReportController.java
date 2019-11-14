package com.zzg.jreport.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zzg.jreport.common.component.JReportCommondUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/api/report")
@Api(value = "功能测试Controlle", tags = "功能测试操作服务")
public class ReportController {
	
	@ApiOperation(httpMethod = "GET", value = "测试操作")
	@RequestMapping(value = "/preview", method = { RequestMethod.GET })
	public void preview(HttpServletRequest req, HttpServletResponse resp){
		try{
			//組裝list數據源
			List<Map> source = new ArrayList<Map>();
			Map parameters = new HashMap();
			parameters.put("title", "THIS IS TITLE");
			parameters.put("date", new SimpleDateFormat("yyyy-mm-dd").format(new Date()));
			parameters.put("name", "小明");
			parameters.put("age", "18");
			parameters.put("dept", "开发部");
			parameters.put("gender", "男");
			
			byte[] bytes = JReportCommondUtil.exportPdf("report9.jasper", source);
			resp.setContentType("application/pdf");
			resp.setContentLength(bytes.length);
			resp.getOutputStream().write(bytes, 0, bytes.length);
			
		}catch(IOException e){
			System.out.println("error:" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	@ApiOperation(httpMethod = "GET", value = "测试操作")
	@RequestMapping(value = "/one", method = { RequestMethod.GET })
	public void one(HttpServletRequest req, HttpServletResponse resp){
		try{
			List<Map> source = new ArrayList<Map>();
			byte[] bytes = JReportCommondUtil.exportPdf("report8.jasper", source);
			resp.setContentType("application/pdf");
			resp.setContentLength(bytes.length);
			resp.getOutputStream().write(bytes, 0, bytes.length);
			
		}catch(IOException e){
			System.out.println("error:" + e.getMessage());
			e.printStackTrace();
		}
	}

}
