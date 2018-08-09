package com.liaolangsheng.commons.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
* @author 廖浪升
* @version 创建时间：2017年11月9日 下午5:40:52
* 
*/
public class Log4jUtil {
	
	/**
	 * 默认配置文件
	 */
	private static final String DEFAULT_PROPERTIES_PATH = "config/log4j.properties";
	private static Logger logger;
	
	/**
	 * 获取logger，使用本地默认配置文件
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Logger getLogger(Class clazz) {
		
		if(logger != null) {
			return logger;
		}
		
		return logger = getLogger(clazz, null);
	}
	
	/**
	 * 获取logger对象
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Logger getLogger(Class clazz, String propertiesPath) {
		
		String properties = StringUtils.isBlank(propertiesPath) ? FileUtil.getFilePathInClassPath(DEFAULT_PROPERTIES_PATH) : propertiesPath;
		
		if(!FileUtil.exists(properties)) {
			properties = DEFAULT_PROPERTIES_PATH;
		}
		
		PropertyConfigurator.configure(properties);
		
		return Logger.getLogger(clazz);
	}
}

