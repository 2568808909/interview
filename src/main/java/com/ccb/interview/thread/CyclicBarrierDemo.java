package com.ccb.interview.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 如果说CountDownLatch是在做减法，CyclicBarrier就是在做加法，
 * 没加到这个数值前，先来的线程都要阻塞，知道加到该数值后，所有线程再唤醒运行
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        //推荐构造方法为 数目+一个回调方法
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> System.out.println("集齐7颗龙珠召唤神龙"));
        for (int i = 1; i <= 7; i++) {
            int tmp = i;
            new Thread(() -> {
                System.out.println("获得" + tmp + "星龙珠");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(tmp)).start();
        }
    }
}
