package com.yblue.lucky_draw.util;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class ThreadLocalUtil {

    // jdk建议将 ThreadLocal 定义为 private static ，这样就不会有弱引用，内存泄漏的问题了
    private static ThreadLocal<Map> mapThreadLocal = ThreadLocal.withInitial(HashMap::new);

    //获取当前线程的存的变量
    public static Map get() {
        return mapThreadLocal.get();
    }

    //设置当前线程的存的变量
    public static void set(Map map) {
        mapThreadLocal.set(map);
    }

    public static <T> T set(String key, T value) {
        mapThreadLocal.get().put(key, value);
        return value;
    }

    public static Object get(String key) {
        return mapThreadLocal.get().get(key);
    }

    //移除当前线程的存的变量
    public static void remove() {
        mapThreadLocal.remove();
    }

}