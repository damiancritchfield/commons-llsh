package org.lscode.commons.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 正则表达式匹配工具
 * @author liaol
 *
 */
public class RegexUtils {
	
	/**
	 * 获取所有正则表达式匹配的字符串
	 * @param content 内容
	 * @param regex 正则表达式
	 * @return 所有匹配的字符串
	 */
	public static List<String> getMatchingString(String content, String regex){
		if(StringUtils.isBlank(content) || StringUtils.isBlank(regex)) {
			return null;
		}
		Pattern p = Pattern.compile(regex);
		Matcher matcher = p.matcher(content);
		
		List<String> matchingStrs = new ArrayList<String>();
		while (matcher.find()) {
			matchingStrs.add(matcher.group());
		}
		return matchingStrs;
	}
	
	/**
	 * 判断字符串是否匹配正则表达式
	 * @param regex 正则表达式
	 * @param str 待匹配的字符串
	 * @return 是否匹配
	 */
	public static boolean match(String regex, String str) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}
	
	/**
	 * 判断是否是文件或目录的绝对路径
	 * windows、liunx
	 * @param str 字符串
	 * @return 是否是文件或目录的绝对路径
	 */
	public static boolean isPath(String str) {
		String windowsRegex = "(?i)([a-z]\\:([\\\\/][^\\\\/]+)+)";
		String linuxRegex = "(?i)(([/][^\\\\/]+)+)";
		return match(windowsRegex, str) || match(linuxRegex, str);
	}
	
	/**
	 * 获取路径最后一段
	 * @param path 路径
	 * @return 路径最后一个字符串
	 */
	public static String getPathCaudal(String path) {
		String regex = "[^(/|\\\\\\\\)]+";
		List<String> matches = getMatchingString(path, regex);
		if(CollectionUtils.isEmpty(matches)) {
			return null;
		}
		return matches.get(matches.size()-1);
	}
}
