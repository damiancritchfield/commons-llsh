package com.liaolangsheng.commons.utils;

import java.io.File;
import java.util.Hashtable;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

/**
 * 二维码工具类
 * @author 廖浪升
 *
 * 2017年6月22日下午5:18:59
 */
public class QRcodeUtil {
	
	/**
	 * 生成二维码保存到文件，输出.png格式，如果文件存在会覆盖文件
	 * @param text
	 * 二维码内容
	 * @param fileName
	 * 输出文件绝对路径，如果路径以.png结尾，则在创建.png指定的图片文件，否则将在目录下自动生成文件名
	 * @param width
	 * 输出图片宽度
	 * @param height
	 * 输出图片高度
	 */
	public static String generateQRcode(String text, String fileName, int width, int height){
		
		if(StringUtils.isBlank(text)
				|| StringUtils.isBlank(fileName)
				|| width <= 0
				|| height <= 0
				) {
			return null;
		}
		
        String format = "png";
        Hashtable<EncodeHintType, String> hints= new Hashtable<EncodeHintType, String>();   
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        
        try {
        	BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height,hints);
        	
        	File file = new File(fileName);
        	if(!file.exists()) {
        		if(StringUtils.endsWith(fileName, ".png")) {
        			file.getParentFile().mkdirs();
        		}else {
        			file.mkdirs();
        			file = new File(file, UUID.randomUUID().toString()+".png");
        		}
        	}else {
        		if(file.isDirectory()){
        			file = new File(file, UUID.randomUUID().toString()+".png");
        		}else {
        			if(!StringUtils.endsWith(fileName, ".png")) {
        				file = new File(file.getParentFile(), RegexUtil.getPathCaudal(fileName)+".png");
        			}
        		}
        	}
        	
        	BitMatrixUtil.writeToFile(bitMatrix, format, file);
        	return file.getAbsolutePath();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
