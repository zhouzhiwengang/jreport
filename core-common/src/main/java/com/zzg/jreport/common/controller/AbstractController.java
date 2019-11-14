package com.zzg.jreport.common.controller;

import java.util.Map;

import com.zzg.jreport.common.constant.WebAppConstants;
import com.zzg.jreport.common.convert.SimpleTypeConverterUtil;
import com.zzg.jreport.common.page.PageParam;

public abstract class AbstractController {
	/**
	 * 参数分页参数转换校验
	 * @param param
	 * @return
	 */
	protected PageParam initPageBounds(Map<String, Object> param){
		int page = SimpleTypeConverterUtil.convertIfNecessary(param.get(WebAppConstants.PAGE) , int.class);
		int limit = SimpleTypeConverterUtil.convertIfNecessary(param.get(WebAppConstants.LIMIT), int.class);
		//页码和页数取消最大限制
		page = (page <= 0) ? 1 : page;
		limit = (limit <= 0) ? 10 : limit;
		PageParam pb = new PageParam(page, limit);
		return pb;
	}
}
