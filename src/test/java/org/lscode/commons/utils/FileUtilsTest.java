package org.lscode.commons.utils;

import org.junit.Test;

public class FileUtilsTest {

    @Test
    public void getFilePathInClassPath(){
        String filepath = FileUtils.getFilePathInClassPath("config/log4j.properties");
        System.out.println(filepath);
    }
}
