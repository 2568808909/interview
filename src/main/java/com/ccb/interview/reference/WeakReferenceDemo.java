package com.ccb.interview.reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

/**
 * 弱引用：不论内存是否够，只要是弱引用，GC一律回收
 */
class Test {
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("final");
    }
}

public class WeakReferenceDemo {

    public static void main(String[] args) throws InterruptedException {
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        WeakHashMap<String,Test> map=new WeakHashMap<>();
        WeakReference<Test> weakReference = new WeakReference<>(new Test());
        map.put(new String("1"),new Test());
        System.out.println(weakReference.get());
        System.gc();
        TimeUnit.SECONDS.sleep(2);
        LinkedHashMap linkedHashMap=new LinkedHashMap();
        System.out.println("==========After GC============");
        System.out.println(weakReference.get());
        System.out.println(map.get("1"));

    }
}
