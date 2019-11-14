package com.zzg.jreport.common.page;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 分页请求参数
 * @author zzg
 *
 */
@SuppressWarnings("serial")
public class PageParam implements Serializable {
    /** The limit. */
    private int limit;
    /** The page no. */
    private int pageNo;
    
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
	public PageParam(int pageNo, int limit){
		this.pageNo = pageNo;
		this.limit = limit;
	}
	
	
    
    
}
