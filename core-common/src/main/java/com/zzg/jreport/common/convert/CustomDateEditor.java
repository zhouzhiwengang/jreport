package com.zzg.jreport.common.convert;

import java.beans.PropertyEditorSupport;

import com.zzg.jreport.common.util.DateUtils;

/**
 * 自定义日期转换工具类
 * @author zzg
 *
 */
public class CustomDateEditor extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		setValue(DateUtils.formatDateStr(text));
	}
	
	

}
