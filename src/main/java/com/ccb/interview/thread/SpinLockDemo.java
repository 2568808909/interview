package com.ccb.interview.thread;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁：指尝试获取锁的线程不会阻塞，而是采取循环的方式去获取锁，这样的好处是减少线程上下文切换的消耗
 * 然而，如果长期获取不到锁一直空转会消耗cpu资源。
 */
public class SpinLockDemo {

    private AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void lock() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + " try to get lock");
        while (!atomicReference.compareAndSet(null, thread)) {  //当compareAndSet不成功时继续循环

        }
        System.out.println(thread.getName() + " get lock");
    }

    public void unlock() {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        System.out.println(thread.getName() + " release lock");
    }

    public static void main(String[] args) throws InterruptedException {
        SpinLockDemo lock = new SpinLockDemo();

        new Thread(() -> {
            lock.lock();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
        }, "AA").start();

        Thread.sleep(1000);
        new Thread(() -> {
            lock.lock();
            lock.unlock();
        }, "BB").start();
    }
}
