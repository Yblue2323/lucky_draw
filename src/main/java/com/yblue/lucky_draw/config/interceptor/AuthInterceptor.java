package com.yblue.lucky_draw.config.interceptor;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.yblue.lucky_draw.config.JwtProperties;
import com.yblue.lucky_draw.util.JwtUtils;
import com.yblue.lucky_draw.vo.Payload;
import com.yblue.lucky_draw.vo.UserInfo;
import com.yblue.lucky_draw.util.HttpServletUtil;
import com.yblue.lucky_draw.util.ResultEnum;
import com.yblue.lucky_draw.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProperties jwtProperties;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //表示接受任意域名的请求,也可以指定域名
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("origin"));

        //该字段可选，是个布尔值，表示是否可以携带cookie
        response.setHeader("Access-Control-Allow-Credentials", "true");

        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS");

        response.setHeader("Access-Control-Allow-Headers", "*");

        //预请求,这里可以不加，但是其他语言开发的话记得处理options请求
        // 如:异步请求的时候前端发两次请求，其中一次是测试通不通
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            return true;
        }


        //获取当前访问的url
        String url = request.getRequestURI();

        //1.白名单判断
        if (CollectionUtils.isNotEmpty(jwtProperties.getAllowPaths())) {
            for (String allPath : jwtProperties.getAllowPaths()) {
                if (url.contains(allPath)) {
                    return true;
                }
            }
        }

        String token = request.getHeader(jwtProperties.getToken().getTokenName());

        //2.校验token的合法性

        Payload<UserInfo> playload = null;
        try {
            playload = JwtUtils.getInfoFromToken(token, jwtProperties.getPublicKey(), UserInfo.class);
        } catch (Exception e) {
            HttpServletUtil.responseError(response, ResultEnum.UNAUTHORIZED);
        }


        //3.校验权限 暂时不做校验
        //将用户id放入threadLocal
        UserInfo userInfo = playload.getInfo();
        ThreadLocalUtil.set("userId", userInfo.getId());


        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}