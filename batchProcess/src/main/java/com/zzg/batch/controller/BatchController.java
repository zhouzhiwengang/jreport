package com.zzg.batch.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zzg.batch.processor.listener.CSVJobListener;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Controller
@RequestMapping("/api/batch")
@Api(value = "批处理Controlle", tags = "批处理操作服务")
public class BatchController {
	// 日志服务处理
	private Logger logger = LoggerFactory.getLogger(BatchController.class);
	
	@Autowired
	private SimpleJobLauncher  launcher;
	@Autowired
	private Job job;
	
	@ApiOperation(httpMethod = "POST", value = "用户对象保存")
	@RequestMapping(value = "/init", method = { RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public void insert() {
		// 后置参数：使用JobParameters中绑定参数
		try{
			JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
					.toJobParameters();
			launcher.run(job, jobParameters);
			
		} catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}

}
