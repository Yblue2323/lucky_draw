package com.yblue.lucky_draw.config;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolConfig {

    //估算平时的流量需要的线程数
    private static final int CORE_POOL_SIZE = 10;//核心线程数大小
    //(CPU数+1)
    private static final int MAXIMUM_POOL_SIZE = Runtime.getRuntime().availableProcessors() + 1;//线程池中允许的最大线程数
    private static final long KEEP_ALIVE_TIME = 1;//表示存活时间
    private static final TimeUnit UNIT = TimeUnit.MINUTES;//时间单位

    private static ThreadPoolExecutor instance;

    private static ThreadPoolExecutor getExecutor() {
        return new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME, UNIT,
                //阻塞队列
                // ArrayBlockingQueue ： 一个由数组结构组成的有界阻塞队列。
                // LinkedBlockingQueue ： 一个由链表结构组成的有界阻塞队列。
                // PriorityBlockingQueue ： 一个支持优先级排序的无界阻塞队列。
                // DelayQueue： 一个使用优先级队列实现的无界阻塞队列。
                // SynchronousQueue： 一个不存储元素的阻塞队列。
                // LinkedTransferQueue： 一个由链表结构组成的无界阻塞队列。
                // LinkedBlockingDeque： 一个由链表结构组成的双向阻塞队列。
                new ArrayBlockingQueue<>(10),
                //线程工厂
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        return new Thread(r);
                    }
                },
                new ThreadPoolExecutor.DiscardOldestPolicy());
        //拒绝策略
        //CallerRunsPolicy：由调用线程处理该任务
        //AbortPolicy：丢弃任务并抛出RejectedExecutionException异常 【 默认 】
        //DiscardPolicy：丢弃任务
        //DiscardOldestPolicy：丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）
    }


    public static ThreadPoolExecutor pool() {
        if (instance == null) {
            synchronized (ThreadPoolConfig.class) {
                if (instance == null) {
                    instance = getExecutor();
                }
            }
        }
        return instance;
    }
}
