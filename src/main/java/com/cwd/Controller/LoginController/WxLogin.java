package com.cwd.Controller.LoginController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WxLogin {
    @RequestMapping("/wxlogin")
    public void wxLogin(Object obj){

        System.out.println(obj.toString());
    }
}
