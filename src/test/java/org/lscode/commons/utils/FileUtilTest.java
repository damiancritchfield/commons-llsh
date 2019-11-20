package org.lscode.commons.utils;

import org.junit.Test;

public class FileUtilTest {

    @Test
    public void getFilePathInClassPath(){
        String filepath = FileUtil.getFilePathInClassPath("config/log4j.properties");
        System.out.println(filepath);
    }
}
