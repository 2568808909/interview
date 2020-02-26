package com.ccb.interview.jvm;

public class HelloGC {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("HelloGC");
        Runtime runtime = Runtime.getRuntime();
        long totalMemory = runtime.totalMemory();
        long maxMemory = runtime.maxMemory();
        System.out.println(String.format("TOTAL_MEMORY(-Xms) %d 字节、%fMB", totalMemory, totalMemory / 1024d / 1024d));   //初始堆内存大小 内存的1/64
        System.out.println(String.format("MAX_MEMORY(-Xmx) %d 字节、%fMB", maxMemory, maxMemory / 1024d / 1024d));   //最大堆内存大小 内存的1/4
        //Thread.sleep(Integer.MAX_VALUE);
    }
}
