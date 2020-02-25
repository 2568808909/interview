package com.ccb.interview.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache {
    private volatile Map<String, Object> cache = new HashMap<>();
    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();


    public void put(String key, Object value) {
        rwLock.writeLock().lock();
        try {
            Thread thread = Thread.currentThread();
            System.out.println(thread.getName() + "\t 正在写入" + key);
            cache.put(key, value);
            System.out.println(thread.getName() + "\t 写入完成");
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public void get(String key) {
        rwLock.readLock().lock();
        try {
            Thread thread = Thread.currentThread();
            System.out.println(thread.getName() + "\t 正在读取");
            Object value = cache.get(key);
            System.out.println(thread.getName() + "\t 读取完成" + value);
        } finally {
            rwLock.readLock().unlock();
        }
    }
}

/**
 * @author ccb
 *
 * 多个线程同时读取一个资源类是没有问题的，也就是说读操作是可以共享的
 * 但是，如果一个线程想要修改资源类，则写操作应该是独占的，此时，其他线程不能进行任何读写操作
 *
 * 为了读写分离，达到以上读共享，写独占的效果，可以使用读写锁{@link java.util.concurrent.locks.ReentrantReadWriteLock}
 *
 * 小总结： 读-读可共存
 *          写-写不可共存
 *          读-写不可共存
 *
 *          写操作应该具有独占性，原子性，不可被打断
 */
public class ReadWriteLockDemo {

    public static void main(String[] args) throws InterruptedException {

        MyCache myCache = new MyCache();
        for (int i = 0; i < 5; i++) {
            int tmp = i;
            new Thread(() -> {
                myCache.put(String.valueOf(tmp), tmp);
            }, String.valueOf(i)).start();
        }

        for (int i = 0; i < 5; i++) {
            int tmp = i;
            new Thread(() -> {
                myCache.get(String.valueOf(tmp));
            }, String.valueOf(i)).start();
        }

    }
}
