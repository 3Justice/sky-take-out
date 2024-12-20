package com.sky.task;

import com.sky.websocket.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class WebSocketTask {
    @Autowired
    private WebSocketServer webSocketServer;

    /**
     * 通过WebSocket每隔5秒向客户端发送消息
     */
    @Scheduled(cron = "0/5 * * * * ?")
    public void sendMessageToClient() {
        try {
            System.out.println("Scheduled task started");
            String message = "这是来自服务端的消息：" + DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now());
            System.out.println("Sending message: " + message);
            webSocketServer.sendToAllClient(message);
            System.out.println("Message sent successfully");
        } catch (Exception e) {
            System.err.println("Error sending message to clients: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
