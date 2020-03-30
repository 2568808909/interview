package com.ccb.interview.algorithm;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 * <p>
 * 获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 * <p>
 * 进阶:
 * <p>
 * 你是否可以在 O(1) 时间复杂度内完成这两种操作？
 */
//直接继承LinkHashMap实现起来更加简洁
class LRUCache extends LinkedHashMap<Integer, Integer> {

    private int capacity;

    public LRUCache(int capacity) {
        super(capacity,0.75F,true); //accessOrder要置为true
        this.capacity = capacity;
    }

    public int get(int key) {
        return getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }
}
//自己的渣实现
//class LRUCache {
//
//    private int capacity;
//
//    private LinkedHashMap<Integer, Integer> cache;
//
//    private int size;
//
//    public LRUCache(int capacity) {
//        this.capacity = capacity;
//        this.size = 0;
//        this.cache = new LinkedHashMap<>();
//    }
//
//    public int get(int key) {
//        Integer value = cache.get(key);
//        if (value == null) return -1;
//        cache.remove(key);
//        cache.put(key, value);
//        return value;
//    }
//
//    public void put(int key, int value) {
//        boolean contains = cache.containsKey(key);
//        if (size < capacity && !contains) {
//            size++;
//        } else if (contains) {
//            cache.remove(key);
//        } else {
//            Map.Entry<Integer, Integer> first = cache.entrySet().iterator().next();
//            cache.remove(first.getKey());
//        }
//        cache.put(key, value);
//    }
//}