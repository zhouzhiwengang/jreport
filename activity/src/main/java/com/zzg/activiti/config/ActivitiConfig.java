package com.zzg.activiti.config;

import javax.sql.DataSource;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
/**
 * activiti 流程配置
 * @author zzg
 *
 */
@Configuration
public class ActivitiConfig {
	
	// 日志管理
//	private Logger logger = LoggerFactory.getLogger(ActivitiConfig.class);
//	
//	
//	@Autowired
//	private DataSource dataSource;
//	
//	@Autowired
//	private PlatformTransactionManager transactionManager;
//	
//	/**
//	 * activiti 处理引擎配置参数实列化对象
//	 * @return
//	 */
//	@Bean
//	public SpringProcessEngineConfiguration processEngineConfig(){
//		SpringProcessEngineConfiguration processEngineConfiguration = new SpringProcessEngineConfiguration();
//		// 数据源设置
//		processEngineConfiguration.setDataSource(dataSource);
//		// 事务管理器
//		processEngineConfiguration.setTransactionManager(transactionManager);
//		return processEngineConfiguration;
//	}
//	
//	/**
//	 * activiti 处理引擎实列化对象
//	 * @return
//	 */
//	@Bean
//	public ProcessEngine processEngine(){
//		ProcessEngine processEngine= null;
//		ProcessEngineFactoryBean factory = new ProcessEngineFactoryBean();
//		factory.setProcessEngineConfiguration(processEngineConfig());
//		try {
//			processEngine = factory.getObject();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			logger.error(e.getMessage());
//			return null;
//		}
//		return processEngine;
//	}
//	/**
//	 * activiti 部署对象实例化
//	 */
//	@Bean
//	public RepositoryService getRepository(){
//		return processEngine().getRepositoryService();
//	}
//	
//	/**
//	 * activiti 运行对象实例化
//	 */
//	@Bean
//	public RuntimeService getRuntime(){
//		return processEngine().getRuntimeService();
//	}
//	
//	/**
//	 * activiti 任务对象实例化
//	 */
//	@Bean
//	public TaskService getTask(){
//		return processEngine().getTaskService();
//	}
//	
//	/**
//	 * activiti 历史任务对象实例化
//	 * @return
//	 */
//	@Bean
//	public HistoryService getHistory(){
//		return processEngine().getHistoryService();
//	}
//	
//	/**
//	 * activiti 表单对象实例化
//	 * @return
//	 */
//	@Bean
//	public FormService getForm(){
//		return processEngine().getFormService();
//	}
	
	
	
	

}
