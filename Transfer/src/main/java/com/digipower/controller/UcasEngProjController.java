package com.digipower.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import com.digipower.domain.UcasEngProj;
import com.digipower.service.UcasEngProjService;
import com.zzg.jreport.common.controller.AbstractController;
import com.zzg.jreport.response.JreportResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


@Controller
@RequestMapping("/api/eng")
@Api(value = "建设项目Controlle", tags = "建设项目操作服务")
public class UcasEngProjController extends AbstractController{
	@Autowired
	private UcasEngProjService service;
	
	@ApiOperation(httpMethod = "POST", value = "建设项目对象保存")
	@RequestMapping(value = "/insert", method = { RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public JreportResponse insert(
			@RequestBody @ApiParam(name = "建设项目对象", value = "json格式对象", required = true) UcasEngProj entity) {
		boolean target = service.insert(entity);
		return JreportResponse.ok(target);
		
	}
	
	@ApiOperation(httpMethod = "POST", value = "建设项目对象更新")
	@RequestMapping(value = "/update", method = { RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public JreportResponse update(
			@RequestBody @ApiParam(name = "UcasEngProj", value = "json格式对象", required = true) UcasEngProj entity) {
		boolean target = service.update(entity);
		return JreportResponse.ok(target);
	}
	
	@RequestMapping(value="/find", method={RequestMethod.POST}, produces = "application/json;charset=UTF-8")
	@ResponseBody
	@ApiOperation(httpMethod = "POST", value = "建设项目对象")
	public JreportResponse find(@RequestBody @ApiParam(name = "建设项目对象", value = "json格式对象", required = true) UcasEngProj entity) {
		List<UcasEngProj> list = service.select(entity);
		return JreportResponse.ok(list);
	}
	
	@RequestMapping(value="/delete", method={RequestMethod.POST}, produces = "application/json;charset=UTF-8")
	@ResponseBody
	@ApiOperation(httpMethod = "POST", value = "建设项目对象")
	public JreportResponse delete(@RequestBody @ApiParam(name = "建设项目对象", value = "json格式对象", required = true) JSONObject entity) {
		Map<String, Object> param = JSONObject.toJavaObject(entity, Map.class);
		boolean target = service.delete(String.valueOf(param.get("sid")));
		return JreportResponse.ok(target);
	}
}
