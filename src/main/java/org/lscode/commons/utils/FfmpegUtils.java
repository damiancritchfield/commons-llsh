package org.lscode.commons.utils;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

public class FfmpegUtils {

	/**
	 * 获得视频完整描述
	 * @param filepath 文件路径
	 * @return 返回视频完整描述
	 */
	public static String getVideoDescribe(String filepath) {
		List<String> command = new java.util.ArrayList<String>();
		command.add("ffmpeg.exe");
		command.add("-i");
		command.add(filepath);
		
		return SystemUtils.cmdWaitFor(command);
	}
	
	/**
	 * 获得视频总长度，格式：时:分:秒.毫秒
	 * @param filepath 获取视频时长
	 * @return 返回视频时长格式：时:分:秒.毫秒
	 */
	public static String getVideoDurationString(String filepath){
		
		if(StringUtils.isBlank(filepath)){
			return null;
		}
		
		String result = getVideoDescribe(filepath);

		String regexDuration = "Duration: (.*?), start: (.*?), bitrate: (\\d*) kb\\/s";
		List<String> list = RegexUtils.getMatchingString(result, regexDuration);
		if(CollectionUtils.isEmpty(list)){
			return null;
		}
		
		String regexTime = "[0-9]*:[0-9]*:[0-9]*.[0-9]*";
		list = RegexUtils.getMatchingString(list.get(0), regexTime);
		if(CollectionUtils.isEmpty(list)){
			return null;
		}
		return list.get(0);
	}
}
