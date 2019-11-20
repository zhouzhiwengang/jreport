package com.zzg.reptile.controller;

import java.util.Iterator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zzg.jreport.response.JreportResponse;
import com.zzg.reptile.component.FangDetailsPageProcessor;
import com.zzg.reptile.component.FangPageProcessor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import us.codecraft.webmagic.Spider;

@Controller
@RequestMapping("/api/fang")
@Api(value = "房天下Controlle", tags = "房天下爬虫操作服务")
public class FangController {

	@Autowired
	private FangPageProcessor processor;
	@Autowired
	private FangDetailsPageProcessor childProcessor;

	@ApiOperation(httpMethod = "POST", value = "新闻信息爬取")
	@RequestMapping(value = "/house", method = { RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public JreportResponse news() {
		String url = "https://sz.esf.fang.com/";
		Spider.create(processor).addUrl(url).run();
		
		JSONArray array = processor.getArray();
		for(Iterator iterator = array.iterator();iterator.hasNext();){
			JSONObject object = (JSONObject) iterator.next();
			System.out.println("jsonobject is :" + object.toString());
			System.out.println("-------------------------------------------------------");
			String childUrl = object.getString("url");
			childUrl = "https://sz.esf.fang.com/chushou/3_223710651.htm?channel=2,2&psid=1_1_90";
			if(!StringUtils.isEmpty(childUrl)){
				Spider.create(childProcessor).addUrl(childUrl).run();
				JSONObject json = childProcessor.getObject();
				System.out.println("detail is :" + json.toString());
	
			}
		}

		
		
		return JreportResponse.ok(array);
	}
}
