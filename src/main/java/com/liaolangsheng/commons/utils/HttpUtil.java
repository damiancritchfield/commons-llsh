package com.liaolangsheng.commons.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * http工具
 * 
 * @author liaol
 * 
 */
public class HttpUtil {

	/**
	 * 模拟请求
	 * 
	 * @param url
	 *            资源地址
	 * @param map
	 *            参数列表
	 *            编码
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public static String post(String url, Map<String, String> map) throws IOException {
		return post(url, map, "utf-8");
	}

	public static String post(String url, Map<String, String> map, String encoding) throws IOException {
		String body = "";

		// 创建httpclient对象
		CloseableHttpClient client = HttpClients.createDefault();
		// 创建post方式请求对象
		HttpPost httpPost = new HttpPost(url);

		// 装填参数
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		if (map != null) {
			for (Entry<String, String> entry : map.entrySet()) {
				nvps.add(new BasicNameValuePair(entry.getKey(), entry
						.getValue()));
			}
		}
		// 设置参数到请求对象中
		httpPost.setEntity(new UrlEncodedFormEntity(nvps, encoding));

		// 设置header信息
		// 指定报文头【Content-type】、【User-Agent】
		httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
		httpPost.setHeader("User-Agent",
				"Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

		// 执行请求操作，并拿到结果（同步阻塞）
		CloseableHttpResponse response = client.execute(httpPost);
		// 获取结果实体
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			// 按指定编码转换结果实体为String类型
			body = EntityUtils.toString(entity, encoding);
		}
		EntityUtils.consume(entity);
		// 释放链接
		response.close();
		return body;
	}
	
	/**
	 * 阻塞下载文件
	 * @param url
	 * @param destFile
	 * @return
	 */
	public static int download(String url, String destFile) {
		CloseableHttpClient client = HttpClients.createDefault();
		RequestConfig config = RequestConfig.custom().build();
		
		HttpGet httpGet = new HttpGet(url);
		httpGet.setConfig(config);
		
		//下载需登陆，设置登陆后的cookie
//		httpGet.addHeader("Cookie", cookie);

		try {
		    HttpResponse respone = client.execute(httpGet);
		    
		    int statusCode = respone.getStatusLine().getStatusCode();
		    
		    if(statusCode != HttpStatus.SC_OK){
		        return statusCode;
		    }
		    HttpEntity entity = respone.getEntity();
		    if(entity != null) {
		        InputStream is = entity.getContent();
		        
		        //目標文件生成路徑
		        File file = new File(destFile);
		        if(!file.exists()){
		        	file.getParentFile().mkdirs();
		        }
		        
		        FileOutputStream fos = new FileOutputStream(file); 
		        
		        byte[] buffer = new byte[4096];
		        int len = -1;
		        while((len = is.read(buffer) )!= -1){
		            fos.write(buffer, 0, len);
		        }
		        fos.close();
		        is.close();
		        
		        return statusCode;
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		}finally{
		    try {
		        client.close();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}
		return 0;
    }
}
