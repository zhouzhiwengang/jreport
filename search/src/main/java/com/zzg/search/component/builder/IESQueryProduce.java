package com.zzg.search.component.builder;

import org.elasticsearch.index.query.BoolQueryBuilder;

/**
 * es 查询 生成类
 * @author zzg
 *
 */
public interface IESQueryProduce {
	
	

	/**
	 * bool 查询
	 * @return
	 */
	BoolQueryBuilder getBoolQueryBuilder();
	
	/**
	 * es 生成核心方法
	 * @param esQueryBuilders
	 * @return
	 */
	IESQueryProduce getProduct(IESQueryBuilders esQueryBuilders);
	
}
