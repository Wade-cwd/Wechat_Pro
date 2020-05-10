package com.cwd.config;

import com.cwd.Admin.Shiro.CustomerRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;

/*description:shiro configuration
* @Author chiwenda
*
* */
@Configuration
public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        Logger.getGlobal().info("配置shiro......");
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String,String> map=new LinkedHashMap<>();
//        static resources pass
        map.put("/js/**","anon");
        map.put("/css/**","anon");
        map.put("/admin/login","anon");
//        map.put("/admin/index","anon");
        map.put("/logout","logout");
//        map.put("/**","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        //没有登录跳转路由
        shiroFilterFactoryBean.setLoginUrl("/admin/toLogin");
        //登录后跳转路由
        shiroFilterFactoryBean.setSuccessUrl("/admin/index");
        //认证不通过
        shiroFilterFactoryBean.setUnauthorizedUrl("error");
        return  shiroFilterFactoryBean;
    }
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher=new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");//md5加密
        hashedCredentialsMatcher.setHashIterations(10);//散列10次
        return hashedCredentialsMatcher;
    }
    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        securityManager.setRealm(customerRealm());
        return securityManager;
    }
    @Bean
    public CustomerRealm customerRealm(){
        return new CustomerRealm();
    }
}
