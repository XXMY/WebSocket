package origin.handler;

import org.springframework.util.StringUtils;
import origin.entity.Message;
import origin.enums.MessageType;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Cfw on 2016/9/5.
 */
@ServerEndpoint("/echo")
public class Echo {

    private static Set<Echo> connections = new HashSet<>();

    private Session session ;

    private String userName;
    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        connections.add(this);
    }

    @OnClose
    public void onClose(){
        connections.remove(this);
    }

    @OnMessage
    public void onMessage(String jsonMessage){
        try{
            Message requestMessage = new Message(jsonMessage);
            switch(requestMessage.getType()){
                case 1:
                    this.userName = requestMessage.getUserName();
                    requestMessage.setCount(Echo.connections.size());
                    break;
            }
            this.broadcast(requestMessage.toString());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @OnError
    public void onError(Throwable t){
        t.printStackTrace();
        this.broadcast(t.getMessage());
    }

    private void broadcast(String message){
        try {
            for(Echo echo : connections){
                echo.session.getBasicRemote().sendText(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
