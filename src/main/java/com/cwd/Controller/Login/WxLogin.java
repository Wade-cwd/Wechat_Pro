package com.cwd.Controller.Login;

import com.alibaba.fastjson.JSON;
import com.cwd.Entity.GlobalConfig;
import com.cwd.Entity.User;
import com.cwd.Service.Login.WxLoginService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
@RestController
@RequestMapping("/login")
public class WxLogin {
    @Autowired
    private WxLoginService wxLoginService;
    @Autowired
    GlobalConfig globalConfig;//当前控制器的全局配置

//    微信登录
    @PostMapping(value = "/wxLogin")
    public void wxLogin(@RequestBody User user) throws IOException {
        String openid="";
        GlobalConfig.getLog(this.getClass()).info("数据"+user.toString());
        if(user!=null){
           JSONObject jsonObject= wxLoginService.getSessionFromWxServer(user.getCode());
           GlobalConfig.getLog(this.getClass()).info("拿到openID:"+jsonObject.getString("openid"));
           openid=jsonObject.getString("openid");
           globalConfig.setOpenid(openid);//openid存入全局配置
        }
        //判断openid是否在数据库中存在
        boolean openidExist= wxLoginService.isOpenidExist(openid);
        if(!openidExist){
            //用户添加唯一标识
            user.setOpenid(openid);
            //添加用户
            wxLoginService.addOneUser(user);
            GlobalConfig.getLog(this.getClass()).info("添加用户成功");

        }else{
            GlobalConfig.getLog(this.getClass()).info("用户已存在");

        }

    }
    //返回用户openid
    @PostMapping("/getOpenid")
    public  String  getOpenid(){
        GlobalConfig.getLog(this.getClass()).info("获取openid中...");
        return globalConfig.getOpenid();
    }
    //返回用户openid通过网络
//    @PostMapping("/getOpenidNet")
//    public String getOpenidNet(User user){
//        String openid="";
//        wxLoginService.getSessionFromWxServer()
//        return
//    }

//校验登录状态
    @PostMapping("/wxLoginStatus")
    public  void wxLoginStatus(@RequestBody User user){
        GlobalConfig.getLog(this.getClass()).info("开始校验微信登录状态");

    }
}
