package com.ccb.interview.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 死锁：两个或两个以上线程争抢资源，导致互相等待的状况，若无外力干扰，程序就无法继续运行。
 */
class DeadLock {
    private Lock lockA = new ReentrantLock();
    private Lock lockB = new ReentrantLock();

    public void getA() {
        lockA.lock();
        try {
            System.out.println(Thread.currentThread().getName()+" get lockA");
        } finally {
            lockA.unlock();
        }
    }

    public void getB() {
        lockB.lock();
        try {
            System.out.println(Thread.currentThread().getName()+" get lockB");
        } finally {
            lockB.unlock();
        }
    }
}

public class DeadLockDemo {

    public static void main(String[] args) {
        DeadLock lock=new DeadLock();
        new Thread(() -> {
            lock.getA();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.getB();
        }, "A").start();

        new Thread(() -> {
            lock.getB();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.getA();
        }, "B").start();
    }
}
