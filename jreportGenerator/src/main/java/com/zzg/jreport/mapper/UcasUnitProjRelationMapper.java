package com.zzg.jreport.mapper;

import com.zzg.jreport.domain.UcasUnitProjRelation;

public interface UcasUnitProjRelationMapper {
    int deleteByPrimaryKey(String sid);

    int insert(UcasUnitProjRelation record);

    int insertSelective(UcasUnitProjRelation record);

    UcasUnitProjRelation selectByPrimaryKey(String sid);

    int updateByPrimaryKeySelective(UcasUnitProjRelation record);

    int updateByPrimaryKey(UcasUnitProjRelation record);
}