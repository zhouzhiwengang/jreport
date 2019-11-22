package com.zzg.hadoop.config;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.fs.FileSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * hadoop hdfs 参数配置
 * @author zzg
 *
 */
@Configuration
public class HadoopHDFSConfig {
	
	// 日志记录
	private static final Logger logger = LoggerFactory.getLogger(HadoopHDFSConfig.class);	

	@Value("${hdfs.hdfsPath}")
	private String hdfsPath;
	@Value("${hdfs.hdfsName}")
	private String hdfsName;
	
	/**
	 * hadoop hdfs 配置参数对象
	 * @return
	 */
	@Bean
	public org.apache.hadoop.conf.Configuration  getConfiguration(){
		org.apache.hadoop.conf.Configuration configuration = new org.apache.hadoop.conf.Configuration();
		configuration.set("fs.defaultFS", hdfsPath);
		return configuration;
	}
	/**
	 * hadoop filesystem 文件系统
	 * @return
	 */
	@Bean
	public FileSystem getFileSystem(){
		FileSystem fileSystem = null;
		try {
			fileSystem = FileSystem.get(new URI(hdfsPath), getConfiguration(), hdfsName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}
		return fileSystem;
	}
	
	

}
