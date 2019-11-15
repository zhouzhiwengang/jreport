package com.zzg.search.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zzg.jreport.response.JreportResponse;
import com.zzg.search.component.ESService;
import com.zzg.search.component.builder.IESQueryBuilders;
import com.zzg.search.component.builder.factory.ESQueryBuilderFactory;
import com.zzg.search.component.builder.impl.ESQueryProduce;
import com.zzg.search.component.builder.model.ESQueryMode;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/api/es")
@Api(value = "搜索引擎Controlle", tags = "搜索引擎操作服务")
public class ESController {
	
	@ApiOperation(httpMethod = "POST", value = "ES检索")
	@RequestMapping(value = "/search", method = { RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public JreportResponse search() {
		ESService util = new ESService("my-application","192.168.1.74", 9300);
		
		// 验证索引文件是否存在
		boolean target = util.indexExist("username");
		if(target){
			System.out.println("指定索引是否存在:" + target);
			util.deleteIndex("username");
			System.out.println("指定索引删除成功");
		}
		return JreportResponse.ok(null);
	}
	
	@ApiOperation(httpMethod = "POST", value = "ES检索")
	@RequestMapping(value = "/find", method = { RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public JreportResponse find() {
		ESService util = new ESService("my-application","192.168.1.74", 9300);
		
		ESQueryProduce produce = new ESQueryProduce();
		// 构建查询条件
		IESQueryBuilders must = null;
		try {
			must = ESQueryBuilderFactory.creatESQueryBuilder(ESQueryMode.MUST);
			must.termQuery("author", "somebody");
			must.rangeQuery("postdate", "2015-01-01", "2019-01-01");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		produce.getProduct(must);
		
	    List<Map<String,Object>> list = util.search("website", "blog", produce);
		
		return JreportResponse.ok(list);
	}

}
