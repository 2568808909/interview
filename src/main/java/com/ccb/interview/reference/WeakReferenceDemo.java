package com.ccb.interview.reference;

import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

/**
 * 弱引用：不论内存是否够，只要是弱引用，GC一律回收
 */
public class WeakReferenceDemo {

    public static void main(String[] args) throws InterruptedException {
        WeakReference<Object> weakReference=new WeakReference<>(new Object());
        System.out.println(weakReference.get());
        System.gc();
        System.out.println("==========After GC============");
        TimeUnit.SECONDS.sleep(2);
        System.out.println(weakReference.get());
    }
}
