package com.zzg.file.component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.I0Itec.zkclient.ZkClient;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zzg.jreport.common.file.DistributedFile;

public class FileComponent {
	// 根路径
	private String root;
	// 分布式配置文件, 默认file.xml
	private String xml = "file.xml";

	// 本机内存临时缓存 节点信息
	List<String> list = new ArrayList<String>();

	int pageCount;
	ZkClient zkClient;

	public FileComponent(String zkServers, int connectionTimeout, String root) {
		this.root = root;
		zkClient = new ZkClient(zkServers, connectionTimeout);
		init();
	}

	// 初始数据方法
	@SuppressWarnings({ "resource", "unused" })
	public void init() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xml);
		String[] names = applicationContext.getBeanDefinitionNames();

		int totalSize = names.length;
		int pageSize = 2;
		pageCount = (totalSize + pageSize - 1) / pageSize;

		for (int i = 0; i < pageCount; i = i + 2) {

			// 创建根节点的临时节点
			zkClient.createEphemeral(root, true);

			// 创建子节点临时节点 + 临时节点数据创建
			zkClient.createEphemeral(root + "/" + names[i + 1], true);
			zkClient.writeData(root + "/" + names[i + 1], names[i]);
			

			// 内存临时缓存
			if (!list.contains(names[i + 1])) {
				list.add(names[i + 1]);
			}
		}
	}

	// 关闭方法
	public void close() {
		zkClient.close();
	}

	// 随机读取分布式文件存储节点数据
	public String getFilePath() {
		String name = list.get(RandomUtils.nextInt(pageCount));
		Object obj = zkClient.readData(root + "/" + name, true);
		if (obj != null) {
			return String.valueOf(obj);
		}
		return null;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}

}
