package cn.gesgood;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class MapTest {
    @Test
    public void testInit() {
        Map<String, String> aMap = new HashMap<String, String>();
        System.out.println(aMap.isEmpty());
    }

    @Test
    public void testBadMap() {
        Map<Map<?, String>, String> aMap = new HashMap<Map<?, String>, String>();
        aMap.put(aMap, "can I");

        Map<String, String> keyMap = new HashMap<String, String>(1);
        aMap.put(keyMap, "hello world");

        System.out.println(aMap);
        System.out.println("you got: " + aMap.get(aMap));
    }

}
