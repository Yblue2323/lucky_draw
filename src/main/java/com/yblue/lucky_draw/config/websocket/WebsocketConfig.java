package com.yblue.lucky_draw.config.websocket;

import com.yblue.lucky_draw.config.cache.UserCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author JiaXinMa
 * @description 初始化 ServerEndpointExporter
 * 注意:如果使用独立的servlet容器，就不需要注入ServerEndpointExporter，
 * 因为servlet容器会自己提供和管理
 * @date 2021/8/16
 */
@Configuration
public class WebsocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    @Autowired
    public void setUserCache(UserCache userCache) {
        ChatWebsocket.userCache = userCache;
    }
}
