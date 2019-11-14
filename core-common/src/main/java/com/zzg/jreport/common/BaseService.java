package com.zzg.jreport.common;

public interface BaseService<T> {
	/**
	 * 创建实体对象
	 * 
	 * @param entity
	 */
	Long insert(T entity);

	/**
	 * 更新实体对象中非空的字段
	 * 
	 * @param entity
	 */
	void updateByPrimaryKeySelective(T entity);
	
	/**
	 * 根据主键查找
	 * 
	 * @param sid
	 */
	T selectByPrimaryKey(String sid);
	
	/**
	 * 根据主键删除找
	 * 
	 * @param sid
	 */
	void deleteByPrimaryKey(String sid);
}
