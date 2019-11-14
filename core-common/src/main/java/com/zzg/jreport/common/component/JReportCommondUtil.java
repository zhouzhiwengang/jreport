package com.zzg.jreport.common.component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.zzg.jreport.common.exception.ReportRuntimeException;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;

/**
 * jreport 工具类
 * @author zzg
 *
 */
public class JReportCommondUtil {
	/** 
	 * @param jasperFile  报表模板文件名称
	 * @param datas       报表填充数据集
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static byte[] exportPdf(String jasperFile, List datas)
	{
		byte[] bytes = null;
		try 
		{
			bytes = JasperRunManager.runReportToPdf(readerJasperFile(jasperFile), null, new DefaultJReportDataSource(datas));
		} 
		catch (JRException e) 
		{
			throw new ReportRuntimeException(e);
		}
		return bytes;
	}
	
	/**
	 * 读取指定jasper 文件流
	 * @param jasperFile
	 * @return
	 */
	private static InputStream readerJasperFile(String jasperFile)
	{
		String filePath ="templates/report/"+jasperFile;
		Resource resource = new ClassPathResource(filePath);
		InputStream is = null;
		try {
			is = resource.getInputStream();
		} catch (IOException e) {
			throw new ReportRuntimeException("File " + jasperFile + " not found. The report design must be compiled first.");
		}
		// String path = ReportCreateCommondUtil.class.getProtectionDomain().getCodeSource().getLocation().getPath().toString();
		//InputStream is = ReportCreateCommondUtil.class.getClassLoader().getResourceAsStream("/templates/report/"+jasperFile);
		if (is == null)
		{
			throw new ReportRuntimeException("File " + jasperFile + " not found. The report design must be compiled first.");
		}
		return is;
	}
	

}
