package org.lscode.commons.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class NameUtils {
	
	private static Pattern linePattern = Pattern.compile("_(\\w)");
	private static Pattern humpPattern = Pattern.compile("[A-Z]");
	
	/**
	 * 下划线名称转换为驼峰短类名，首字母大写
	 * @param lineName 下划线名称
	 * @return 短类名
	 */
	public static String toShortClassName(String lineName){
		String humpName = lineNameToHumpName(lineName);
		return upperFirstLetter(humpName);
	}
	
	/**
	 * 首字母大写
	 * @param name 字符串
	 * @return 返回首字母大写后的字符串
	 */
	public static String upperFirstLetter(String name){
		if(StringUtils.isBlank(name)){
			return name;
		}
		String firstLetter = (name.charAt(0)+"").toUpperCase();
		return name.replaceFirst(name.charAt(0)+"", firstLetter);
	}
	
	/**
	 * 首字母小写
	 * @param name 字符串
	 * @return 首字母小写后的字符串
	 */
	public static String lowerFirstLetter(String name){
		if(StringUtils.isBlank(name)){
			return name;
		}
		String firstLetter = (name.charAt(0)+"").toLowerCase();
		return name.replaceFirst(name.charAt(0)+"", firstLetter);
	}
	
	/**
	 * 下划线命名转驼峰命名，首字母小写
	 * @param lineName 下划线命名名称
	 * @return 返回驼峰命名
	 */
	public static String lineNameToHumpName(String lineName) {
		if(StringUtils.isBlank(lineName)) {
			return lineName;
		}
		
		String lowerLineName = lineName.toLowerCase();
		Matcher matcher = linePattern.matcher(lowerLineName);
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
		}
		matcher.appendTail(sb);
		return sb.toString();
	}
	
	/**
	 * 转换为短类名，不包含包路径
	 * @param longClassName 长类名转换为短类名
	 * @return 短类名
	 */
	public static String longClassNameToShortClassName(String longClassName){
		String[] name = longClassName.split("[.]");
		return name[name.length-1];
	}
	
	/**
	 * 去除下划线前缀
	 * @param lineName 下滑线名称
	 * @return 去除下划线前缀后的名称
	 */
	public static String dislodgePrefix(String lineName) {
		return lineName.replaceFirst("^[a-zA-Z]{1}_", "");
	}
}
