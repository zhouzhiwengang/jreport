package com.zzg.batch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.zzg.batch.service.AuthUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/api/trans")
@Api(value = "事务处理Controlle", tags = "事务处理操作服务")
public class TransactionController {

	@Autowired
	private AuthUserService service;
	
	@ApiOperation(httpMethod = "POST", value = "事务功能测试")
	@RequestMapping(value = "/init", method = { RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public void init() {
		service.transaction();
	}

}
