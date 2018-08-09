package com.liaolangsheng.commons;

import com.liaolangsheng.commons.utils.FileUtil;
import com.liaolangsheng.commons.utils.Log4jUtil;
import com.liaolangsheng.commons.utils.QRcodeUtil;
import com.liaolangsheng.commons.utils.VelocityUtil;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        String path = QRcodeUtil.generateQRcode("ddd", "F:\\my_temp", 100, 100);
        FileUtil.openDir(path);

        String url = FileUtil.getFilePathInClassPath("config/log4j.properties");
        System.out.println(url);

        Logger logger = Log4jUtil.getLogger(App.class);

        logger.info("测试日志输出");

        Map<String, Object> map = new HashMap<>();
        map.put("name","廖浪升");
        map.put("say",100);
        String str = VelocityUtil.generate(FileUtil.getFilePathInClassPath("template/hello.vm"), map);
        System.out.println(str);
    }
}
