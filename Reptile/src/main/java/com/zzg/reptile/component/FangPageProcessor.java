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
 * 房天下#二手房
 * 
 * @author zzg
 *
 */
@Component
public class FangPageProcessor implements PageProcessor {
	// 解析数据
	private JSONArray array = new JSONArray();

	public JSONArray getArray() {
		return array;
	}

	public void setArray(JSONArray array) {
		this.array = array;
	}

	// 站点信息
	String domain = "https://sz.esf.fang.com/";
	Integer sleepTime = 1000;
	Integer retryTime = 30;
	String charset = "gb2312";
	Integer timeOut = 30000;
	String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.97 Safari/537.36";

	
	// 初始化站点
		private Site site = Site.me().setDomain(domain).setSleepTime(sleepTime)
	            .setRetryTimes(retryTime).setCharset(charset).setTimeOut(timeOut).setUserAgent(userAgent);
	@Override
	public void process(Page page) {
		// TODO Auto-generated method stub
		Html html = page.getHtml();
		if(html != null){
			List<Selectable> selectables = html.xpath("//dl[@class='clearfix']").nodes();
			for(int i = 0; i < selectables.size(); i ++){
				// 解析数据接收
				JSONObject jsonObject = new JSONObject();
				
				Selectable selectable = selectables.get(i);
				// 标题
				String title = selectable.xpath("//dd/h4[@class='clearfix']/a/span/text()").get();
				// url 地址
				String url = selectable.xpath("//dd/h4[@class='clearfix']").links().get();
				// 优势
				String advantage = selectable.xpath("//dd/p[@class='tel_shop']/text()").get();
				// 名称
				String name = selectable.xpath("//dd/p[@class='add_shop']/a/text()").get();
				// 地址
				String address = selectable.xpath("//dd/p[@class='add_shop']/span/text()").get();
				// 特定
				String characteristic = selectable.xpath("//dd/p[@class='clearfix label']/span/text()").nodes().toString();
				// 价格
				String price = selectable.xpath("//dd[@class='price_right']/span[@class='red']/b/text()").get() + "万";
				// 单价
				String unitPrice = "";
				String[] arrays = selectable.xpath("//dd[@class='price_right']/span/text()").nodes().get(1).toString().split("/");
				if(arrays != null && arrays.length > 0){
					unitPrice = arrays[0];
				}
				jsonObject.put("title", title);
				jsonObject.put("url", url);
				jsonObject.put("advantage", advantage);
				jsonObject.put("name", name);
				jsonObject.put("address", address);
				jsonObject.put("characteristic", characteristic);
				jsonObject.put("price", price);
				jsonObject.put("unitPrice", unitPrice);
				
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
