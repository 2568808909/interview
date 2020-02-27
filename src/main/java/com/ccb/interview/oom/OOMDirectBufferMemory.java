package com.ccb.interview.oom;

import java.nio.ByteBuffer;

/**
 * OutOfMemoryError: Direct buffer memory
 * 诱因：
 * 写NIO程序时使用ByteBuffer分配空间时，有两种方式：
 * 1.ByteBuffer.allocate(capacity) ，该方式分配的是JVM内存，属于GC管辖，但是由于需要拷贝，所以速度较慢
 * 2.ByteBuffer.allocateDirect(capacity) ，该方式使用native函数分配堆外内存，不属于GC管辖，不需要拷贝，所以速度较快
 *   通过一个存储在Java堆里的DirectByteBuffer对象作为这块内存的引用进行操作。
 *
 * 如果不断的分配本地内存，堆内存使用很少，JVM基本不需要GC，此时堆内存充足，但是本地内存基本用光，
 * 在此尝试分配本地内存时就会导致该错误
 */
public class OOMDirectBufferMemory {

    public static void main(String[] args) {
        System.out.println("配置的本地内存为：" + sun.misc.VM.maxDirectMemory() / 1024d / 1024d + "m");
        //配置参数为 -Xmx10m -Xms10m -XX:MaxDirectMemorySize=5m ，只配置了5m本地内存，但是要分配6m的空间，就会抛出错误
        ByteBuffer.allocateDirect(6 * 1024 * 1024);
    }
}
