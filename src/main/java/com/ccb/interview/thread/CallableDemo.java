package com.ccb.interview.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 创建线程的三种方式：
 * 1.继承Thread类，重写run方法
 * 2.实现Runnable接口，实现run方法
 * 3.实现Callable接口，实现call方法
 *
 * Callable接口启动的线程可以抛出异常，并有返回值。因FutureTask继承了Runnable接口，
 * 创建时刻传入Callable，所以可以将FutureTask传入Thread来启动线程。其返回值可以通过
 * FutureTask的get方法获取，不过该方法会阻塞至线程结束有返回值为止。也可以通过调用
 * FutureTask的isDone方法轮询线程是否执行完成。
 */
class MyCallable implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        return 1024;
    }
}

public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyCallable());
        new Thread(futureTask,"call").start();


        System.out.println(futureTask.get());
    }
}
