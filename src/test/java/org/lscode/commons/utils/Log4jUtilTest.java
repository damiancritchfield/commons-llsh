package org.lscode.commons.utils;

import org.apache.log4j.Logger;
import org.junit.Test;

public class Log4jUtilTest {

    @Test
    public void getLogger(){
        Logger logger = Log4jUtil.getLogger(Log4jUtilTest.class);
        logger.info("测试日志输出");
    }
}
