package com.zzg.file.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import com.zzg.file.domain.AuthUser;
import com.zzg.file.service.AuthUserService;
import com.zzg.jreport.common.controller.AbstractController;
import com.zzg.jreport.common.page.PageData;
import com.zzg.jreport.common.page.PageParam;
import com.zzg.jreport.response.JreportResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Controller
@RequestMapping("/api/auth/user")
@Api(value = "用户Controlle", tags = "用户操作服务")
public class AuthUserController extends AbstractController {

	@Autowired
	private AuthUserService service;
	
	@ApiOperation(httpMethod = "POST", value = "用户对象保存")
	@RequestMapping(value = "/insert", method = { RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public JreportResponse insert(
			@RequestBody @ApiParam(name = "用户对象", value = "json格式对象", required = true) AuthUser entity) {
		Long engSid = service.insert(entity);
		return JreportResponse.ok(engSid);
		
	}
	
	@ApiOperation(httpMethod = "POST", value = "用户对象更新")
	@RequestMapping(value = "/update", method = { RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public JreportResponse update(
			@RequestBody @ApiParam(name = "用户对象", value = "json格式对象", required = true) AuthUser entity) {
		service.updateByPrimaryKeySelective(entity);
		return JreportResponse.ok();
	}
	
	@RequestMapping(value="/find", method={RequestMethod.POST}, produces = "application/json;charset=UTF-8")
	@ResponseBody
	@ApiOperation(httpMethod = "POST", value = "用户查询")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "主键", required = false, dataType = "Integer", paramType = "query"),
		@ApiImplicitParam(name = "username", value = "用户名称", required = false, dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "email", value = "用户邮箱", required = false, dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "isStaff", value = "是否在职：1 在职、2:离职", required = false, dataType = "Integer", paramType = "query"),
		@ApiImplicitParam(name = "isActive", value = "激活状态：1 已激活、2:未激活", required = false, dataType = "Integer", paramType = "query")
	})
	public JreportResponse find(@RequestBody @ApiParam(name = "用户对象", value = "json格式对象", required = true) JSONObject entity) {
		Map<String, Object> param = JSONObject.toJavaObject(entity, Map.class);
		List<AuthUser> list = service.selectAll(param);
		return JreportResponse.ok(list);
	}
	
	
	@RequestMapping(value="/findByPage", method={RequestMethod.POST}, produces = "application/json;charset=UTF-8")
	@ResponseBody
	@ApiOperation(httpMethod = "POST", value = "用户分页查询")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "主键", required = false, dataType = "Integer", paramType = "query"),
		@ApiImplicitParam(name = "username", value = "用户名称", required = false, dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "email", value = "用户邮箱", required = false, dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "isStaff", value = "是否在职：1 在职、2:离职", required = false, dataType = "Integer", paramType = "query"),
		@ApiImplicitParam(name = "isActive", value = "激活状态：1 已激活、2:未激活", required = false, dataType = "Integer", paramType = "query")
	})
	public JreportResponse findByPage(@RequestBody @ApiParam(name = "用户对象", value = "json格式对象", required = true) JSONObject entity) {

		Map<String, Object> param = JSONObject.toJavaObject(entity, Map.class);
		PageParam rb = super.initPageBounds(param);
		PageData<AuthUser> pageList = service.selectAllPage(param, rb);
		return JreportResponse.ok(pageList);
	}

	
	
	
}
