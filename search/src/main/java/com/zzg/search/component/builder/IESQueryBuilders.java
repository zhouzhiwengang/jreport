package com.zzg.search.component.builder;

import org.elasticsearch.index.query.BoolQueryBuilder;

/**
 * es 查询构建工具类
 * 
 * @author zzg
 *
 */
public interface IESQueryBuilders {
	/**
	 * term 查询
	 * @param name
	 * @param valve
	 * @return
	 * @throws Exception
	 */
	IESQueryBuilders termQuery(String name, String... values) throws Exception;

	/**
	 * wildcard 查询
	 * @param name
	 * @param values
	 * @return
	 * @throws Exception
	 */
	IESQueryBuilders wildcardQuery(String name, String... values) throws Exception;

	/**
	 * rang 查询
	 * @param name
	 * @param from
	 * @param to
	 * @return
	 * @throws Exception
	 */
	IESQueryBuilders rangeQuery(String name, String from, String to) throws Exception;

	/**
	 * bool 查询
	 * @return
	 */
	BoolQueryBuilder getBoolQueryBuilder();

}
