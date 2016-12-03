package spring.buffer;


import spring.Model.Message;

import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 消息缓冲区
 * Created by Duskrain on 2016/10/9.
 */
public class MessageBuffer {

    /**
     * 缓冲区大小
     *
     * @author Fangwei_Cai
     * @time since 2016-10-9 09:42:59
     */
    private final static int maxBufferSize = 10000;

    private static Integer bufferSize = 0;

    private final static ReentrantLock lock = new ReentrantLock();

    /**
     * 待发送的消息缓冲区<br/>
     * Controller 接受消息，并存入缓冲区中，<br/>
     * Thread 从缓冲区中拿取消息
     *
     * @author Fangwei_Cai
     * @time since 2016-10-9 09:43:19
     */
    private static ConcurrentLinkedQueue<Message> buriedPointBuffer = new ConcurrentLinkedQueue<Message>();

    /**
     * 向缓冲区中存入消息<br/>
     *
     * @param message
     * @return
     * @author Fangwei_Cai
     * @time since 2016-10-9 09:45:17
     */
    public static boolean push(Message message) {
        int bufferSize = MessageBuffer.getBufferSize();

        if (message != null && bufferSize < MessageBuffer.maxBufferSize) {
            buriedPointBuffer.add(message);
            bufferSizeIncrease();
            return true;
        }

        return false;
    }

    /**
     * 从缓冲区中获取消息<br/>
     *
     * @return
     * @author Fangwei_Cai
     * @time since 2016-10-9 10:04:54
     */
    public static Message poll() {
        Message message = null;
        try {
            message = buriedPointBuffer.poll();
            if (message != null) {
                bufferSizeDecrease();
            }

        } catch (NoSuchElementException e) {
            return null;
        }

        return message;
    }

    /**
     * @return
     * @author Fangwei_Cai
     * @time since 2016-10-9 11:16:10
     */
    public static int getBufferSize() {
        ReentrantLock lock = MessageBuffer.lock;
        try {
            lock.lock();
            int size = bufferSize;
            return size;
        } finally {
            lock.unlock();
        }

    }

    private static int bufferSizeIncrease() {
        ReentrantLock lock = MessageBuffer.lock;
        try {
            lock.lock();
            bufferSize++;
        } finally {
            lock.unlock();
        }

        return bufferSize;
    }

    private static int bufferSizeDecrease() {
        ReentrantLock lock = MessageBuffer.lock;
        try {
            lock.lock();
            bufferSize--;
        } finally {
            lock.unlock();
        }

        return bufferSize;
    }

    public static boolean isEmpty() {
        return buriedPointBuffer.isEmpty();
    }
}