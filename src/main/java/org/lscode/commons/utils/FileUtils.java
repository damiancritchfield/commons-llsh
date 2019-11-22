package org.lscode.commons.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * 
 * @author llsh
 * 
 */
public class FileUtils {
	
	/**
	 * 获取当前目录，File类型
	 * @return 返回当前目录
	 */
	public static File getCurrentDirFile() {
		File directory = new File(".");
		try {
			return directory.getCanonicalFile();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 获取当前目录，字符串类型
	 * @return 返回当前目录路径
	 */
	public static String getCurrentDirPath() {
		File directory = new File(".");
		try {
			return directory.getCanonicalPath();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 写出文本
	 * 
	 * @param path
	 *            路径
	 * @param str
	 *            内容
	 * @param append
	 * 是否追加
	 */
	public static void write(String path, String str, boolean append) {
		try {
			File file = new File(path);
			if (!file.exists()) {
				String dirPath = path.substring(0, path.lastIndexOf("/"));
				File dirFile = new File(dirPath);
				if (!dirFile.exists()) {
					dirFile.mkdirs();
				}
				file.createNewFile();
			}
			FileOutputStream out = new FileOutputStream(file, append); // 如果追加方式用true
			out.write(str.toString().getBytes("utf-8"));// 注意需要转换对应的字符集
			out.close();
		} catch (IOException e) {
			System.out.println(e.getStackTrace());
		}
	}

	/**
	 * 写出文本
	 * 
	 * @param path
	 *            路径
	 * @param str
	 *            内容
	 */
	public static void write(String path, String str) {
		write(path, str, true);
	}
	
	/**
	 * 递归删除文件或目录，包括本目录
	 * @param path 文件或目录路径
	 */
	public static void deleteFile(String path){
		File file = new File(path);
		deleteFile(file);
	}
	
	/**
	 * 递归删除文件或目录，包括本目录
	 * @param file 文件或目录
	 */
	public static void deleteFile(File file) {
		if (file.isDirectory()) {
			File[] subFiles = file.listFiles();
			for (File subFile : subFiles) {
				deleteFile(subFile);
			}
		}
		file.delete();//只对空目录或文件有用，非空目录删除不成功不抛异常
	}
	
	/**
	 * 清空目录下所有文件，不删除本目录
	 * @param dirPath 目录
	 */
	public static void cleanDir(String dirPath){
		File file = new File(dirPath);
		if (file.isDirectory()) {
			File[] subFiles = file.listFiles();
			for (File subFile : subFiles) {
				deleteFile(subFile);
			}
		}
	}

	/**
	 * 打开目录并选中文件
	 * @param filePath 文件路径
	 * @return 是否成功
	 */
	public static boolean openDir(String filePath) {
		try {
			String filePathFormat = filePath.replaceAll("/", "\\\\");

			Process videoProcess = Runtime.getRuntime().exec(
					"explorer /select," + filePathFormat);
			new PrintStream(videoProcess.getErrorStream()).start();
			new PrintStream(videoProcess.getInputStream()).start();
			videoProcess.waitFor();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 文件或目录是否存在
	 * @param filePath 文件路径
	 * @return 文件或目录是否存在
	 */
	public static boolean exists(String filePath) {
		return new File(filePath).exists();
	}

	/**
	 * 获取类路径下的文件路径
	 * @param name 文件名
	 * @return 文件路径
	 */
	public static String getFilePathInClassPath(String name){
		try {
			//获取classpath下的config.json路径
			return FileUtils.class.getClassLoader().getResource(name).toURI().getPath();
		} catch (URISyntaxException e) {
			return null;
		}
	}

	private static class PrintStream extends Thread {
		java.io.InputStream __is = null;

		public PrintStream(java.io.InputStream is) {
			__is = is;
		}

		public void run() {
			try {
				while (this != null) {
					int _ch = __is.read();
					if (_ch != -1)
						System.out.print((char) _ch);
					else
						break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}