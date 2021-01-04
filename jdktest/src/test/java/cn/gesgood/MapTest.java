package cn.gesgood;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

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
    
    @Test public void testStream() {
    	Map<String, String> aMap = new HashMap<String, String>();
    	aMap.put("greet", "Hello");
    	// super
    	aMap.computeIfPresent("hello", (k, v) -> v.concat(" World"));
    	aMap.computeIfPresent("greet", (k, v) -> v.concat(" World"));
    	
    	System.out.println(aMap);
    }
    
    @Test public void testPECS() {
    	FakeMap<String, ?> m = new FakeMap<>();
    	m.compute("key", (a, b) -> b);
    }

    @Test public void testHash() {
    	int n = 0b10000000000000000000000000000000;
    	int m = 0b1000000000000000;
    	System.out.printf("%d, %d\n", n, m);
    	
    	int hn = Integer.valueOf(n).hashCode();
    	hn ^= (hn >>> 16);
    	System.out.println(Integer.toBinaryString(hn));
    	int hm = Integer.valueOf(m).hashCode();
    	hm ^= (hm >>> 16);
    	System.out.printf("%d, %d", hn, hm);
    }
    
    @Test public void testPut() {
    	Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    	for (int i = 0; i < 30; i++) {
    		map.put(i, i);
    	}
    	System.out.println(map.size());
    }
}

class FakeMap<K, V> {
	public V compute(K key, BiFunction<? super K, ? super V, ? extends V> biFunc) {
		return biFunc.apply(key, (V)null);
	}
		
}
