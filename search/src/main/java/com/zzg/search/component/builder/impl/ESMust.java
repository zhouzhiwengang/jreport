package com.zzg.search.component.builder.impl;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import com.zzg.search.component.builder.IESQueryBuilders;


public class ESMust implements IESQueryBuilders {

	private BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();

	@Override
	public IESQueryBuilders termQuery(String name, String... values) throws Exception {
		// TODO Auto-generated method stub
		boolQueryBuilder.must(QueryBuilders.termsQuery(name, values));
		return this;
	}

	@Override
	public IESQueryBuilders wildcardQuery(String name, String... values) throws Exception {
		// TODO Auto-generated method stub
		for (String value : values){
            boolQueryBuilder.must(QueryBuilders.wildcardQuery(name, "*" + value + "*"));
        }
        return this;
	}

	@Override
	public IESQueryBuilders rangeQuery(String name, String from, String to) throws Exception {
		// TODO Auto-generated method stub
		 boolQueryBuilder.must(QueryBuilders.rangeQuery(name).from(from, true).to(to, true));
	        return this;
	}

	@Override
	public BoolQueryBuilder getBoolQueryBuilder() {
		// TODO Auto-generated method stub
		return boolQueryBuilder;
	}

}
