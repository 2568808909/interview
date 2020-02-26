package com.ccb.interview.thread;

import java.util.concurrent.*;

/**
 * 线程池
 * 线程池的工作主要为控制线程运行的数量，将任务放入队列当中，有空闲的资源便启动任务，如果任务数量超过最大的数量便排队等候
 * <p>
 * 架构：
 * Java中的线程池是通过Executor框架实现的，该框架中使用到了Executor，Executors,ExecutorService,ThreadPoolExecutor(线程池的底层就是这个类)
 * <p>
 * 作用(特点)：1.线程复用 2.控制最大并发数 2.管理线程
 * <p>
 * 通过复用线程降低资源消耗，避免对象的频繁创建于回收
 * 提高响应效率，任务一到即可运行，不用等待线程创建
 * 线程池可以提高线程的可管理性，可以进行同一的分配，调优和监控
 * <p>
 * 常用线程池：
 * FixedThreadPool             固定大小的线程池
 * SingleThreadPoolExecutor    单任务线程池
 * CachedThreadPool            带缓存，可弹性扩展的线程池
 */
public class ThreadPoolDemo {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = new ThreadPoolExecutor(
                2,
                5,
                30L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());

        try {
            for (int i = 0; i < 50; i++) {
                executor.execute(() -> System.out.println(Thread.currentThread().getName() + "处理业务"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            executor.shutdown();
        }
    }
}
