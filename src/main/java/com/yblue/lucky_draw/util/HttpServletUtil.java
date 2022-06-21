package com.yblue.lucky_draw.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.MutablePair;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


@Slf4j
public class HttpServletUtil {

    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public static HttpServletResponse getHttpServletResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    /**
     * 获取url中的参数
     */
    public static Map<String, String> getUrlParam(HttpServletRequest request) {
        Map<String, String> result = null;

        String urlParam = request.getQueryString();//字段1=值1&...&字段n=值n
        if (StringUtils.isNotBlank(urlParam)) {
            result = new HashMap<>();
            String[] paramArr = urlParam.split("&");
            for (String param : paramArr) {
                result.put(param.split("=")[0], param.split("=")[1]);
            }
        }

        return result;
    }

    /**
     * 获取body中的参数
     * 返回值:
     * 左边是 {字段1=值1,..., 字段n=字段n}
     * 右边是 {"字段1":值1,..., "字段n":字段n}
     */
    public static MutablePair<Map<String, String>, String> getBodyParam(HttpServletRequest request) throws IOException {

        BufferedReader streamReader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
        StringBuilder responseStrBuilder = new StringBuilder();
        String inputStr;
        while ((inputStr = streamReader.readLine()) != null) {
            responseStrBuilder.append(inputStr);
        }

        MutablePair<Map<String, String>, String> result = new MutablePair<>();
        result.setLeft(JSON.parseObject(responseStrBuilder.toString(), Map.class));
        result.setRight(responseStrBuilder.toString());

        return result;
    }

    /**
     * 获取form-data中的参数
     */
    public static Map<String, String> getFormDataParam(HttpServletRequest request) {
        Map<String, String> result = new HashMap<>();

        Enumeration<String> formData = request.getParameterNames();
        while (formData.hasMoreElements()) {
            String name = formData.nextElement();
            String value = request.getParameter(name);
            result.put(name, value);
        }

        return result;
    }

    /**
     * 获取客户端IP
     */
    public static String getHost(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }


        if ("127.0.0.1".equals(ip)) {
            InetAddress inet = null;
            try { // 根据网卡取本机配置的IP
                inet = InetAddress.getLocalHost();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            ip = inet.getHostAddress();
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ip != null && ip.length() > 15) {
            if (ip.indexOf(",") > 0) {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }
        return ip;
    }

    /**
     * 响应错误信息
     */
    public static void responseError(HttpServletResponse response, Integer code, String returnMessage) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("code", code);
            jsonObject.put("msg", returnMessage);
            response.getWriter().append(jsonObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 响应错误信息
     */
    public static void responseError(HttpServletResponse response, ResultEnum resultEnum) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("code", resultEnum.getCode());
            jsonObject.put("msg", resultEnum.getMsg());
            response.getWriter().append(jsonObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
