package org.lscode.commons.utils;

import org.junit.Test;

import java.util.Date;

public class CommonDateUtilsTest {

    @Test
    public void testCommonMethods(){
        Date date = CommonDateUtils.dateParse("2017-12-30");
        System.out.println(CommonDateUtils.getYear(date));
        System.out.println(CommonDateUtils.getMonth(date));
        System.out.println(CommonDateUtils.getDay(date));
        System.out.println(CommonDateUtils.getYear(null));
        System.out.println(CommonDateUtils.getMonth(null));
        System.out.println(CommonDateUtils.getDay(null));
        Date date2 = CommonDateUtils.parse("2017-05-23", "yyyy-MM-dd");
        System.out.println(date2);
        String dateStr = CommonDateUtils.format(date2, "yyyy-MM-dd 0505");
        System.out.println(dateStr);
    }

}
