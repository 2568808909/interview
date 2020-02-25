package com.ccb.interview.thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

/**
 * 非线程安全集合
 * ArrayList
 */
public class ContainerNotSafeDemo {

    public static void main(String[] args) throws InterruptedException {

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8)+"T"+Thread.currentThread().getName());
                System.out.println(Thread.currentThread().getName()+" "+list);
            }, String.valueOf(i)).start();
        }
        //java.util.ConcurrentModificationException
        /**
         * 1.故障现象：
         * 1.1 java.util.ConcurrentModificationException
         * 1.2 部分元素为null
         *
         * 2.导致原因：
         * 2.1  ConcurrentModificationException：这里的异常是输出list时触发的，在使用sout对list进行输出时，会使用list的迭代器对list进行遍历。
         *      但在此时某线程还在向list中添加元素，就触发了fail-fast机制。
         * 2.2  部分元素为null:
         *
         * 3.解决方案：
         * 3.1 Vector 几乎所有方法都是用synchronized
         * 3.2 Conllections.synchronizedList(new ArrayList) 相当于装饰器模式
         *     给ArrayList做了一层包装，返回一个SynchronizedList，其提供的方法
         *     都是想Vector一样用synchronized上锁的，在此基础上调用ArrayList的方法
         *3.3 CopyOnWriteArrayList：运用了写时复制的机制
         *
         * 4.优化建议：
         */


    }
}
