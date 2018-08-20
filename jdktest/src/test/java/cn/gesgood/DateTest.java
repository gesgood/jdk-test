package cn.gesgood;

import org.junit.Test;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTest {

    @Test
    public void testDate2String() {
        Date d = new Date();
        System.out.println(d);

        DateFormat df = DateFormat.getDateInstance();
        System.out.println(df.format(d));
    }

    @Test
    public void testDst() {
        // 卢森堡
        System.setProperty("user.timezone", "Europe/Luxembourg");

        Date now = new Date();
        System.out.println(now);

        Calendar c = Calendar.getInstance();
        c.setTime(now);
        // 东1区，夏令时
        System.out.println(c.get(Calendar.ZONE_OFFSET) / (1000*60*60));
        System.out.println(c.get(Calendar.DST_OFFSET) / (1000*60*60));

        c.add(Calendar.MONTH, 3);
        // 非夏令时
        System.out.println(c.getTime());
        System.out.println(c.get(Calendar.ZONE_OFFSET) / (1000*60*60));
        System.out.println(c.get(Calendar.DST_OFFSET) / (1000*60*60));
    }

    @Test
    public void testOffsetDate() {
        // 卢森堡
        System.setProperty("user.timezone", "Europe/Luxembourg");
        Calendar c = Calendar.getInstance();
        // 此时仍是夏令时，DST OFFSET=1，偏移2个小时，包含一个DST切换补偿的一个小时
        // 卢森堡在当地时间 2018年10月28日，03:00:00 时钟向后调整 1 小时 变为 2018年10月28日，02:00:00，结束夏令时
        c.set(2018, 9, 28, 1, 59, 59);
        System.out.println(c.getTime());
        System.out.println(c.get(Calendar.DST_OFFSET) / (1000*60*60));

        c.add(Calendar.HOUR, 2);
        System.out.println(c.getTime());
        System.out.println(c.get(Calendar.DST_OFFSET) / (1000*60*60));

        c = Calendar.getInstance();
        // 2点以后，认为时区已经切换，不再有补偿
        c.set(2018, 9, 28, 2, 0, 0);
        System.out.println(c.getTime());
        System.out.println(c.get(Calendar.DST_OFFSET) / (1000*60*60));

        c.add(Calendar.HOUR, 1);
        System.out.println(c.getTime());
        System.out.println(c.get(Calendar.DST_OFFSET) / (1000*60*60));

        c = Calendar.getInstance();
        // 进入夏令时，没有2点，直接到3点
        // 卢森堡在当地时间 2018年03月25日，02:00:00 时钟向前调整 1 小时 变为 2018年03月25日，03:00:00，开始夏令时
        c.set(2018, 2, 25, 2, 0, 0);
        System.out.println(c.getTime());
        System.out.println(c.get(Calendar.DST_OFFSET) / (1000*60*60));

        c.add(Calendar.HOUR, 1);
        System.out.println(c.getTime());
        System.out.println(c.get(Calendar.DST_OFFSET) / (1000*60*60));

        c.set(2018, 2, 25, 1, 59, 59);
        System.out.println(c.getTime());
        System.out.println(c.get(Calendar.DST_OFFSET) / (1000*60*60));

        c.add(Calendar.HOUR, 1);
        System.out.println(c.getTime());
        System.out.println(c.get(Calendar.DST_OFFSET) / (1000*60*60));
    }
}
