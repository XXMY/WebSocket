package origin.handler;

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
    public void onMessage(String message){
        this.broadcast(message);
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
