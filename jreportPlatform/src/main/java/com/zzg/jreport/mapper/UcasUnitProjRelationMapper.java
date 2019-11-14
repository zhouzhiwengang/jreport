package com.zzg.jreport.mapper;


import java.util.List;
import java.util.Map;

import com.zzg.jreport.domain.UcasUnitProjRelation;

public interface UcasUnitProjRelationMapper {
    int deleteByPrimaryKey(String sid);

    int insert(UcasUnitProjRelation record);

    int insertSelective(UcasUnitProjRelation record);

    UcasUnitProjRelation selectByPrimaryKey(String sid);

    int updateByPrimaryKeySelective(UcasUnitProjRelation record);

    int updateByPrimaryKey(UcasUnitProjRelation record);
    
    // 方法梳理
    List<UcasUnitProjRelation> selectAll(Map<String, Object> paramter);
}