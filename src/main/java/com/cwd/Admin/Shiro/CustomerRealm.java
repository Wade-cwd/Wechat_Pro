package com.cwd.Admin.Shiro;

import com.cwd.Admin.Service.LoginService;
import com.cwd.Entity.Admin;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
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
        String userName= (String) authenticationToken.getPrincipal();
        Logger.getGlobal().info("获取用户输入的账号："+userName);
        //数据库验证
        Admin admin= loginService.findByUserName(userName);
        if(admin==null) return null;
        Logger.getGlobal().info("对比用户名:"+userName+"与"+admin.getUserName());
        SimpleAuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo(userName,
                admin.getPassword(),
                ByteSource.Util.bytes(admin.getPassword()),
                getName());
        Logger.getGlobal().info("授权信息："+authenticationInfo.toString());
        return authenticationInfo;
    }
}
