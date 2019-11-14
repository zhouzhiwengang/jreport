package com.zzg.reptile.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
/**
 * druid 监控配置
 * @author zzg
 *
 */
@Configuration
public class DruidConfig {
	 	@Bean
	    public ServletRegistrationBean druidServletRegistrationBean() {
	        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
	        servletRegistrationBean.setServlet(new StatViewServlet());
	        servletRegistrationBean.addUrlMappings("/druid/*");
	        servletRegistrationBean.addInitParameter("allow", "");
	        servletRegistrationBean.addInitParameter("deny", "");
	        servletRegistrationBean.addInitParameter("loginUsername", "admin");
	        servletRegistrationBean.addInitParameter("loginPassword", "admin");
	        return servletRegistrationBean;
	    }

	    /**
	     * 注册DruidFilter拦截
	     *
	     * @return
	     */
	    @Bean
	    public FilterRegistrationBean duridFilterRegistrationBean() {
	        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
	        filterRegistrationBean.setFilter(new WebStatFilter());
	        Map<String, String> initParams = new HashMap<String, String>();
	        //设置忽略请求
	        initParams.put("exclusions", "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*");
	        filterRegistrationBean.setInitParameters(initParams);
	        filterRegistrationBean.addUrlPatterns("/*");
	        return filterRegistrationBean;
	    }
}
