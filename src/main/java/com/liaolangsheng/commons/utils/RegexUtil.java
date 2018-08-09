package com.liaolangsheng.commons.utils;

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
public class RegexUtil {
	
	/**
	 * 获取所有正则表达式匹配的字符串
	 * @param content
	 * @param regex
	 * @return
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
	 * @param regex
	 * @param str
	 * @return
	 */
	public static boolean match(String regex, String str) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}
	
	/**
	 * 判断是否是文件或目录的绝对路径
	 * windows、liunx
	 * @param str
	 * @return
	 */
	public static boolean isPath(String str) {
		String windowsRegex = "(?i)([a-z]\\:([\\\\/][^\\\\/]+)+)";
		String linuxRegex = "(?i)(([/][^\\\\/]+)+)";
		return match(windowsRegex, str) || match(linuxRegex, str);
	}
	
	/**
	 * 获取路径最后一段
	 * @param path
	 * @return
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
