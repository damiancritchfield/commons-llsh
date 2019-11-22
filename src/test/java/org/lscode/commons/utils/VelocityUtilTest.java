package org.lscode.commons.utils;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class VelocityUtilTest {

    @Test
    public void generate(){
        Map<String, Object> map = new HashMap<>();
        map.put("name","刘德华");
        map.put("say",100);
        String str = VelocityUtils.generate(FileUtils.getFilePathInClassPath("template/hello.vm"), map);
        System.out.println(str);
    }
}
