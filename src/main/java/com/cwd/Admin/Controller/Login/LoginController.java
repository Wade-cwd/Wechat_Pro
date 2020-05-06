package com.cwd.Admin.Controller.Login;

import com.cwd.Entity.Admin;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.logging.Logger;

/*管理员登录界面
* */
@Controller
@RequestMapping("/admin")
public class LoginController {
    private Admin temAdmin=new Admin();
    /*登录验证
    * */
    @PostMapping("/login/{userName}/{password}")
    @ResponseBody
    public String adminLogin(@PathVariable("userName")String userName,@PathVariable("password")String password){
        Logger.getGlobal().info("登录中......");
        Admin admin=new Admin();
        admin.setUserName("admin");
        admin.setPassword("admin");
        Subject subject= SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(admin.getUserName(),admin.getPassword());
        try{
            subject.login(usernamePasswordToken);
        }catch (AuthenticationException e){
            e.printStackTrace();
            Logger.getGlobal().info("账号密码错误返回登录页");
            return "login";
        }
        Logger.getGlobal().info("登录成功");
        return "index";
    }
    /*未登录跳转登录界面
    * */
    @RequestMapping("/toLogin")
    public String toLoginPage(){
        Logger.getGlobal().info("未登录，跳转登录界面");
        return "login";
    }
    /*已经登录跳转index页面
    * */
    @RequestMapping("/index")
    public String toIndex(){
        Logger.getGlobal().info("已登录跳转到首页");
        return "index";
    }
}
