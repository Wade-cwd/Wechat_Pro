package com.cwd.Entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

//对话消息实体
@Component
public class ChatMessage implements Serializable {

    private String sender;//发送者
    private String receiver;//接收者
    private String message;//发送内容
    private String sendTime;//发送时间

    public ChatMessage(){}
    public ChatMessage(String sender, String receiver, String message, String sendTime) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.sendTime = sendTime;
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", message='" + message + '\'' +
                ", sendTime='" + sendTime + '\'' +
                '}';
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }
}
