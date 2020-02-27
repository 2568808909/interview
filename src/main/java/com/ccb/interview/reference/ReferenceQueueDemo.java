package com.ccb.interview.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * 虚引用：顾名思义形同虚设，虚引用和没引用是一样的，使用其get方法总是会返回null，
 * 每次GC回收垃圾时，也会将其回收。一般来说是结合ReferenceQueue引用队列来使用的。
 * 每当一个虚引用被回收前，都会仙贝放到引用队列中(软引用，虚引用也会)。其作用主要是跟踪对象的回收状态，
 * 回收前会调用对象的finalize()方法，做一些清除或者通知的工作(有种AOP后置通知的感觉)。
 */
public class ReferenceQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        //phantom();
        weak();
    }

    public static void weak() throws InterruptedException {
        Object object = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        WeakReference<Object> weakReference = new WeakReference<>(object, referenceQueue);
        System.out.println(object);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());
        System.out.println("============After GC===============");

        object = null;
        System.gc();
        Thread.sleep(500);
        System.out.println(object);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());
    }

    public static void phantom() throws InterruptedException {
        Object object = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        PhantomReference<Object> phantomReference = new PhantomReference<>(object, referenceQueue);
        System.out.println(object);
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());
        System.out.println("============After GC===============");
        object = null;
        System.gc();
        Thread.sleep(500);
        System.out.println(object);
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());
    }
}
