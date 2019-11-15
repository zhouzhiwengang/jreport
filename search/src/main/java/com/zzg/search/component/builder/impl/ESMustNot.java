package com.zzg.search.component.builder.impl;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import com.zzg.search.component.builder.IESQueryBuilders;

public class ESMustNot implements IESQueryBuilders {
	private BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
	
	@Override
	public IESQueryBuilders termQuery(String name, String... values) throws Exception {
		// TODO Auto-generated method stub
		boolQueryBuilder.mustNot(QueryBuilders.termQuery(name, values));
		return this;
	}

	@Override
	public IESQueryBuilders wildcardQuery(String name, String... values) throws Exception {
		// TODO Auto-generated method stub
		for (String value : values){
            boolQueryBuilder.mustNot(QueryBuilders.wildcardQuery(name, "*" + value + "*"));
        }
        return this;

	}

	@Override
	public IESQueryBuilders rangeQuery(String name, String from, String to) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BoolQueryBuilder getBoolQueryBuilder() {
		// TODO Auto-generated method stub
		return boolQueryBuilder;
	}

}
