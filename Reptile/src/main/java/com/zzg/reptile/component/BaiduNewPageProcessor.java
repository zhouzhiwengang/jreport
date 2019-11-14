package com.zzg.reptile.component;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

/**
 * 百度新闻#国内焦点 
 * @author zzg
 *
 */
@Component
public class BaiduNewPageProcessor implements PageProcessor {
	
	//解析数据
	private JSONArray array = new JSONArray();
	
	public JSONArray getArray() {
		return array;
	}

	public void setArray(JSONArray array) {
		this.array = array;
	}

	// 站点信息
	String domain ="news.baidu.com";
	Integer sleepTime = 1000;
	Integer retryTime = 30;
	String charset ="utf-8";
	Integer timeOut = 30000;
	String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.97 Safari/537.36";
	
	// 
	
	// 初始化站点
	private Site site = Site.me().setDomain(domain).setSleepTime(sleepTime)
            .setRetryTimes(retryTime).setCharset(charset).setTimeOut(timeOut).setUserAgent(userAgent);
	
	@Override
	public void process(Page page) {
		// TODO Auto-generated method stub
		Html html = page.getHtml();
		if(html != null){
			List<Selectable> selectables = html.xpath("//div[@class='hotnews']/ul/li/strong/").nodes();
			
			for(int i = 0; i < selectables.size(); i ++){
				Selectable selectable = selectables.get(i);
				String content = selectable.toString();
				Pattern pattern = Pattern.compile("^<i");
				Matcher matcher = pattern.matcher(content);
				if(matcher.find()){
					selectables.remove(i);
				}
			}
			
			for(int i =0; i < selectables.size(); i ++){
				// 解析数据接收
				JSONObject jsonObject = new JSONObject();
				
				Selectable selectable = selectables.get(i);
				String url = selectable.links().toString();
				System.out.println("url is:" + url);
				// 设置访问url
				jsonObject.put("url", url);
				String context = selectable.toString();
				Pattern pattern = Pattern.compile(".*(<b>).*");
				Matcher matcher = pattern.matcher(context);
				if(matcher.find()){
					String title = selectable.xpath("/a/b/text()").get();
					// 设置访问标题
					jsonObject.put("title", title);
				} else {
					String title = selectable.xpath("a/text()").get();
					// 设置访问标题
					jsonObject.put("title", title);
				}
				array.add(jsonObject);
			}

			
			
			
		}
	}

	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return site;
	}

}
