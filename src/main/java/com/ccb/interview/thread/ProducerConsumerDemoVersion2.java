package com.ccb.interview.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 *使用阻塞队列实现生产者消费者模式
 */
class MyResource {
    private volatile boolean flag = true;
    private BlockingQueue<String> blockingQueue;

    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void produce() throws InterruptedException {
        Thread thread = Thread.currentThread();
        boolean isSuccess;
        while (flag) {
            isSuccess = blockingQueue.offer("A", 2L, TimeUnit.SECONDS);
            if (isSuccess) {
                System.out.println(thread.getName() + "\t生产成功");
            } else {
                System.out.println(thread.getName() + "\t生产失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println("生产者关闭");
    }

    public void consume() throws InterruptedException {
        Thread thread = Thread.currentThread();
        String result;
        while (flag) {
            result = blockingQueue.poll(2L, TimeUnit.SECONDS);
            if (result != null) {
                System.out.println(thread.getName() + "\t消费" + result + "成功");
            } else {
                System.out.println(thread.getName() + "\t消费失败");
                flag = false;
            }
        }
        System.out.println("消费者关闭");
    }

    public void stop() {
        flag = false;
    }
}

public class ProducerConsumerDemoVersion2 {

    public static void main(String[] args) throws InterruptedException {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<>(3));
        new Thread(() -> {
            try {
                myResource.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();

        new Thread(() -> {
            try {
                myResource.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B").start();
        TimeUnit.SECONDS.sleep(5);
        myResource.stop();
    }
}
