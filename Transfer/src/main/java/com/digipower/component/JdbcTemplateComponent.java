package com.digipower.component;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

/**
 * JdbcTemplate 通用组件功能封装
 * @author zzg
 *
 */
@Component
public class JdbcTemplateComponent {
	
	//日志记录
	public static final Logger logger = LoggerFactory.getLogger(JdbcTemplateComponent.class);
	
	@Autowired
	private JdbcTemplate template;
	
	 /**
     * 通用的update语句，适用于实体类作为参数的类型
     * @param sql
     * @param object
     */
    public int updateSql(String sql,Object object) {
    	int num = 0;
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(template);
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(object);
        try {
            num = namedParameterJdbcTemplate.update(sql,sqlParameterSource);
        }catch (Exception e){
            logger.error("error sql:" + sql);
        }
        return num;
    }
    
    /**
     * 通用的update语句，适用于数组作为参数的类型
     * @param sql
     * @param objects
     */
    public int updateSql(String sql,Object[] objects){
    	int num = 0;
        try {
        	num = template.update(sql,objects);
        }catch (Exception e){
            logger.error("error sql"+sql);
        }
        return num;
    }
    
    /**
     * 通用的select语句，适用于实体作为参数的类型
     * @param sql
     * @param object
     * @return
     */
    public List<? extends Object> selectSql(String sql, Object object){
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(template);
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(object);
        List<? extends Object> list=null;
        try {
            list = namedParameterJdbcTemplate.query(sql, sqlParameterSource, new BeanPropertyRowMapper<>(object.getClass()));
        }catch (Exception e){
            logger.error("error sql"+sql);
        }
        return list;
    }
    
    /**
     * 通用的select语句，适用于数组作为参数的类型
     * @param sql
     * @param objects
     * @return
     */
    public Object selectSql(String sql, Object[] objects,Class clazz){
        List<Object> list=null;
        try {
            list = template.query(sql, objects, new BeanPropertyRowMapper<>(clazz));
        }catch (Exception e){
            list=new ArrayList<>();
            logger.error("error sql"+sql);
        }
        return list.get(0);
    }
}
