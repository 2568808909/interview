package com.ccb.interview.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * synchronized和ReentrantLock的区别：
 * 1.原子构成：
 * 1.1 synchronized是JVM层面上的概念，基于底层的monitor对象实现，notify和wait也是依赖于monitor实现，
 * 所以这两个方法只能在同步代码块中执行。
 * 1.2 ReentrantLock是api层面的实现的锁
 * <p>
 * 2.使用方法：
 * 2.1 synchronized使用时，会自动释放锁(即使异常退出) 编译字节码后可以看见monitorenter指令获取锁，
 * 然后有monitorexit(可能有两条，保证锁释放)自动与其配对。
 * 2.2 使用JUC的lock需要手动调用unlock()来释放(配合try finally完成)，否则会出现死锁现象
 * <p>
 * 3.是否公平
 * 3.1 synchronized是非公平锁
 * 3.2 ReentrantLock两种都可以实现，只需要在构造方法传入不同的布尔值既可
 * <p>
 * 4.是否可中断
 * 4.1 synchronized不可中断，除非抛出异常
 * 4.2 可中断 : 1).设置超时时间 {@code tryLock(Long timeout,TimeUnit unit)}
 * 2).代码块中lockInterruptibly()，再调用interrupt()方法可中断
 * <p>
 * 5.锁定多个条件Condition
 * 5.1 synchronized没有
 * 5.2 ReentrantLock可以做到分组唤醒线程，也可以精确唤醒，而不是像synchronized那样只能随机唤醒一个线程或者全部唤醒
 * <p>
 * ===============================================================
 * <p>
 * 需求：多线程之间按顺序调用，现在有三个线程，
 * 第一个线程打印AA 5次，第二个打印BB 10次 第三个线程打印CC 15次
 * .......
 * 重复十次
 */
class ShareResource {
    private int number = 1;
    private Lock lock = new ReentrantLock();
    private Condition a = lock.newCondition();
    private Condition b = lock.newCondition();
    private Condition c = lock.newCondition();

    public void print(String print, int time) throws InterruptedException {
        lock.lock();
        try {
            checkAwait(time);
            for (int i = 0; i < time; i++) {
                System.out.println(print + " " + (i + 1));
            }
            signalNext();
        } finally {
            lock.unlock();
        }
    }

    private void checkAwait(int time) throws InterruptedException {
        while (time == 5 && number != 1) {
            a.await();
        }
        while (time == 10 && number != 2) {
            b.await();
        }
        while (time == 15 && number != 3) {
            c.await();
        }
    }

    private void signalNext() {
        if (number == 1) {
            number = 2;
            b.signal();
        } else if (number == 2) {
            number = 3;
            c.signal();
        } else {
            number = 1;
            a.signal();
        }
    }
}

public class SyncAndReentrantLockDemo {
    public static void main(String[] args) {
        ShareResource resource = new ShareResource();

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    resource.print(Thread.currentThread().getName(), 5);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    resource.print(Thread.currentThread().getName(), 10);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B").start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    resource.print(Thread.currentThread().getName(), 15);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "C").start();
    }
}
