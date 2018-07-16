package cn.gesgood;

import org.junit.Assert;
import org.junit.Test;

public class StringTest {

    @Test
    public void testSplit() {
        String s = "99999";
        String[] arr = s.split("");
        Assert.assertEquals(5, arr.length);

        // for jdk1.6，arr.length=6
//        Assert.assertEquals(6, arr.length);
//        Assert.assertEquals("", arr[0]);
    }
}
