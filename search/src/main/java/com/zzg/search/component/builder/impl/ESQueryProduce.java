package com.zzg.search.component.builder.impl;

import org.elasticsearch.index.query.BoolQueryBuilder;
import com.zzg.search.component.builder.IESQueryBuilders;
import com.zzg.search.component.builder.IESQueryProduce;

public class ESQueryProduce implements IESQueryProduce{
	
	private BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
	
	private int size = Integer.MAX_VALUE;
	
	private String asc;

	private String desc;
	
	private int from = 0;
	
	@Override
	public BoolQueryBuilder getBoolQueryBuilder() {
		// TODO Auto-generated method stub
		return boolQueryBuilder;
	}

	@Override
	public IESQueryProduce getProduct(IESQueryBuilders esQueryBuilders) {
		// TODO Auto-generated method stub
		boolQueryBuilder.must(esQueryBuilders.getBoolQueryBuilder());
		return this;
	}

	public String getAsc() {
		return asc;
	}

	public void setAsc(String asc) {
		this.asc = asc;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}
}
