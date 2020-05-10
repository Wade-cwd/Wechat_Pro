package com.cwd.Admin.Shiro;

import com.cwd.Admin.Service.LoginService;
import com.cwd.Entity.Admin;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

public class CustomerRealm  extends AuthorizingRealm {
    @Autowired
    private LoginService loginService;
    /*权限
    * */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
    /*身份认证
    * */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        Logger.getGlobal().info("登录校验开始......");
        //获取账号
        UsernamePasswordToken usernamePasswordToken= (UsernamePasswordToken) authenticationToken;
        Logger.getGlobal().info("获取用户输入的账号："+usernamePasswordToken.getUsername());
        //数据库验证
        Admin admin= loginService.findAdminByUserName(usernamePasswordToken.getUsername());
        //查询不到账号返回null,shiro抛出账号不存在
        if(admin==null) return null;
        Logger.getGlobal().info("查询到用户名:"+admin.getUserName()+" 密码："+admin.getPassword());
        SimpleAuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo("", admin.getPassword(),
                "");
        return authenticationInfo;
    }
}
