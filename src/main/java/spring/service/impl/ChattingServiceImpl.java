package spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;
import spring.Model.Message;
import spring.buffer.MessageBuffer;
import spring.service.ChattingService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Cfw on 2016/12/3.
 */
@Service("chattingService")
public class ChattingServiceImpl implements ChattingService {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public ChattingServiceImpl(SimpMessagingTemplate simpMessagingTemplate){
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @Override
    public void dealMessage(Message message) {
        MessageBuffer.push(message);
    }

    @Override
    @Scheduled(fixedDelay = 1)
    public void sendNotifications() {
        Message message = null;
        Map<String,Object> map = new HashMap<>();
        map.put(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON);
        while((message = MessageBuffer.poll()) != null){
            //this.simpMessagingTemplate.convertAndSendToUser(message.getUserName(),"/topic/greetings",message,map);
            this.simpMessagingTemplate.convertAndSend("/topic/greetings",message,map);
        }
    }
}
