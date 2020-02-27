package com.ccb.interview.reference;

import java.lang.ref.SoftReference;
import java.util.concurrent.TimeUnit;

/**
 * 软引用：当内存足够时，GC不会回收，当内存不够时，则回收
 *
 * 使用场景：
 *      做缓存：假如要从文件中读取一些数据，如果每次都从文件中读，每次都要磁盘IO，则性能不高，
 *              但是，将这些文件中的数据总放在内存中又会占用空间。此时就可以使用软引用，内存
 *              不足时，这些软应用会被全部回收，从而避免OOM。(Mybatis源码的缓存部分大量使用了软引用)
 */
public class SoftReferenceDemo {

    public static void memoryEnough() throws InterruptedException {
        Object object = new Object();
        SoftReference<Object> softReference = new SoftReference<>(object);
        System.out.println(object);
        System.out.println(softReference.get());

        object = null;
        System.out.println("==========After GC============");
        System.gc();

        TimeUnit.SECONDS.sleep(3);

        System.out.println(object);
        System.out.println(softReference.get());
    }

    /**
     * 模拟内存不足的情况，-Xmx5m -Xms5m -XX:+PrintGCDetails
     */
    public static void memoryNotEnough() {
        Object object = new Object();
        SoftReference<Object> softReference = new SoftReference<>(object);

        object = null;
        try {
            byte[] bytes = new byte[30 * 1024 * 1024];
        } catch (Throwable e) {

        } finally {
            System.out.println(object);
            System.out.println(softReference.get());
        }

    }

    public static void main(String[] args) throws InterruptedException {
        //memoryEnough();
        memoryNotEnough();
    }
}
