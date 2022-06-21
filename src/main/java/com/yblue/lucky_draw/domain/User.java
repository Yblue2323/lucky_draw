package com.yblue.lucky_draw.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("tb_user")
public class User {

    @TableId(value = "user_id", type = IdType.ASSIGN_ID)
    private Long userId;

    private String name;

    private String username;

    private String password;

    private String telephone;

    private String openId;//公众号、小程序 用户唯一标识

    private String unionId;//公众号、小程序、应用 用户唯一标识

}
