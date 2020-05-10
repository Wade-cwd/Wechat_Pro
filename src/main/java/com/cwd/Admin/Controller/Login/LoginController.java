package com.cwd.Admin.Controller.Login;

import com.cwd.Entity.Admin;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.websocket.server.PathParam;
import java.util.logging.Logger;

/*管理员登录
* */
@Controller
@RequestMapping("/admin")
public class LoginController {
    private Admin temAdmin=new Admin();
    /*登录验证拦截
    * */
    @PostMapping("/login")
    public ModelAndView adminLogin(@PathParam("userName") String userName, @PathParam("password") String password){
        Logger.getGlobal().info("登录中......当前用户:"+userName+"当前密码："+password);
        Subject subject= SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(userName,password);
        ModelAndView modelAndView=new ModelAndView();
        try{
            //登录成功
            subject.login(usernamePasswordToken);
            Logger.getGlobal().info("登录成功!");
            modelAndView.addObject("loginStatus","登录成功!");
            modelAndView.setViewName("index");
            return modelAndView;
        } catch (UnknownAccountException e){
            e.printStackTrace();
            Logger.getGlobal().info("用户名不存在!");
            modelAndView.addObject("loginStatus","用户名不存在！");
            modelAndView.setViewName("login");
            return modelAndView;
        } catch (IncorrectCredentialsException e){
            e.printStackTrace();
            Logger.getGlobal().info("密码错误!");
            modelAndView.addObject("loginStatus","密码不正确！");
            modelAndView.setViewName("login");
            return modelAndView;
        } catch (AuthenticationException e){
            e.printStackTrace();
            Logger.getGlobal().info("账号密码错误返回登录页");
            modelAndView.addObject("loginStatus","账号异常！");
            modelAndView.setViewName("login");
            return modelAndView;
        }
    }
    /*未登录跳转登录界面
    * */
    @RequestMapping("/toLogin")
    public String toLoginPage(){
        Logger.getGlobal().info("未登录，跳转登录界面");
        return "login";
    }
    /*已经登录跳转首页
    * */
    @RequestMapping("/index")
    public String index(){
        Logger.getGlobal().info("已登录跳转到首页");
        return "index";
    }
}
