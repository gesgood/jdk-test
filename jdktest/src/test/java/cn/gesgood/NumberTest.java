package cn.gesgood;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class NumberTest {

    @Test
    public void testLong() {
        Long l1 = Long.valueOf(1);
        Long l2 = 1L;
        Long l3 = new Long(1);

        assertTrue(l1 == l2);
        assertTrue(l3 != l2);
        assertEquals(l3, l1);
    }

    @Test
    public void testBitCountInt() {
        // all bit = 1
        int i = -1;
        System.out.println(Integer.toBinaryString(i));
        i = i - ((i >>> 1) & 0x55555555);
        System.out.println(Integer.toBinaryString(i));
        i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
        System.out.println(Integer.toBinaryString(i));
        i = (i + (i >>> 4)) & 0x0f0f0f0f;
        System.out.println(Integer.toBinaryString(i));
        i = i + (i >>> 8);
        System.out.println(Integer.toBinaryString(i));
        i = i + (i >>> 16);
        System.out.println(Integer.toBinaryString(i));
        int bitCount = i & 0x3f;
        System.out.println(Integer.toBinaryString(bitCount));
        System.out.println(bitCount);
    }
}
