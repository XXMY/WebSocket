package spring;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * Greeting message-handling controller
 * Created by Duskrain on 2016/8/31.
 */
@Controller
public class GreetingController {

    @MessageMapping("/spring")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(500);
        System.out.println(message.getMessage());
        return new Greeting("Hello: "+message.getMessage()+ "!");
    }

}
