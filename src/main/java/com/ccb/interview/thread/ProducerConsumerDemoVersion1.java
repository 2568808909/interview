package com.ccb.interview.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData {
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void incr() throws InterruptedException {
        Thread thread = Thread.currentThread();
        lock.lock();
        try {
            while (number != 0) {
                condition.await();
            }
            number++;
            System.out.println(thread.getName()+"\t "+number);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void decr() throws InterruptedException {
        Thread thread = Thread.currentThread();
        lock.lock();
        try {
            while (number == 0) {
                condition.await();
            }
            number--;
            System.out.println(thread.getName()+"\t "+number);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

}

/**
 * 需求:一个变量初始值为0，使用两个线程对其交替操作，加1一次减1一次
 * 1.   线程  操作  资源类
 * 2.   判断  干活  通知
 * 3.   防止虚假唤醒 : 多线程条件判断应该用while代替if，唤醒后再判断一次，不符合工作条件继续阻塞
 */
public class ProducerConsumerDemoVersion1 {

    public static void main(String[] args) {
        ShareData shareData=new ShareData();

        new Thread(() -> {
            try {
                for(int i=0;i<5;i++){
                    shareData.incr();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {

            }
        }, "AA").start();

        new Thread(() -> {
            try {
                for(int i=0;i<5;i++){
                    shareData.decr();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {

            }
        }, "BB").start();


    }
}
