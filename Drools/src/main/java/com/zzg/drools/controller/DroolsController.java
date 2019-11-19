package com.zzg.drools.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zzg.drools.service.impl.DroolsServiceImpl;
import com.zzg.jreport.response.JreportResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/api/drools")
@Api(value = "规则流程引擎Controlle", tags = "规则流程引擎操作服务")
public class DroolsController {
	// 日志管理
	private Logger logger = LoggerFactory.getLogger(DroolsController.class);
	
	@Autowired
	private DroolsServiceImpl service;
	
	@ApiOperation(httpMethod = "POST", value = "规则流程引擎")
	@RequestMapping(value = "/rule", method = { RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public JreportResponse rule() {
		String message = service.fireRule();
		return JreportResponse.ok(message);
		
	}
	
	

}
