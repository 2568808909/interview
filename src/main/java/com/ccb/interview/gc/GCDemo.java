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
 *      开启UseParNewGC后，会使用ParNew(新生代)+SerialOld(老年代)的收集器组合，在新生代使用复制算法，老年代使用标记整理算法
 *      ParNew是Serial的多线程版本，最常见的应用场景是配合老年代的CMS GC工作，其余行为和Serial完全一致，收集过程中同样要stop-the-world
 *      它是很多Java虚拟机在Server模式下的新生代垃圾收集器。可以使用 -XX:ParallelGCThreads来设置GC线程数量
 *
 *3.-Xmx10m -Xms10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParallelGC(默认使用)/-XX:+UseParallelOldGC    (PSYoung+ParOldGen)
 *      开启UsePSYoungGC后，会使用Parallel Scavenge(新生代)+Parallel Old (老年代)的收集器组合.相当于串行化收集器在新生代和老年代的并行化
 *      Parallel Scavenge与ParNew相比，其更关注吞吐量，吞吐量=运行用户代码时间/(运行用户代码时间+垃圾手机时间)，吞吐量越高代表对CPU的利用越高效，
 *      并且，它拥有一种自适应调整策略，虚拟机会根据当前系统运行情况监控当前信息，动态调整这些参数提供合适的停顿时间(-XX:MaxGCPauseMillis)或最大吞吐量
 *
 *4.-Xmx10m -Xms10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseConcMarkSweepGC    (ParNew+CMS+SerialOld)
 *      CMS(Concurrent Mark Sweep并发标记清除)收集器，是一种以停顿时间最短为目标的垃圾收集器。适合互联网B/S系统的服务器上。
 *      CMS适合堆内存大，CPU核数多的服务端应用，是G1出现前的大型应用首选收集器。
 *      -XX:+UseConcMarkSweepGC会开启ParNew(Young)+CMS(Tenured)+SerialOld 其中SerialOld是为了在CMS收集出现异常后，作为替补进行收集。
 *      CMS收集分为4个过程：
 *          1).初始标记(CMS Initial Mark)：标记一下GC Roots能直接关联的对象，速度很快，仍然需要停止所有的工作线程
 *          2).并发标记(CMS Concurrent Mark) ：进行GC Roots跟踪，和用户线程一起工作，不需要暂停线程。主要标记过程，标记所有对象
 *          3).重新标记(CMS Remark)：在正式清除前，修正在并发标记期间，因用户程序继续运行而导致标记产生变动的一部分对象的标记记录，需要停止所有工作线程
 *          4).并发清除(CMS Concurrent Sweep)：清除GC Roots不可达对象，不需要停止用户线程
 *          由于2和4两个最耗时的阶段可以并发与用户线程一起工作，总体上来看，CMS和用户线程是一起并发运行的。
 *      优点：并发收集降低停顿
 *      缺点：
 *          1).由于并发进行，所以CMS收集过程中会增加CPU负担，同时会增加对堆内存的占用，所以CMS要在老年代空间使用完之前完成收集，不然CMS就会收集失败，并处罚
 *          担保机制——使用SerialOld进行垃圾回收(以Stop-the-world方式进行一次GC，造成大停顿)。
 *          2).由于CMS是基于标记清除的方式进行垃圾回收，所以必然会产生大量内存碎片，老年代空间也会因此被耗尽，最后不得不启动担保机制对内存进行压缩，
 *          对于这种情况可以通过配置-XX:CMSFullGCBeforeCompaction(默认为0，每次都进行内存整理)，来指定多少次CMS收集后会进行一次FullGC压缩内存
 *
 *5.-Xmx10m -Xms10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseG1GC    (G1)
 *      G1之前的收集器特点：
 *          1).新生代和老年代是各自独立且连续的内存块
 *          2).新生代分为eden+S0+S1，并使用复制算法
 *          3).老年代收集必须扫描整个老年代区域
 *          4).都是以尽可能少而快速的执行GC为目的
 *      G1
 *
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
