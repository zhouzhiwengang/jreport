package com.zzg.jreport.common.namespace;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

import com.zzg.jreport.common.definition.DistributedFileBeanDefinitionParser;

/**
 * 分布式文件# 命名空间定义
 * @author zzg
 *
 */
public class DistributedFileNamespaceHandlerSupport extends NamespaceHandlerSupport {

	@Override
	public void init() {
		// TODO Auto-generated method stub
		registerBeanDefinitionParser("file", new DistributedFileBeanDefinitionParser());
	}

}
