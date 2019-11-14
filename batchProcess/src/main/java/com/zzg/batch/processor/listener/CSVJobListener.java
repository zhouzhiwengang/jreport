package com.zzg.batch.processor.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class CSVJobListener implements JobExecutionListener {
	private Logger logger = LoggerFactory.getLogger(CSVJobListener.class);
	
	private long startTime;
    private long endTime;
	@Override
	public void afterJob(JobExecution arg0) {
		// TODO Auto-generated method stub
		 endTime = System.currentTimeMillis();
	     logger.info("job process end...");
	     logger.info("spend time: " + (endTime - startTime) + "ms");
	}

	@Override
	public void beforeJob(JobExecution arg0) {
		// TODO Auto-generated method stub
		 startTime = System.currentTimeMillis();
	     logger.info("job process start...");
	}

}
