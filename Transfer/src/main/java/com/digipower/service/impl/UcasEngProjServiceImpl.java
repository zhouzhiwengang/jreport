package com.digipower.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.digipower.component.JdbcTemplateComponent;
import com.digipower.domain.UcasEngProj;
import com.digipower.service.UcasEngProjService;

@Service
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
public class UcasEngProjServiceImpl implements UcasEngProjService {
	
	@Autowired
	private JdbcTemplateComponent component;

	@Override
	public UcasEngProj selectBySid(String sid) {
		// TODO Auto-generated method stub
		String sql = "";
		Object[] objects = new Object[]{sid};
		return (UcasEngProj) component.selectSql(sql, objects, UcasEngProj.class);
	}

	@Override
	public boolean insert(UcasEngProj object) {
		// TODO Auto-generated method stub
		boolean target = false;
		String sql = "insert into ucas_eng_proj(sid) values(:sid)";
		int num = component.updateSql(sql, object);
		if(num > 0){
			target = true;
		}
		return target;
	}

	@Override
	public boolean update(UcasEngProj object) {
		// TODO Auto-generated method stub
		boolean target = false;
		String sql = "update ucas_eng_proj t set t.eng_proj_name=? where t.sid=?";
		int num = component.updateSql(sql, new Object[]{object.getEngProjName(),object.getSid()});
		if(num > 0){
			target = true;
		}
		return target;
	}

	@Override
	public List<UcasEngProj> select(UcasEngProj t) {
		// TODO Auto-generated method stub
		String sql = "select * from ucas_eng_proj a where a.sid =:sid";
		List<UcasEngProj> list = (List<UcasEngProj>) component.selectSql(sql, t);
		return list;
	}

	@Override
	public boolean delete(String sid) {
		// TODO Auto-generated method stub
		boolean target = false;
		String sql = "delete from ucas_eng_proj where ucas_eng_proj.sid =?";
		Object[] objects = new Object[]{sid};
		int num = component.updateSql(sql, objects);
		if(num > 0){
			target = true;
		}
		return target;
	}

}
