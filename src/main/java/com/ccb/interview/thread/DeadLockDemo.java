package com.ccb.interview.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 死锁：两个或两个以上线程争抢资源，导致互相等待的状况，若无外力干扰，程序就无法继续运行。
 */
class DeadLock implements Runnable{
    private String lockA;
    private String lockB;

    public DeadLock(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName()+" get "+lockA);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName()+" get "+lockB);
            }
        }
    }
}

public class DeadLockDemo {

    public static void main(String[] args) {
        String lockA="lockA";
        String lockB="lockB";
        new Thread(new DeadLock(lockA,lockB), "A").start();
        new Thread(new DeadLock(lockB,lockA), "B").start();

        /**
         * 如何发现死锁： 使用jps命令查看进程号，再使用 jstack [进程号] 查看该进程的线程状态
         */
    }
}
