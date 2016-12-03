package spring.service;

import spring.Model.Message;

/**
 * Created by Cfw on 2016/12/3.
 */
public interface ChattingService {

    void dealMessage(Message message);

    void sendNotifications();
}
