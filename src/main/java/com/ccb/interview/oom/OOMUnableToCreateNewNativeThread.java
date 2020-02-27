package com.ccb.interview.oom;

import java.util.concurrent.TimeUnit;

/**
 * OutOfMemoryError: unable to create new native thread
 *
 * 高并发请求服务器时，经常出现如下异常：java.lang.OutOfMemoryError: unable to create new native thread
 * 该异常与对应平台有关
 *
 * 导致原因：
 * 1.应用进程创建太多线程，超过了系统承载的极限
 * 2.服务器不允许一个进程创建过多的线程，linux默认允许一个进程创建1024个线程
 *
 * 解决：
 * 1.想办法降低应用程序创建的线程数量，分析应用是否需要创建如此多的线程，如果不是，建议降到最低
 * 2.对于有的应用，确实需要很多线程，如果超过linux系统允许范围1024，可以通过修改linux服务器配置，扩大linux允许进程创建的线程数量
 */
public class OOMUnableToCreateNewNativeThread {
    public static void main(String[] args) {
        for (int i = 0; ; i++) {
            System.out.println("i=" + i);
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
