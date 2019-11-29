package com.zzg.jreport.common.definition;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;
import com.zzg.jreport.common.file.DistributedFile;

/**
 * 分布式文件xml解析
 * @author zzg
 *
 */
public class DistributedFileBeanDefinitionParser implements BeanDefinitionParser {

	@Override
	public BeanDefinition parse(Element element, ParserContext context) {
		RootBeanDefinition def = new RootBeanDefinition();
		 
		// 设置Bean Class
		def.setBeanClass(DistributedFile.class);
 
 
		// 注册属性
		String path = element.getAttribute("path");
		String name = element.getAttribute("name");
		BeanDefinitionHolder pathHolder = new BeanDefinitionHolder(def, path);
		BeanDefinitionReaderUtils.registerBeanDefinition(pathHolder,
				context.getRegistry());
		BeanDefinitionHolder nameHolder = new BeanDefinitionHolder(def, name);
		BeanDefinitionReaderUtils.registerBeanDefinition(nameHolder,
				context.getRegistry());
		
		def.getPropertyValues().addPropertyValue("path", path);
		def.getPropertyValues().addPropertyValue("name", name);
		return def;

	}

}
