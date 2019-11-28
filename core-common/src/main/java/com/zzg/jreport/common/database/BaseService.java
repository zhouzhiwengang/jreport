package com.zzg.jreport.common.database;

import java.util.List;

/**
 * 数据库 公共基础服务
 * @author zzg
 *
 * @param <T>
 */
public interface BaseService<T> {

	 T selectBySid(String sid);
	 
	 boolean insert(T t);
	 
	 boolean update(T t);
	 
	 List<T> select(T t);
	 
	 boolean delete(String sid);
	 
}
