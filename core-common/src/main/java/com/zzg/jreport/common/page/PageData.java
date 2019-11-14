package com.zzg.jreport.common.page;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Setter
@Getter
@AllArgsConstructor
public class PageData<T> implements Serializable {
	private int pageNum; // 当前页
	private int pageSize;// 每页数量
	private long totalCount;// 总条数
	private int totalPageNum;// 总页数
	private List<T> data;// 当前页返回数据
	
	// 空构造函数
	public PageData(){
		
	}

}
