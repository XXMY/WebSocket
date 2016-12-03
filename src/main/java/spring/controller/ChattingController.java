package spring.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import spring.Model.Greeting;
import spring.Model.HelloMessage;
import spring.Model.Message;
import spring.buffer.MessageBuffer;
import spring.service.ChattingService;

import javax.annotation.Resource;

/**
 * Greeting message-handling controller
 * Created by Duskrain on 2016/8/31.
 */
@Controller
public class ChattingController {

    @Resource(name = "chattingService")
    private ChattingService chattingService;

    @MessageMapping("/spring")
    public void greeting(Message message) throws Exception {

        this.chattingService.dealMessage(message);

    }

}
