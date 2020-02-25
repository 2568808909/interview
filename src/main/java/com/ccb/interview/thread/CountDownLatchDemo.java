package com.ccb.interview.thread;

import com.ccb.interview.myenum.CountryEnum;

import java.util.Optional;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch count = new CountDownLatch(6);
        for (int i = 1; i <= 7; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "被秦所灭");
                count.countDown();
            }, Optional.ofNullable(CountryEnum.get(i)).orElse("不知名的小国")).start();
        }
        count.await();
        System.out.println("大秦统一华夏");
    }
}
