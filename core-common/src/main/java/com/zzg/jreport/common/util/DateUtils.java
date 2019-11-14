package com.zzg.jreport.common.util;

import java.lang.management.ManagementFactory;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 时间工具类
 * @author zzg
 *
 */
public class DateUtils {

	public static final String YYYY = "yyyy" ;

    public static final String YYYY_MM = "yyyy-MM" ;

    public static final String YYYY_MM_DD = "yyyy-MM-dd" ;

    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss" ;

    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss" ;

    private static String[] parsePatterns = {
            YYYY_MM_DD , YYYY_MM_DD_HH_MM_SS , "yyyy-MM-dd HH:mm" , YYYY_MM ,
            "yyyy/MM/dd" , "yyyy/MM/dd HH:mm:ss" , "yyyy/MM/dd HH:mm" , "yyyy/MM" ,
            "yyyy.MM.dd" , "yyyy.MM.dd HH:mm:ss" , "yyyy.MM.dd HH:mm" , "yyyy.MM"};

    /**
     * 获取当前Date型日期
     *
     * @return Date() 当前日期
     */
    public static Date getNowDate() {
        return new Date();
    }

    /**
     * 获取当前日期, 默认格式为yyyy-MM-dd
     *
     * @return String
     */
    public static String getDate() {
        return dateTimeNow(YYYY_MM_DD);
    }

    public static final String getTime() {
        return dateTimeNow(YYYY_MM_DD_HH_MM_SS);
    }

    public static final String dateTimeNow() {
        return dateTimeNow(YYYYMMDDHHMMSS);
    }

    public static final String dateTimeNow(final String format) {
        return parseDateToStr(format, new Date());
    }

    public static final String dateTime(final Date date) {
        return parseDateToStr(YYYY_MM_DD, date);
    }

    public static final String parseDateToStr(final String format, final Date date) {
    	if (date == null) {
            return null;
        }

        Format formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }

    /**
     * 获取服务器启动时间
     */
    public static Date getServerStartDate() {
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        return new Date(time);
    }
    
    private static final List<DateFormat> formarts = new ArrayList<>(4);
	static {
		formarts.add(new SimpleDateFormat("yyyy-MM"));
		formarts.add(new SimpleDateFormat("yyyy-MM-dd"));
		formarts.add(new SimpleDateFormat("yyyy-MM-dd hh:mm"));
		formarts.add(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));
	}

	public static Date formatDateStr(String source) {
		String value = source.trim();
		if ("".equals(value)) {
			return null;
		}
		try {
			if (source.matches("^\\d{4}-\\d{1,2}$")) {
				return formarts.get(0).parse(source);
			} else if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$")) {
				return formarts.get(1).parse(source);
			} else if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}$")) {
				return formarts.get(2).parse(source);
			} else if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$")) {
				return formarts.get(3).parse(source);
			} else {
				throw new IllegalArgumentException("Invalid boolean value '" + source + "'");
			}
		} catch (Exception e) {
			return null;
		}
	}

    /**
     * 计算两个时间差
     */
    public static String getDatePoor(Date endDate, Date nowDate) {
        long nd = (long)1000 * 24 * 60 * 60;
        long nh = (long)1000 * 60 * 60;
        long nm = (long)1000 * 60;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        return day + "天" + hour + "小时" + min + "分钟" ;
    }

}
