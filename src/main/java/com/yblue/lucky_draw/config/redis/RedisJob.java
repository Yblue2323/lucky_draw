//package com.yblue.lucky_draw.config.redis;
//
//
//import lombok.extern.slf4j.Slf4j;
//import org.redisson.api.RLock;
//import org.redisson.api.RedissonClient;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.concurrent.TimeUnit;
//
///**
// * 使用Redission版本
// */
//@Component
//@Slf4j
//public class RedisJob {
//
//    @Autowired
//    private RedissonClient redissonClient;
//
//
//    //    @Scheduled(cron = "0/10 * * * * ?")
//    public void job() throws InterruptedException {
//        // 创建锁对象
//        RLock lock = redissonClient.getLock("lock");
//        // 获取锁,设置自动失效时间为50s
//        boolean isLock = lock.tryLock(50, TimeUnit.SECONDS);
//        // 判断是否获取锁
//        if (!isLock) {
//            // 获取失败
//            log.info("获取锁失败，停止定时任务");
//            return;
//        }
//        try {
//            // 执行业务
//            log.info("获取锁成功，执行定时任务。");
//            // 模拟任务耗时
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            log.error("任务执行异常", e);
//        } finally {
//            // 释放锁
//            lock.unlock();
//        }
//    }
//
//}
