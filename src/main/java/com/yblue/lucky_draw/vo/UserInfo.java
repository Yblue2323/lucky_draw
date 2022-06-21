package com.yblue.lucky_draw.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 存放JWt的载荷中的登录用户信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    private Integer id;//用户ID
    private String name;//用户名称
    private String username;//用户名称
}