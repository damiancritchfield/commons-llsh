package org.lscode.commons.utils;

import org.docx4j.org.apache.poi.util.IOUtils;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class WordUtilsTest {

    @Test
    public void insertImgs() throws Exception {
        // 模板文件路径
        String templatePath = "F://my_temp/custReportTemlpate.docx";
        // 生成的文件路径
        String targetPath = "F://my_temp/target.docx";
        // 书签名
        String bookmarkName = "bookmark";
        // 图片路径
        String imagePath = "F://my_temp/1bc655b5-de6e-43e2-87c3-822f2f1df591.png";

        Map<String, byte[]> replacement = new HashMap<String, byte[]>();

        InputStream is = new FileInputStream(imagePath);
        byte[] bytes = IOUtils.toByteArray(is);
        is.close();
        replacement.put(bookmarkName, bytes);

        WordUtils.insertImgs(templatePath, targetPath, replacement);
    }
}
