package cn.gesgood;

import cn.gesgood.util.HexUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StringTest {

    @Test
    public void testSplit() {
        String s = "99999";
        String[] arr = s.split("");
        assertEquals(5, arr.length);

        // in jdk1.6，arr.length=6
        // Assert.assertEquals(6, arr.length);
        // Assert.assertEquals("", arr[0]);
    }

    @Test
    public void testSplitByReg() {
        String s = "9|9|9";
        String[] arr = s.split("|");
        assertEquals(5, arr.length);

        // in jdk1.6，arr.length=6
        // Assert.assertEquals(6, arr.length);
        // Assert.assertEquals("", arr[0]);
    }

    @Test
    public void testCharSet() {
        String chineseChar = "汉";
        assertEquals(3, chineseChar.getBytes().length);
        System.out.println(HexUtil.toHexString(chineseChar.getBytes()));
    }

    @Test
    public void testToString() {
        String s = null;
        assertEquals("toString with for NULL is null", String.valueOf(s), "null");
    }

    @Test
    public void testCharDigit() {
        System.out.println(Character.digit('z', 36));
        System.out.println(Character.forDigit(35, 36));
        System.out.println(Character.getName('z'));
        System.out.println(Character.getName(65536));
    }

    @Test
    public void testTypeClass()  {
        assertTrue(Integer.TYPE == int.class);
    }

    @Test
    public void testStringBux() throws InterruptedException {
        StringBuffer sbuf = new StringBuffer();

        StringBuilder sbuiler = new StringBuilder();

        ExecutorService es = Executors.newFixedThreadPool(4);
        es.submit(new Appender(sbuf));
        es.submit(new Appender(sbuf));
        es.submit(new Appender(sbuiler));
        es.submit(new Appender(sbuiler));
        es.shutdown();

        es.awaitTermination(1, TimeUnit.DAYS);

        assertEquals(20000, sbuf.length());
        //assertEquals(20000, sbuiler.length());

        long memBefore = Runtime.getRuntime().maxMemory();
        System.out.println(memBefore / 1024);

        long l = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            String s = sbuf.toString();
        }
        System.out.printf("call toString 1K in %d ms\n", (System.currentTimeMillis() - l));
        long memMiddle =  Runtime.getRuntime().maxMemory();
        System.out.println(memMiddle - memBefore);

        l = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            String s = sbuiler.toString();
        }
        System.out.printf("call toString 1K in %d ms\n", (System.currentTimeMillis() - l));
        System.out.println(Runtime.getRuntime().maxMemory() - memMiddle);
    }

    private class Appender extends Thread {

        private final Appendable sb;

        public Appender(Appendable sb) {
            this.sb = sb;
        }

        @Override
        public void run() {
            long s = System.currentTimeMillis();
            for (int i = 0 ; i < 10000; i++) {
                try {
                    // Thread.sleep(i % 3);
                    sb.append(i%10 + "");
                } catch (IOException e) {
                    e.printStackTrace();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
                }
            }
            System.out.printf("append 10K in %d ms\n", (System.currentTimeMillis() - s));
        }
    }
}
