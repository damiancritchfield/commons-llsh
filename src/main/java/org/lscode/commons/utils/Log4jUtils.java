package org.lscode.commons.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
* @author 廖浪升
* @version 创建时间：2017年11月9日 下午5:40:52
* 
*/
public class Log4jUtils {
	
	/**
	 * 默认配置文件
	 */
	private static final String DEFAULT_PROPERTIES_PATH = "config/log4j.properties";
	private static Logger logger;
	
	/**
	 * 获取logger，使用本地默认配置文件
	 * @param clazz 类型
	 * @return 日志对象
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
	 * @param clazz 类型
	 * @param propertiesPath 设置属性的配置文件路径
	 * @return 日志对象
	 */
	@SuppressWarnings("rawtypes")
	public static Logger getLogger(Class clazz, String propertiesPath) {
		
		String properties = StringUtils.isBlank(propertiesPath) ? FileUtils.getFilePathInClassPath(DEFAULT_PROPERTIES_PATH) : propertiesPath;
		
		if(!FileUtils.exists(properties)) {
			properties = DEFAULT_PROPERTIES_PATH;
		}
		
		PropertyConfigurator.configure(properties);
		
		return Logger.getLogger(clazz);
	}
}

