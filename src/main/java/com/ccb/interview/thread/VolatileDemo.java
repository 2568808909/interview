package com.ccb.interview.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

class MyData {
     int number = 0;

    AtomicInteger atomicInteger = new AtomicInteger();

    public void addTo60() {
        this.number = 60;
    }

    //此时number前已经被volatile修饰
    public void plus() {
        number++;
    }

    public void addAtomic() {
        atomicInteger.incrementAndGet();
    }
}

/**
 * 验证volatile的可见性
 * 1.1 如果没有加volatile修饰number，则对number的修改对其他线程不可见
 * 1.2 添加volatile可以解决可见性问题
 * <p>
 * 验证volatile不保证原子性
 * 2.1 原子性是什么意思？
 * 不可分割，完整性，执行某个具体业务时，中间不能被分隔，需要完整执行，所有操作要么全部成功，要么全部失败
 * 2.2 volatile不保证原子性案例
 */
public class VolatileDemo {

    public static void main(String[] args) throws InterruptedException {
        MyData myData = new MyData();
        CountDownLatch count = new CountDownLatch(20);
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++)
                    myData.plus();
                count.countDown();
            }, String.valueOf(i)).start();
        }
        //如果活跃线程>2(main+GC 两个线程)就继续等待
//        while (Thread.activeCount() > 2) {
//            Thread.yield();
//        }
        count.await();
        //
        System.out.println(myData.number);
    }

    //volatile可以保证可见性，可以及时通知其他线程主内存内的变量已经被修改
    private static void seeOKByVolatile() {
        MyData data = new MyData();
        new Thread(() -> {
            System.out.println("AAA start....");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            data.addTo60();
            System.out.println("AAA end ... number=" + data.number);
        }, "AAA").start();

        while (data.number == 0) {
            //如果number一直为0(没有接收到变化)，则一直空转，main线程不结束
        }
        //如果不用volatile修饰number，则线程AAA对data变量的修改对main线程不可见，也就跳不出循环
        System.out.println("main end... number=" + data.number);
    }
}
