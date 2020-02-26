package com.ccb.interview.jvm;

/**
 * 使用 -XX:PrintGCDetails查看GC日志
 */
public class SeeGCDetail {

    public static void main(String[] args) {
        byte[] bytes = new byte[50 * 1024 * 1024];  //参数中设置最大堆大小为10m，这里创建50m的byte数组
    }

}
