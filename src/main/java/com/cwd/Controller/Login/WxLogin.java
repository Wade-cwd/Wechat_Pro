package com.cwd.Controller.Login;

import com.alibaba.fastjson.JSON;
import com.cwd.Entity.GlobalConfig;
import com.cwd.Entity.User;
import com.cwd.Service.Login.WxLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/login")
public class WxLogin {
    @Autowired
    private WxLoginService wxLoginService;

//    微信登录
    @PostMapping(value = "/wxLogin")
    public void wxLogin(@RequestBody User user) throws IOException {
        GlobalConfig.getLog(this.getClass()).info("数据"+user.toString());
        String jsonStr= JSON.toJSONString(user);
        if(user!=null){
            wxLoginService.getSessionFromWxServer(user.getCode());
        }
    }
//校验登录状态
    @PostMapping("/wxLoginStatus")
    public  void wxLoginStatus(@RequestBody User user){
        GlobalConfig.getLog(this.getClass()).info("开始校验微信登录状态");

    }
}
