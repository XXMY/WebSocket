package spring.Model;

import com.google.gson.Gson;

/**
 * Created by Cfw on 2016/10/2.
 */
public class Message {

    private static Gson gson = new Gson();

    private String userName;

    private int type;

    private String content;

    private int count;

    public Message(){}

    public Message(int type, String content){
        this.type = type;
        this.content = content;
    }
    public Message(String jsonMessage){
        Message m = Message.gson.fromJson(jsonMessage,Message.class);
        this.userName = m.getUserName();
        this.type = m.getType();
        this.content = m.getContent();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String toString(){
        return Message.gson.toJson(this);
    }
}
