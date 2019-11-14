package com.zzg.jreport.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.zzg.jreport.common.controller.AbstractController;
import com.zzg.jreport.common.page.PageData;
import com.zzg.jreport.common.page.PageParam;
import com.zzg.jreport.domain.UcasUnitProjRelation;
import com.zzg.jreport.response.JreportResponse;
import com.zzg.jreport.service.UcasUnitProjRelationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Controller
@RequestMapping("/api/test")
@Api(value = "功能测试Controlle", tags = "功能测试操作服务")
public class TestController extends AbstractController {
	@Autowired
	private UcasUnitProjRelationService service;
	
	@ApiOperation(httpMethod = "POST", value = "关系对象保存")
	@RequestMapping(value = "/insert", method = { RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public JreportResponse insert(
			@RequestBody @ApiParam(name = "关系对象", value = "json格式对象", required = true) UcasUnitProjRelation entity) {
		Long engSid = service.insert(entity);
		return JreportResponse.ok(engSid);
		
	}
	
	@ApiOperation(httpMethod = "POST", value = "关系对象更新")
	@RequestMapping(value = "/update", method = { RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public JreportResponse update(
			@RequestBody @ApiParam(name = "关系对象对象", value = "json格式对象", required = true) UcasUnitProjRelation entity) {
		service.updateByPrimaryKeySelective(entity);
		return JreportResponse.ok();
	}
	
	@RequestMapping(value="/find", method={RequestMethod.POST}, produces = "application/json;charset=UTF-8")
	@ResponseBody
	@ApiOperation(httpMethod = "POST", value = "关系查询")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "sid", value = "主键", required = false, dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "unitProjSid", value = "单项工程sid", required = false, dataType = "String", paramType = "query")
	})
	public JreportResponse find(@RequestBody @ApiParam(name = "关系对象", value = "json格式对象", required = true) JSONObject entity) {
		Map<String, Object> param = JSONObject.toJavaObject(entity, Map.class);
		List<UcasUnitProjRelation> list = service.selectAll(param);
		return JreportResponse.ok(list);
	}
	
	
	@RequestMapping(value="/findByPage", method={RequestMethod.POST}, produces = "application/json;charset=UTF-8")
	@ResponseBody
	@ApiOperation(httpMethod = "POST", value = "关系分页查询")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "sid", value = "主键", required = false, dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "unitProjSid", value = "单项工程sid", required = false, dataType = "String", paramType = "query")
	})
	public JreportResponse findByPage(@RequestBody @ApiParam(name = "关系对象", value = "json格式对象", required = true) JSONObject entity) {

		Map<String, Object> param = JSONObject.toJavaObject(entity, Map.class);
		PageParam rb = super.initPageBounds(param);
		PageData<UcasUnitProjRelation> pageList = service.selectAllPage(param, rb);
		return JreportResponse.ok(pageList);
	}

}
