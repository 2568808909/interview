package com.ccb.interview.reference;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

public class WeakHashMapDemo {

    public static void main(String[] args) throws InterruptedException {
        hashMap();
        System.out.println("======================");
        weakHashMap();
    }

    public static void hashMap() throws InterruptedException {
        Map<Integer, String> map = new HashMap<>();
        Integer key = new Integer(1);
        String value = "HashMap";
        map.put(key, value);
        System.out.println(map);

        key = null;
        System.out.println(map);
        System.gc();
        TimeUnit.SECONDS.sleep(2);
        System.out.println(map);
    }

    /**
     * 如果使用Integer之类的会被放入常量池中的引用，要使用new才可以，因为常量池会长期保留引用，
     * 即使置空，还是有一个引用会指向常量池
     * @throws InterruptedException
     */
    public static void weakHashMap() throws InterruptedException {
        WeakHashMap<Integer, String> map = new WeakHashMap<>();
        Integer key = new Integer(2);
        String value = "weakHashMap";
        map.put(key, value);
        System.out.println(map);

        key = null;
        System.out.println(map);
        System.gc();
        TimeUnit.SECONDS.sleep(5);
        System.out.println(map);
    }
}
