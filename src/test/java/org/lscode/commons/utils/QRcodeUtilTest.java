package org.lscode.commons.utils;

import org.junit.Test;

public class QRcodeUtilTest {

    @Test
    public void generateQRcode(){
        String path = QRcodeUtil.generateQRcode("qrcode-text-content", "F:\\my_temp", 100, 100);
        System.out.println(path);
    }

}
