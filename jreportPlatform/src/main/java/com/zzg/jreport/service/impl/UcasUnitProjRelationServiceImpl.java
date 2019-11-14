package com.zzg.jreport.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzg.jreport.common.page.PageData;
import com.zzg.jreport.common.page.PageParam;
import com.zzg.jreport.domain.UcasUnitProjRelation;
import com.zzg.jreport.mapper.UcasUnitProjRelationMapper;
import com.zzg.jreport.service.UcasUnitProjRelationService;

@Service
public class UcasUnitProjRelationServiceImpl implements UcasUnitProjRelationService {

	@Autowired
	private UcasUnitProjRelationMapper mapper;
	@Override
	public Long insert(UcasUnitProjRelation entity) {
		// TODO Auto-generated method stub
		Integer num = mapper.insertSelective(entity);
		return Long.valueOf(num);
	}

	@Override
	public void updateByPrimaryKeySelective(UcasUnitProjRelation entity) {
		// TODO Auto-generated method stub
		mapper.updateByPrimaryKeySelective(entity);
	}

	@Override
	public UcasUnitProjRelation selectByPrimaryKey(String sid) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(sid);
	}

	@Override
	public void deleteByPrimaryKey(String sid) {
		// TODO Auto-generated method stub
		mapper.deleteByPrimaryKey(sid);
	}

	@Override
	public List<UcasUnitProjRelation> selectAll(Map<String, Object> paramter) {
		// TODO Auto-generated method stub
		return mapper.selectAll(paramter);
	}

	@Override
	public PageData<UcasUnitProjRelation> selectAllPage(Map<String, Object> parame, PageParam rb) {
		// TODO Auto-generated method stub
		PageData<UcasUnitProjRelation> pageData = new PageData<UcasUnitProjRelation>();
		
		PageHelper.startPage(rb.getPageNo(), rb.getLimit());
		List<UcasUnitProjRelation> rs = mapper.selectAll(parame);
		
		PageInfo<UcasUnitProjRelation> pageInfo = new PageInfo<UcasUnitProjRelation>(rs);
		pageData.setData(pageInfo.getList());
		pageData.setPageNum(pageInfo.getPageNum());
		pageData.setPageSize(pageInfo.getPageSize());
		pageData.setTotalCount(pageInfo.getTotal());
		return pageData;
	}

}
