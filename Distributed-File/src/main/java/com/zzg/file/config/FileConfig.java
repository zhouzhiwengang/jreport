package com.zzg.file.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.zzg.file.component.FileComponent;

@Configuration
public class FileConfig {
	
	@Bean
	public FileComponent getFileComponent() {
		FileComponent component = new FileComponent("192.168.1.74:2181", 3000, "/root");
		return component;
	}

}
