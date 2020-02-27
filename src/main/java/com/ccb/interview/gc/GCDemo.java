package com.ccb.interview.gc;

import java.util.Random;

/**
 * GC日志参数说明：
 * DefNew : Default New Generation
 * Tenured : Old
 * ParNew : Parallel New Generation
 * PSYoungGen : Parallel Scavenge
 * ParOldGen : Parallel Old Generation
 *
 * 1. -Xmx10m -Xms10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseSerialGC   (DefNew+Tenured)
 *      开启UseSerialGC后，会使用Serial(新生代)+SerialOld(老年代)的收集器组合，在新生代使用复制算法，老年代使用标记整理算法
 *      串行化收集时组古老，最稳定以及效率高的收集器，只用一个县城去回收，但其在回收垃圾时可能会产生较长的停顿(stop-the-world)
 *
 * 2.-Xmx10m -Xms10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParNewGC    (ParNew+Tenured)
 *      开启UseSerialGC后，会使用ParNew(新生代)+SerialOld(老年代)的收集器组合，在新生代使用复制算法，老年代使用标记整理算法
 *      ParNew是Serial的多线程版本，最常见的应用场景是配合老年代的CMS GC工作，其余行为和Serial完全一致，收集过程中同样要stop-the-world
 *      它是很多Java虚拟机在Server模式下的新生代垃圾收集器。可以使用 -XX:ParallelGCThreads来设置GC线程数量
 *
 *3.-Xmx10m -Xms10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParallelGC(默认使用)/-XX:+UseParallelOldGC    (ParNew+Tenured)
 *      开启UsePSYoungGC后，会使用Parallel Scavenge(新生代)+Parallel Old (老年代)的收集器组合.相当于串行化收集器在新生代和老年代的并行化
 *      Parallel Scavenge与ParNew相比，其更关注吞吐量，吞吐量=运行用户代码时间/(运行用户代码时间+垃圾手机时间)，吞吐量越高代表对CPU的利用越高效，
 *      并且，它拥有一种自适应调整策略，虚拟机会根据当前系统运行情况监控当前信息，动态调整这些参数提供合适的提顿时间(-XX:MaxGCPauseMillis)或最大吞吐量
 *
 */
public class GCDemo {
    public static void main(String[] args) {
        String str = "ccb";
        while (true) {
            str += str + new Random(2222222).nextInt() + new Random(11111111).nextInt();
            str.intern();
        }
    }
}
