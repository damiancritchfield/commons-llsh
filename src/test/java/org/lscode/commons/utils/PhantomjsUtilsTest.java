package org.lscode.commons.utils;

import org.junit.Test;

public class PhantomjsUtilsTest {

    @Test
    public void htmlToMultiPagePdf() {
        String path = PhantomjsUtils.htmlToMultiPagePdf("https://www.baidu.com/");
        System.out.println(path);
    }
}
