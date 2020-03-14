package com.cwd.Controller.WebSocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cwd.Entity.ChatMessage;
import com.cwd.Entity.GlobalConfig;
import com.fasterxml.jackson.databind.json.JsonMapper;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CopyOnWriteArraySet;

//websocket连接
@ServerEndpoint("/chat/{sender}/{receiver}")
@RestController
public class ChatController {

    private static volatile int onlineCount = 0;//在线人数
    //与客户端的连接回话
    private Session session;
    private static CopyOnWriteArraySet<ChatController> websocketSet = new CopyOnWriteArraySet<ChatController>();//在线对象数组
    private String receiver;//接收者
    private String sender;//发送者
/*连接时触发*/
    @OnOpen
    public void OnOpen(@PathParam("sender") String sender, @PathParam("receiver") String receiver, Session session) {
        this.session = session;
        this.receiver = receiver;
        this.sender = sender;

        if(ChatController.websocketSet.size()>0){
            if(!this.isConnectExist(this)){
                GlobalConfig.getLog(this.getClass()).info("连接池不存在:"+this.sender+"对象");
                websocketSet.add(this);//加入当前对象
                ++ChatController.onlineCount;
                GlobalConfig.getLog(this.getClass()).info("已连接!新加入用户:" + sender);
                GlobalConfig.getLog(this.getClass()).info("当前连接数:" + websocketSet.size());
                GlobalConfig.getLog(this.getClass()).info("sessionId:" + session.getId());
            }
        }else {
            websocketSet.add(this);//加入当前对象
            ++ChatController.onlineCount;
            GlobalConfig.getLog(this.getClass()).info("已连接!新加入用户:" + sender);
            GlobalConfig.getLog(this.getClass()).info("当前连接数:" + websocketSet.size());
            GlobalConfig.getLog(this.getClass()).info("sessionId:" + session.getId());
        }
    }
/*关闭连接触发*/
    @OnClose
    public void OnClose() throws Exception {
        for (ChatController chatControllers : ChatController.websocketSet) {
            GlobalConfig.getLog(this.getClass()).info("队列" + chatControllers.receiver);
        }
        websocketSet.remove(this);
        --ChatController.onlineCount;
        GlobalConfig.getLog(this.getClass()).info("关闭了一个连接用户："+this.receiver);
    }
/*触发发送消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        for (ChatController chat : ChatController.websocketSet) {
            if (this.sender.equals(chat.receiver)) {
                try {
                    GlobalConfig.getLog(this.getClass()).info("发送者:" + this.sender  + "-发送目标:" + this.receiver);
                    ChatMessage chatMessage=new ChatMessage(this.sender,this.receiver,message,
                            new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
                    JSONObject jsonObject= (JSONObject) JSONObject.toJSON(chatMessage);//转成json
                    GlobalConfig.getLog(this.getClass()).info("即将发送数据:" + jsonObject.toJSONString());
                    chat.sendMessage(jsonObject, chat);//广播 发送
                    GlobalConfig.getLog(this.getClass()).info("成功发消息给:" + this.receiver);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @OnError
    public void onError(Session session, Throwable error) {
        GlobalConfig.getLog(this.getClass()).error("Error while websocket. ", error);
    }
/*发送消息*/
    public void sendMessage(JSONObject message, ChatController chatController) throws Exception {
        GlobalConfig.getLog(this.getClass()).info("正在发送消息:" + message);
        if (chatController.session.isOpen()) {
            chatController.session.getAsyncRemote().sendText(message.toJSONString());
            GlobalConfig.getLog(this.getClass()).info("发送成功!");
        }
    }
    /*是否存在连接*/
    public Boolean  isConnectExist(ChatController chatController){
        for(ChatController chat:ChatController.websocketSet){
            if (chatController.sender.equals(chat.sender) && chatController.receiver.equals(chat.receiver)) {
                return true;
            }else {
                continue;
            }
        }
        return false;
    }
}
