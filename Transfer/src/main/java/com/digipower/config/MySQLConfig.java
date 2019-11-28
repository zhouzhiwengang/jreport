package com.digipower.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import com.alibaba.druid.pool.DruidDataSource;

/**
 * mysql 配置化对象
 * @author zzg
 *
 */
@Configuration
public class MySQLConfig {
	
	@ConfigurationProperties(prefix = "spring.datasource")
	@Bean
	public DruidDataSource getDataSource(){
		DruidDataSource dataSource = new DruidDataSource();
		return dataSource;
	}
	
	@Bean
	public JdbcTemplate getTemplate(){
		JdbcTemplate template = new JdbcTemplate(getDataSource());
		return template;
	}

}
