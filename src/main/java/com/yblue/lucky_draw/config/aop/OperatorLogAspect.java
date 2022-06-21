//package com.yblue.lucky_draw.config.aop;
//
//
//import com.alibaba.fastjson.JSON;
//import com.yblue.lucky_draw.domain.OperatorLog;
//import com.yblue.lucky_draw.service.IOperatorLogService;
//import com.yblue.lucky_draw.util.HttpServletUtil;
//import com.yblue.lucky_draw.util.ThreadLocalUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.ServletRequest;
//import javax.servlet.http.HttpServletRequest;
//import java.net.InetAddress;
//import java.net.UnknownHostException;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//@Aspect
//@Component
//@Slf4j
//public class OperatorLogAspect {
//
//
//    @Autowired
//    private IOperatorLogService operatorLogService;
//
//    //定义切点 @Pointcut
//    //在注解的位置切入代码
//    @Pointcut("@annotation(com.yblue.lucky_draw.config.annotation.OperatorLog)")
//    public void logPointCut() {
//    }
//
//
//    @Around("logPointCut()")
//    public Object saveSysLog(ProceedingJoinPoint joinPoint) throws Exception {
//        HttpServletRequest request = HttpServletUtil.getHttpServletRequest();
//
//        //IP地址
//        String ip =HttpServletUtil.getHost(request);
//        //方法
//        String method = joinPoint.getSignature().getName();
//        //请求地址
//        String requestUrl = request.getRequestURL().toString();
//        //入参
//        String requestParam = this.getReqParam(joinPoint, request);
//        //请求类型
//        String requestType = request.getMethod();
//
//        long start = System.currentTimeMillis();
//
//        Object resp = null;
//        try {
//            resp = joinPoint.proceed();
//        } catch (Throwable throwable) {
//            log.error("[{}] invoke failed.", method, throwable);
//            throw (Exception) throwable;
//        } finally {
//            long execTime = System.currentTimeMillis() - start;
//
//            OperatorLog operatorLog = new OperatorLog();
//            operatorLog.setIp(ip);
//            operatorLog.setMethod(method);
//            operatorLog.setRequestUrl(requestUrl);
//            operatorLog.setRequestParam(requestParam);
//            operatorLog.setRequestType(requestType);
//            operatorLog.setCostTime(execTime);
//            operatorLog.setResponseData(JSON.toJSONString(resp));
//            operatorLog.setUserId(Long.valueOf(ThreadLocalUtil.get("userId").toString()));
//
//            operatorLogService.save(operatorLog);
//        }
//        return resp;
//    }
//
//
//    private String getReqParam(ProceedingJoinPoint joinPoint, HttpServletRequest request) {
//        String param = "";
//        if (request.getMethod().equalsIgnoreCase(RequestMethod.GET.name())) {
//            Map<String, String[]> parameterMap = request.getParameterMap();
//            Map<String, String> paramMap = new HashMap<>();
//            parameterMap.forEach((key, value) -> {
//                paramMap.put(key, Arrays.stream(value).collect(Collectors.joining(",")));
//            });
//            param = JSON.toJSONString(paramMap);
//        } else if (request.getMethod().equalsIgnoreCase(RequestMethod.POST.name())) {
//            Object[] args = joinPoint.getArgs();
//            StringBuffer sb = new StringBuffer();
//
//            for (Object object : args) {
//                if (object != null) {
//                    sb.append(object.toString().replace("=", ":"));
//                }
//            }
//
//            if (sb.length() == 0) {
//                sb.append("{}");
//            }
//            param = sb.toString();
//        }
//        return param;
//
//    }
//
//
//
//}
//
