package com.yblue.lucky_draw.config.websocket;

import com.yblue.lucky_draw.config.cache.UserCache;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.MutablePair;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author: JiaXinMa
 * @description: 聊天室
 * @date: 2022/5/9
 */
@Slf4j
@Component
@ServerEndpoint("/websocket/chat/{UserTo}")
//  ws://localhost:7748/websocket/chat
//  入参是 userId,userId,group:groupId 拼起来的字符串 如果是群组的要拼上 group:
public class ChatWebsocket {

    //注意;每次刷新页面(就是重新连接)session都会变
    // 同时服务端会自动去调用关闭连接，然后再建立连接

    private static final String userPrefix = "user:";

    private static final String groupPrefix = "group:";

    private static final String connect = ":";

    public static UserCache userCache;

    private static Map<Object, Session> sessionMap = new ConcurrentHashMap<>();


    //建立连接
    @OnOpen
    public void onOpen(Session session, @PathParam("UserTo") String userTo) {

        Long sendUserId = getSendUserId(userTo);

        MutablePair<List<String>, List<String>> userAndSoOn = getUserAndSoOn(userTo);
        if ((!CollectionUtils.isEmpty(userAndSoOn.getRight()) && !CollectionUtils.isEmpty(userAndSoOn.getLeft()) ||
                (!CollectionUtils.isEmpty(userAndSoOn.getRight()) && userAndSoOn.getRight().size() > 1)) ||
                (!CollectionUtils.isEmpty(userAndSoOn.getLeft()) && userAndSoOn.getRight().size() > 1)) {
            //这些情况是转发

        } else if (!CollectionUtils.isEmpty(userAndSoOn.getRight())) {
            sessionMap.put(userAndSoOn.getRight().get(0), session);
        } else if (!CollectionUtils.isEmpty(userAndSoOn.getLeft())) {
            sessionMap.put(userAndSoOn.getLeft().get(0), session);
        }

        log.info("用户id:{},用户名:{} 建立连接成功!!!", sendUserId, userCache.getUserById(sendUserId).getName());

    }

    //关闭连接
    @OnClose
    public void onClose(Session session, @PathParam("UserTo") String userTo) {
        try {
            MutablePair<List<String>, List<String>> userAndSoOn = getUserAndSoOn(userTo);
            if (!CollectionUtils.isEmpty(userAndSoOn.getRight())) {
                sessionMap.remove(userAndSoOn.getRight().get(0), session);
            } else if (!CollectionUtils.isEmpty(userAndSoOn.getLeft())) {
                sessionMap.remove(userAndSoOn.getLeft().get(0), session);
            }
            session.close();

        } catch (IOException e) {
            log.info("关闭连接失败!!!");
        }
    }


    //群发消息
    @OnMessage
    public void onMessage(String message, @PathParam("UserTo") String userTo) throws IOException {

        Long sendUserId = getSendUserId(userTo);
        MutablePair<List<String>, List<String>> userAndSoOn = getUserAndSoOn(userTo);

        //前3种情况是转发
        List<String> right = userAndSoOn.getRight();
        List<String> left = userAndSoOn.getLeft();
        if (!CollectionUtils.isEmpty(right) && !CollectionUtils.isEmpty(left)) {

        } else if (!CollectionUtils.isEmpty(right) && right.size() > 1) {

        } else if (!CollectionUtils.isEmpty(left) && right.size() > 1) {

        } else if (!CollectionUtils.isEmpty(right)) {

        } else if (!CollectionUtils.isEmpty(left)) {

        }

//        for (int i = 0; i < userAndSoOn.length; i++) {
//            String sendUserName = "";
//
//            if (i == 0) {
//                sendUserName = "我";
//            } else {
//                sendUserName = userCache.getUserById(sendUserId).getName();
//            }
//
//
//            //如果是包含群组的需要发送个所有群成员
//            if (userAndSoOn[i].contains(groupPrefix)) {
//                long groupId = Long.parseLong(userAndSoOn[i].substring(0, groupPrefix.length() - 1));
//            }
//
//            if (sessionMap.get(Long.parseLong(userAndSoOn[i])) != null) {
//
//                Message msg = new Message();
//                msg.setUserName(sendUserName);
//                msg.setMessage(message);
//
//                sessionMap.get(Long.parseLong(userAndSoOn[i])).getBasicRemote().sendText(JSON.toJSONString(new JsonResult(200, "发送消息成功!", msg)));
//            }
//
//        }

    }

    /**
     * @author: JiaXinMa
     * @description: 左边放 用户的，右边放 群聊的
     * @date: 2022/5/9
     */
    private MutablePair<List<String>, List<String>> getUserAndSoOn(String userTo) {

        List<String> left = new ArrayList<>();
        List<String> right = new ArrayList<>();

        String[] split = userTo.split(",");
        String sendUserId = split[0];
        for (int i = 1; i < split.length; i++) {
            String review = split[i];
            if (review.contains(groupPrefix)) {
                right.add(sendUserId + connect + review);
            } else {
                left.add(sendUserId + connect + review);
            }
        }

        MutablePair<List<String>, List<String>> pair = new MutablePair<>();
        pair.setRight(right);
        pair.setLeft(left);
        return pair;
    }

    private Long getSendUserId(String userTo) {
        return Long.valueOf(userTo.split(",")[0]);
    }

    //ws://localhost:7748/websocket/chat/01 523586176064200705,01523586176064200706
    //ws://localhost:7748/websocket/chat/01523586176064200706,01523586176064200705,01523586176072589314
    //ws://localhost:7748/websocket/chat/01523586176072589314,01523586176064200706,01523586176064200705


    @Data
    class Message {
        String userName;
        String message;
        String currentTime;
    }
}
