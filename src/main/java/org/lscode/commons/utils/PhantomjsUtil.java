package org.lscode.commons.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PhantomjsUtil {

	/**
	 * phantomjs路径
	 */
	private static final String PHANTOMJS_PATH = "tools/phantomjs/phantomjs-2.1.1-windows/bin/phantomjs.exe";
	
	/**
	 * phantomjs脚本目录路径
	 */
	private static final String PHANTOMJS_SCRIPT_DIR_PATH = "tools/phantomjs/javascript";
	
	private static final String PHANTOMJS_WORKSPACE = "F:/my_workspace";
	
	/**
	 * html转换为多页pdf文件
	 * @param url
	 */
	public static String htmlToMultiPagePdf(String url) {
		
		List<String> command = new ArrayList<String>();

		String phantomjs = FileUtil.getFilePathInClassPath(PHANTOMJS_PATH).substring(1);
		String js = FileUtil.getFilePathInClassPath(PHANTOMJS_SCRIPT_DIR_PATH + "/toMultiPagePdf.js").substring(1);

		command.add(phantomjs);
		command.add(js);
		command.add(url);
		
		String fileName = PHANTOMJS_WORKSPACE + "/" + UUID.randomUUID() + ".pdf";
		command.add(fileName);
		
		String res = WindowsUtil.cmdWaitFor(command);
		
		System.out.println(res);
		
		return fileName;
	}
	
	public static void main(String[] args) {
		String path = PhantomjsUtil.htmlToMultiPagePdf("http://120.25.59.77/eSaleConsole/portals/login/login.jsp");
		System.out.println(path);
	}
}
