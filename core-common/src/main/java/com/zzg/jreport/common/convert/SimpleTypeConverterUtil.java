package com.zzg.jreport.common.convert;

import java.util.Date;

import org.springframework.beans.SimpleTypeConverter;

/**
 * 简单类型转换器
 * @author zzg
 *
 */
public class SimpleTypeConverterUtil {
	private static final SimpleTypeConverter typeConverterDelegate = new SimpleTypeConverter();
	static{
		typeConverterDelegate.registerCustomEditor(Date.class, new CustomDateEditor());
	}
	
	/**
	 * @param <T>
	 * @param value  待转换值,一般字符串
	 * @param requiredType 转后类型类对象
	 * @return
	 */
	public static <T> T convertIfNecessary(Object value, Class<T> requiredType) {
		T rs = null;
		try {
			rs = typeConverterDelegate.convertIfNecessary(value, requiredType);
		} catch (Exception e) {
			if(requiredType == int.class || requiredType == Integer.class){
				rs = (T)Integer.valueOf(0);
			}
		}
		return rs;
	}
}
