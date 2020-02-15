package com.cwd.Controller.LoginController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cwd.Entity.User;
import com.cwd.Utils.HttpRequest.HttpRequest;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class WxLogin {

    @PostMapping(value = "/wxlogin")
    public void wxLogin(@RequestBody User user) throws IOException {
        String jsonStr= JSON.toJSONString(user);
        System.out.println(jsonStr);
        new HttpRequest().openGET("" +
                "https://api.weixin.qq.com/sns/jscode2session?appid=wx4885352c449fe953" +
                "&secret=b936b3b82d48d0eedc5286515d327a6c&js_code="+user.getCode()+"&grant_type=authorization_code");

    }
}
