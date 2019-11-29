package com.zzg.file.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zzg.jreport.common.file.DistributedFile;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("file.xml");
		DistributedFile master = (DistributedFile) applicationContext.getBean("master");
		System.out.println("path:" + master.getPath() + " name:" + master.getName() );
	}

}
