package org.lscode.commons.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author llsh
 * 日期时间工具类
 */
public class CommonDateUtil {
	/**
	 * 日期格式，例如：2017-05-05
	 */
	public final static String DATE_PATTERN = "yyyy-MM-dd";
	
	/**
	 * 日期格式，例如：2017-05
	 */
	public final static String DATE_MONTH_PATTERN = "yyyy-MM";
	
	/**
	 * 日期时间格式(24小时)，例如：2017-05-05 13:00:34
	 */
	public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 字符串生成日期
	 * @param date
	 * @param pattern 默认为yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static Date parse(String date, String pattern){
		if(StringUtils.isBlank(date)){
			return null;
		}
		if(StringUtils.isBlank(pattern)){
			pattern = DATE_TIME_PATTERN;
		}
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		try {
			return df.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 格式化日期
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String format(Date date, String pattern){
		if(date == null){
			return null;
		}
		if(StringUtils.isBlank(pattern)){
			pattern = DATE_TIME_PATTERN;
		}
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		String dateString = df.format(date);
		return dateString;
	}
	
	/**
	 * 获取日期字段
	 * @param date
	 * @param field
	 * @return
	 */
	public static int getField(Date date, int field){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(field);
	}
	
	/**
	 * 设置日期字段
	 * @param date
	 * @param field
	 * @return
	 */
	public static Date setField(Date date, int field, int value){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(field, value);
		return cal.getTime();
	}
	
	/**
	 * Date指定字段加减
	 * @param date
	 * @param field
	 * @param amount
	 * @return
	 */
	public static Date add(Date date, int field, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(field, amount);
		return cal.getTime();
	}
	
	/**
	 * 通过Date获取Calendar
	 * @param date
	 * @return
	 */
	public static Calendar getCalendarByDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}
	
	/**
	 * 获得今天日期：只包括年月日
	 * @return
	 */
	public static Date getDateWithToday(){
		String dateString = format(new Date(), DATE_PATTERN);
		return parse(dateString, DATE_PATTERN);
	}
	
	/**
	 * 获取当前日期的yyyy-MM-01格式的Date
	 * @return
	 */
	public static Date getDateMonthWithToday() {
		Date date = new Date();
		setField(date, Calendar.DAY_OF_MONTH, 1);
		String dateString = format(date, DATE_PATTERN);
		return parse(dateString, DATE_PATTERN);
	}
	
	/**
	 * 以今日为起点，获取距离当前时间指定天数的日期。
	 * <p>如：今日是2017-05-05时，day=1返回2017-05-06 00:00:00，day=-1返回2017-05-04 00:00:00</p>
	 * @param day
	 * @return
	 */
	public static Date getDateBeginWithToday(int day){
		Date today = getDateWithToday();
		return add(today, Calendar.DAY_OF_MONTH, day);
	}
	
	/**
	 * 以今日为起点，获取距离当前时间指定月数的日期。
	 * <p>如：今日是2017-05-05时，month=1返回2017-06-05 00:00:00，month=-1返回2017-04-05 00:00:00</p>
	 * @return
	 */
	public static Date getDateMonthBeginWithToday(int month){
		Date today = getDateWithToday();
		return add(today, Calendar.MONTH, month);
	}
	
	/**
	 * 获取昨天：只包括年月日
	 * @return
	 */
	public static Date getDateWithYesterday(){
		return getDateBeginWithToday(-1);
	}
	
	/**
	 * 获取明天：只包括年月日
	 * @return
	 */
	public static Date getDateWithTomorrow(){
		return getDateBeginWithToday(1);
	}
	
	/**
	 * 获得今天日期的时间戳：只包括年月日
	 * @return
	 */
	public static long getDateStampWithToday(){
		Date today= getDateWithToday();
		Calendar cal = getCalendarByDate(today);
		long timestamp = cal.getTimeInMillis();
		return timestamp;
	}
	
	/**
	 * 格式化日期时间 yyyy-MM-dd hh:mm:ss
	 * @param date
	 * @return
	 */
	public static String dateTimeFormat(Date date){
		return format(date, DATE_TIME_PATTERN);
	}
	
	/**
	 * 格式化日期 yyyy-MM-dd
	 * @param date
	 * @return
	 */
	public static String dateFormat(Date date){
		return format(date, DATE_PATTERN);
	}
	
	/**
	 * 格式化日期 yyyy-MM
	 * @param date
	 * @return
	 */
	public static String dateMonthFormat(Date date){
		return format(date, DATE_MONTH_PATTERN);
	}
	
	/**
	 * 字符串生成日期时间 yyyy-MM-dd hh:mm:ss
	 * @param date
	 * @return
	 */
	public static Date dateTimeParse(String date){
		return parse(date, DATE_TIME_PATTERN);
	}
	
	/**
	 * 字符串生成日期 yyyy-MM-dd
	 * @param date
	 * @return
	 */
	public static Date dateParse(String date){
		return parse(date, DATE_PATTERN);
	}
	
	/**
	 * 字符串生成日期月 yyyy-MM
	 * @param date
	 * @return
	 */
	public static Date dateMonthParse(String date){
		return parse(date, DATE_MONTH_PATTERN);
	}
	
	/**
	 * 指定时间是否在两个时间参数区间内，包括临界点
	 * @param date
	 * @param startDateTime
	 * @param stopDateTime
	 * @return
	 */
	public static boolean isDateBetween(Date date, Date startDateTime, Date stopDateTime){
		if(startDateTime == null && stopDateTime == null){
			return true;
		}
		if(startDateTime != null && stopDateTime == null){
			if(date.compareTo(startDateTime) < 0){
				return false;
			}
			return true;
		}
		if(startDateTime == null && stopDateTime!=null){
			if(date.compareTo(stopDateTime) > 0){
				return false;
			}
			return true;
		}
		
		if(date.compareTo(startDateTime)>=0 && date.compareTo(stopDateTime)<=0){
			return true;
		}
		return false;
	}
	
	/**
	 * 当前时间是否在两个时间参数区间内，包括临界点
	 * @param startDateTime
	 * @param stopDateTime
	 * @return
	 */
	public static boolean isNowBetween(Date startDateTime, Date stopDateTime){
		return isDateBetween(getDateWithToday(), startDateTime, stopDateTime);
	}
	
	/**
	 * 获取年份
	 * @param date
	 * @return
	 */
	public static int getYear(Date date){
		if(date == null){
			return 0;
		}
		return getField(date, Calendar.YEAR);
	}
	
	/**
	 * 获取月份
	 * @param date
	 * @return
	 */
	public static int getMonth(Date date){
		if(date == null){
			return 0;
		}
		return getField(date, Calendar.MONTH) + 1;
	}
	
	/**
	 * 获取日
	 * @param date
	 * @return
	 */
	public static int getDay(Date date){
		if(date == null){
			return 0;
		}
		return getField(date, Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * 返回某个月份的最后一天
	 * @param date
	 */
	public static Date getLastDateOfMonth(Date date){
		Calendar cal = getCalendarByDate(date);
		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.DAY_OF_MONTH, 0);
		return cal.getTime();
	}
	
	/**
	 * 获取date所在月份最大天数
	 * @param date
	 * @return
	 */
	public static int getLastDayOfMonth(Date date){
        return getDay(getLastDateOfMonth(date));
	}
	
	public static void main(String[] args) {
		
		Date date = CommonDateUtil.dateParse("2017-12-30");
		System.out.println(CommonDateUtil.getYear(date));
		System.out.println(CommonDateUtil.getMonth(date));
		System.out.println(CommonDateUtil.getDay(date));
		System.out.println(CommonDateUtil.getYear(null));
		System.out.println(CommonDateUtil.getMonth(null));
		System.out.println(CommonDateUtil.getDay(null));
		Date date2 = CommonDateUtil.parse("2017-05-23", "yyyy-MM-dd");
		System.out.println(date2);
		String dateStr = CommonDateUtil.format(date2, "yyyy-MM-dd 0505");
		System.out.println(dateStr);
	}
}
