package com.potoyang.learn.websocketrabbitmq;

import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.TimeoutException;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * Create: 2018/8/8 15:50
 * Modified By:
 * Description:
 */
@ServerEndpoint("/mysocket")
@Component
@Slf4j
public class WebSocketServerEndpoint {
    private static Logger logger = LoggerFactory.getLogger(WebSocketServerEndpoint.class);

    private Session session;

    private static CopyOnWriteArrayList<Session> sessions = new CopyOnWriteArrayList<>();

    private static int onlineCount = 0;
    private static CopyOnWriteArraySet<WebSocketServerEndpoint> webSocketSet = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        sessions.add(session);
        webSocketSet.add(this);
        addOnlineCount();
        logger.info("有新的连接加入，当前在线人数为: " + getOnlineCount());
        String queueName = "exchange_test";
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("192.168.20.108");
            factory.setPort(5672);
            factory.setUsername("asdf");
            factory.setPassword("asdf");

            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.queueDeclare(queueName, true, false, false, null);
            channel.basicQos(1);
            logger.info("[*] Waiting for message.");
            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                                           byte[] body) throws IOException {
                    String message = null;
                    try {
                        message = new String(body, "utf-8");
                        sendMessage(session, message);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                        channel.abort();
                    } finally {
                        logger.info("[x] Done");
                        channel.basicAck(envelope.getDeliveryTag(), false);
                    }
                    logger.info("[x] Received '" + message + "'");
                }
            };

            channel.basicConsume(queueName, false, consumer);
        } catch (TimeoutException | IOException e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        sessions.remove(session);
        subOnlineCount();
        logger.info("有一个连接关闭，当前在线人数为: " + getOnlineCount());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        logger.info("来自客户端的消息: " + message);
        for (WebSocketServerEndpoint item : webSocketSet) {
            try {
                item.sendMessage(item.session, session.getId() + ":" + message);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        logger.error("发生错误");
        error.printStackTrace();
    }

    private void sendMessage(Session session, String message) throws IOException {
        session.getBasicRemote().sendText(message);
        logger.info("[x] 推送消息" + message);
    }

    private static synchronized int getOnlineCount() {
        return onlineCount;
    }

    private static synchronized void addOnlineCount() {
        WebSocketServerEndpoint.onlineCount++;
    }

    private static synchronized void subOnlineCount() {
        WebSocketServerEndpoint.onlineCount--;
    }
}
