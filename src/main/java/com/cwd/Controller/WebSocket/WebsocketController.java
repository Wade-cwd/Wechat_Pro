package com.cwd.Controller.WebSocket;

import com.cwd.Entity.GlobalConfig;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArraySet;

//websocket连接
@ServerEndpoint("/websocket/{name}")
@RestController
public class WebsocketController {

    private static volatile int onlineCount=0;
    //与客户端的连接回话
    private Session session;
    private static CopyOnWriteArraySet<WebsocketController> websocketSet =new CopyOnWriteArraySet<WebsocketController>();

    @OnOpen
    public void OnOpen(@PathParam("name") String name,Session session){
        this.session=session;
        websocketSet.add(this);
        ++WebsocketController.onlineCount;
        GlobalConfig.getLog(this.getClass()).info("当前连接数:"+websocketSet.size());
        GlobalConfig.getLog(this.getClass()).info("新加入用户:"+name);

    }
    @OnClose
    public  void OnClose(){
        websocketSet.remove(this);
        --WebsocketController.onlineCount;
        GlobalConfig.getLog(this.getClass()).info("关闭了一个连接用户");
    }
    @OnMessage
    public void onMessage(String message, Session session) {
        GlobalConfig.getLog(this.getClass()).info("Receive a message from client: " + message);
        
    }
    @OnError
    public void onError(Session session, Throwable error) {
        GlobalConfig.getLog(this.getClass()).error("Error while websocket. ", error);
    }
    public void sendMessage(String message) throws Exception {
        if (this.session.isOpen()) {
            this.session.getBasicRemote().sendText("Send a message from server. ");
        }
    }

}
