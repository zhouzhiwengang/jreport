package com.zzg.jreport.service;

import java.util.List;
import java.util.Map;

import com.zzg.jreport.common.BaseService;
import com.zzg.jreport.common.page.PageData;
import com.zzg.jreport.common.page.PageParam;
import com.zzg.jreport.domain.UcasUnitProjRelation;

public interface UcasUnitProjRelationService extends BaseService<UcasUnitProjRelation> {
	  // 方法梳理
    List<UcasUnitProjRelation> selectAll(Map<String, Object> paramter);
    
    // 添加分页方法
    PageData<UcasUnitProjRelation> selectAllPage(Map<String,Object> parame, PageParam rb);
}
