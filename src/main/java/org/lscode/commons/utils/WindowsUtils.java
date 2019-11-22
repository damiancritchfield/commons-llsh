package org.lscode.commons.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

public class WindowsUtils {

	/**
	 * 执行外部命令，等待外部名字执行完毕再返回
	 * @param command 命令集合
	 * @return
	 * 返回执行结果
	 */
	public static String cmdWaitFor(List<String> command) {
		if(CollectionUtils.isEmpty(command)){
			return null;
		}
		try {
			ProcessBuilder builder = new ProcessBuilder();
			builder.command(command);
			builder.redirectErrorStream(true);
			Process p = builder.start();

			// 1. start
			BufferedReader buf = null; // 保存ffmpeg的输出结果流
			String line = null;
			// read the standard output

			buf = new BufferedReader(new InputStreamReader(p.getInputStream()));

			StringBuffer sb = new StringBuffer();
			while ((line = buf.readLine()) != null) {
				sb.append(line);
				continue;
			}
			p.waitFor();//这里线程阻塞，将等待外部转换进程运行成功运行结束后，才往下执行
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
