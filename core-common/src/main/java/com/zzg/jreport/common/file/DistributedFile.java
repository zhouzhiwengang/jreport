package com.zzg.jreport.common.file;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * 分布式文件 基础对象
 * @author zzg
 *
 */
@Setter
@Getter
@SuppressWarnings("serial")
public class DistributedFile implements Serializable {
	
	private String name;
	
	private String path;
}
