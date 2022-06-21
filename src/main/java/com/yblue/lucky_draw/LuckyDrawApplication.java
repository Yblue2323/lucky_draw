package com.yblue.lucky_draw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@MapperScan("com.yblue.lucky_draw.mapper")
public class LuckyDrawApplication {
    public static void main(String[] args) {
        SpringApplication.run(LuckyDrawApplication.class, args);
    }
}
