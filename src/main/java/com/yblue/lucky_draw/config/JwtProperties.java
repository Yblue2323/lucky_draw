package com.yblue.lucky_draw.config;


import com.yblue.lucky_draw.util.RsaUtils;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.PrivateKey;
import java.security.PublicKey;

import java.util.List;

/**
 * 读取jwt配置信息
 */
@Data
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    private String pubKeyPath;//公钥路径
    private String priKeyPath;//私钥路径

    private List<String> allowPaths;//白名单

    private PublicKey publicKey;//公钥对象
    private PrivateKey privateKey;//私钥对象

    //通过路径获取公私钥对象
    @PostConstruct // 标记为初始化方法
    public void init() throws Exception {
        publicKey = RsaUtils.getPublicKey(pubKeyPath);
        privateKey = RsaUtils.getPrivateKey(priKeyPath);
    }

    //接收cookie中的属性
    private TokenPojo token = new TokenPojo();

    @Data
    public class TokenPojo {
        private Integer expire;
        private Integer refreshTime;
        private String TokenName;
    }

}
