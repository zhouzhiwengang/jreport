package com.zzg.jreport.common.component;

import java.util.List;

import com.zzg.jreport.common.util.BeanUtils;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class DefaultJReportDataSource implements JRDataSource {
	@SuppressWarnings("rawtypes")
	private List rows = null;
	private int index = -1;
	

	public DefaultJReportDataSource(@SuppressWarnings("rawtypes") List data)
	{
		this.rows = data;
	}

	@Override
	public boolean next() throws JRException {
		// TODO Auto-generated method stub
		return (++index < this.rows.size());
	}

	@Override
	public Object getFieldValue(JRField jrField) throws JRException {
		// TODO Auto-generated method stub
		String fieldName = jrField.getName();
		Object obj = rows.get(index);
		Object res = null;
		try 
		{
			res = BeanUtils.getProperty(obj, fieldName); 
		} 
		catch (Exception e) 
		{
			res = " ";
		}
			
		return res;
	}
}
