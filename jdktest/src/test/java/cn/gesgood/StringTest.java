package cn.gesgood;

import cn.gesgood.util.HexUtil;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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


}
