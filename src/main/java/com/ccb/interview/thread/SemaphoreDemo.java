package com.ccb.interview.thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 信号量，多资源控制访问量，资源量可伸缩
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        Semaphore semaphore=new Semaphore(3);
        for (int i = 0; i < 6; i++) {
            int tmp=i;
            new Thread(() -> {
                try{
                    semaphore.acquire();
                    System.out.println(tmp+"抢到车位");
                    TimeUnit.SECONDS.sleep(3);
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    System.out.println(tmp+"离开停车场");
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }
    }
}
