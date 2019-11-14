package com.zzg.reptile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.zzg.jreport.response.JreportResponse;
import com.zzg.reptile.component.BaiduNewPageProcessor;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import us.codecraft.webmagic.Spider;

@Controller
@RequestMapping("/api/webmagic")
@Api(value = "爬虫Controlle", tags = "爬虫操作服务")
public class WebmagicController {
	@Autowired
	private BaiduNewPageProcessor processor;

	@ApiOperation(httpMethod = "POST", value = "新闻信息爬取")
	@RequestMapping(value = "/news", method = { RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public JreportResponse news() {
		String url = "https://news.baidu.com/?cmd=1&class=civilnews&tn=rss&sub=0";
		Spider.create(processor).addUrl(url).run();
		
		JSONArray array = processor.getArray();
		return JreportResponse.ok(array);
	}

}
