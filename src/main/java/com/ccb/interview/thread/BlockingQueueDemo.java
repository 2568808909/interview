package com.ccb.interview.thread;

/**
 * 阻塞队列：当队列为空时，从队列中获取元素会被阻塞，当队列满时，往里放元素会被阻塞
 * <p>
 * 常用的阻塞队列
 * {@link java.util.concurrent.ArrayBlockingQueue} 由数组实现的有界阻塞队列，创建时要指定大小
 * {@link java.util.concurrent.LinkedBlockingQueue} 由链表实现的有界阻塞队列(默认大小为Integer.MAX_VALUE 这样其实相当于无界，也可以在创建时指定容量)
 * {@link java.util.concurrent.SynchronousQueue} 不存储元素的阻塞队列，也就是单个元素的阻塞队列，往里放元素时就会阻塞，知道有线程拿元素为止
 * {@link java.util.concurrent.PriorityBlockingQueue} 使用优先队列实现的无界阻塞队列
 * {@link java.util.concurrent.DelayQueue} 使用优先队列实现的延迟无界队列
 * {@link java.util.concurrent.LinkedTransferQueue} 由链表实现的无界阻塞队列
 * <p>
 * 方法类型     抛出异常        特殊值     阻塞      超时
 * 插入          add(e)       offer(e)   put(e)    offer(e,time,unit)
 * 移除          remove()     poll()     take()    poll(time,unit)
 * 检查          element()    peek()     不可用     不可用
 *
 * 为什么要使用阻塞队列： 好处是，我们不需要关心什么时候要阻塞线程，什么时候需要唤醒线程，
 * 这些都由阻塞队列一手包办了
 */
public class BlockingQueueDemo {
}
