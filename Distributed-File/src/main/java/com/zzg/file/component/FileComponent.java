package com.zzg.file.component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zzg.jreport.common.file.DistributedFile;

public class FileComponent {
	// 日志记录
	
	// 根路径
	private String root;
	// 分布式配置文件, 默认file.xml
	private String xml = "file.xml";

	// 本机内存临时缓存 节点信息
	List<String> list = new ArrayList<String>();

	int pageCount;
	CuratorFramework zkClient;

	public FileComponent(String zkServers, String root) {
		this.root = root;
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
		zkClient =   CuratorFrameworkFactory.builder()
                .connectString(zkServers)
                .sessionTimeoutMs(5000)
                .connectionTimeoutMs(5000)
                .retryPolicy(retryPolicy)
                .namespace(root)
                .build();
		
		try {
			init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 初始数据方法
	@SuppressWarnings({ "resource", "unused" })
	public void init() throws Exception {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xml);
		String[] names = applicationContext.getBeanDefinitionNames();

		int totalSize = names.length;
		int pageSize = 2;
		pageCount = (totalSize + pageSize - 1) / pageSize;

		for (int i = 0; i < pageCount; i = i + 2) {

			// 创建根节点的临时节点
			if(zkClient.getState() != CuratorFrameworkState.STARTED){
				zkClient.start();
			}
			zkClient.create().withMode(CreateMode.EPHEMERAL).forPath("/" + names[i + 1], names[i].getBytes());
			
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
	public String getFilePath() throws Exception {
		int num = this.getRandomNumber(0, pageCount - 1);
		System.out.println("随机数:" + num);
		String name = list.get(num);
		
		byte[] bytes = zkClient.getData().forPath("/" + name);
		if (bytes != null && bytes.length > 0) {
			return new String(bytes);
		}
		return null;
	}
	
	 /**
     * 获得某个范围内的随机数
     * @param num1 起始范围参数
     * @param num2 终止范围参数
     * @return 返回num1到num2的随机数
     */
    public int getRandomNumber(int num1, int num2) {
        return (int) (num1 + Math.random() * (num2 - num1));
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
