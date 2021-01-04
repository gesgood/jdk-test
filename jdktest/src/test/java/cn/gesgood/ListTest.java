package cn.gesgood;

import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.util.*;

public class ListTest {

    @Test
    public void testList2String() {
        List<String> list = new ArrayList<String>(3 );
        list.add("1");
        list.add("3");
        list.add("2");

        Assert.assertEquals("[1, 3, 2]", list.toString());

        ListIterator<String> listItr = list.listIterator(list.size() );
        Assert.assertFalse(listItr.hasNext());


        List<String> linklist = new LinkedList<String>();
        linklist.add("1");
        linklist.add("3");
        linklist.add("2");

        Assert.assertEquals("[1, 3, 2]", linklist.toString());
    }

    @Test
    public void testAdd2EmptyList() throws IOException, ClassNotFoundException {
        List<String> list = new ArrayList<String>();
        // size = max(1.5*size, size+1);
        // elementData.length = 10
        list.add("1");
        Assert.assertEquals(1, list.size());

        list = new ArrayList<String>();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(list);

        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(in);
        List<String> anoList = (List<String>) ois.readObject();

        // elementData.length = 1, not 10
        anoList.add("2");
        // elementData.length = 2
        anoList.add("3");
    }

    @Test
    public void testSubList() {
        List<String> pList = new ArrayList<String>(10);
        for (int i = 0; i < 10; i++) {
            pList.add("" + i);
        }

        // getSubList
        List<String> subList = pList.subList(2,3);
        System.out.println(subList);
        subList.add("bug");

        Assert.assertEquals("bug", pList.get(3));
        System.out.println(pList);
    }

    @Test
    public void testItr() {
        List<String> ll = new LinkedList<String>();
        ll.add("hello");
        ((LinkedList<String>) ll).addFirst("world");
        ll.add("java");

        Iterator<String> itr = ((LinkedList<String>) ll).descendingIterator();
        //        while (itr.hasNext()) {
//            System.out.println(itr.next());
//        }
        for (String s = itr.next(); itr.hasNext(); ) {
            System.out.println(s);
            System.out.println(itr.next());
        }
    }

    @Test(expected=ConcurrentModificationException.class)
    public void testAddAll() {
        final List<String> ll = new LinkedList<String>();
        String sayHi = "Hello all, this is a test for linked list.";
        for (String s : sayHi.split("[,.\\s]+")) {
            ll.add(s);
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i =0; i< 100; i++) {
                    ll.add(i+"");
                }
            }
        }).start();
        List<String> anoll = new LinkedList<String>();
//        anoll.addAll(ll);
        for (String s : ll) {
            anoll.add(s);
        }
        System.out.println(anoll.size());
    }
    
    @Test public void testTypeRef() {
    	List<String> stringList = new ArrayList<>();
    	stringList.add("A");
    	stringList.addAll(Arrays.<String>asList());
    	stringList.addAll(Arrays.asList());
    }
    
    @Test public void testPECS() {
    	List<? extends Number> list = new ArrayList<Long>();
//    	list.add(1L);
//    	list.add((Number)Long.valueOf(1));
    }

}
