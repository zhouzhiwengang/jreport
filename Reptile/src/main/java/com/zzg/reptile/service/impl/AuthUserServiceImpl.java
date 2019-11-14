package com.zzg.reptile.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzg.jreport.common.convert.SimpleTypeConverterUtil;
import com.zzg.jreport.common.page.PageData;
import com.zzg.jreport.common.page.PageParam;
import com.zzg.reptile.domain.AuthUser;
import com.zzg.reptile.mapper.AuthUserMapper;
import com.zzg.reptile.service.AuthUserService;

@Service
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
public class AuthUserServiceImpl implements AuthUserService {
	@Autowired
	private AuthUserMapper mapper;

	@Override
	public Long insert(AuthUser entity) {
		// TODO Auto-generated method stub
		Integer num = mapper.insertSelective(entity);
		return Long.valueOf(num);
	}

	@Override
	public void updateByPrimaryKeySelective(AuthUser entity) {
		// TODO Auto-generated method stub
		mapper.updateByPrimaryKeySelective(entity);
	}

	@Override
	public AuthUser selectByPrimaryKey(String sid) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(SimpleTypeConverterUtil.convertIfNecessary(sid, int.class));
	}

	@Override
	public void deleteByPrimaryKey(String sid) {
		// TODO Auto-generated method stub
		mapper.deleteByPrimaryKey(SimpleTypeConverterUtil.convertIfNecessary(sid, int.class));
	}

	@Override
	public List<AuthUser> selectAll(Map<String, Object> paramter) {
		// TODO Auto-generated method stub
		return mapper.selectAll(paramter);
	}

	@Override
	public PageData<AuthUser> selectAllPage(Map<String, Object> parame, PageParam rb) {
		// TODO Auto-generated method stub
		PageData<AuthUser> pageData = new PageData<AuthUser>();

		PageHelper.startPage(rb.getPageNo(), rb.getLimit());
		List<AuthUser> rs = mapper.selectAll(parame);

		PageInfo<AuthUser> pageInfo = new PageInfo<AuthUser>(rs);
		pageData.setData(pageInfo.getList());
		pageData.setPageNum(pageInfo.getPageNum());
		pageData.setPageSize(pageInfo.getPageSize());
		pageData.setTotalCount(pageInfo.getTotal());
		return pageData;
	}
}
