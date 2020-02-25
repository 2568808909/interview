package com.ccb.interview.thread;

import com.ccb.interview.myenum.CountryEnum;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch count = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"被秦所灭");
                count.countDown();
            }, CountryEnum.get(i)).start();
        }
        count.await();
        System.out.println("大秦统一华夏");
    }
}
