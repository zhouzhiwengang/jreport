package com.zzg.reptile.component;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

@Component
public class FangDetailsPageProcessor implements PageProcessor {

	private JSONObject object = new JSONObject();

	public JSONObject getObject() {
		return object;
	}

	public void setObject(JSONObject object) {
		this.object = object;
	}

	// 站点信息
	String domain = "https://sz.esf.fang.com/";
	Integer sleepTime = 1000;
	Integer retryTime = 30;
	String charset = "utf-8";
	Integer timeOut = 30000;
	String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.97 Safari/537.36";

	// 初始化站点
	private Site site = Site.me().setDomain(domain).setSleepTime(sleepTime).setRetryTimes(retryTime).setCharset(charset)
			.setTimeOut(timeOut).setUserAgent(userAgent);

	@Override
	public void process(Page page) {
		// TODO Auto-generated method stub
		Html html = page.getHtml();
		if(html != null){
			System.out.println(html);
			String title = html.xpath("//div[@class='wid1200 clearfix']/div[@class='tab-cont clearfix']/div[@class='title rel']/h1/text()").get();
			System.out.println(title);
		}
	}

	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return site;
	}

}
