//package com.yblue.lucky_draw.config.aop;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.yblue.lucky_draw.util.HttpServletUtil;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.tuple.MutablePair;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.redisson.api.RLock;
//import org.redisson.api.RedissonClient;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.Map;
//import java.util.concurrent.TimeUnit;
//
//import static com.yblue.lucky_draw.util.HttpServleUtil.getHttpServletRequest;
//
///**
// * @author JiaXinMa
// * @description 防重复
// * @date 2022/3/18
// */
//@Slf4j
//@Aspect
//@Component
//@AllArgsConstructor
//public class DuplicateAspect {
//
//    private RedissonClient redissonClient;
//
//    //可以用来防重复提交
//    @Pointcut("@annotation(com.yblue.lucky_draw.config.annotation.NotDuplicate)")
//    public void duplicate() {
//    }
//
//    /**
//     * 环绕通知
//     * 目标方法的调用由环绕通知决定，即你可以决定是否调用目标方法，
//     * joinPoint.proceed()就是执行目标方法的代码 。
//     */
//    @Around("duplicate()")
//    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
//        Object object = null;
//        HttpServletRequest request = HttpServletUtil.getHttpServletRequest();
//        String token = request.getHeader("Authorization");
//        //解析token中获取用户id 拼内容
//        String body = HttpServletUtil.getBodyParam(request).getRight();
//
//        String url = request.getServletPath();
//        RLock lock = redissonClient.getLock("duplicateLock" + url + body);
//        boolean isLock = lock.tryLock(1, TimeUnit.SECONDS);
//        if (isLock) {
//            try {
//                object = joinPoint.proceed();
//            } finally {
//                lock.unlock();
//            }
//        } else {
//            HttpServletUtil.responseError(HttpServletUtil.getHttpServletResponse(), 500, "请不要重复插入数据！！！");
//        }
//        return object;
//    }
//
//
//}
