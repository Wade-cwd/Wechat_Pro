package com.cwd.Admin.Service;

import com.cwd.Admin.Mapper.LoginMapper;
import com.cwd.Entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class LoginService {
    @Autowired
    private LoginMapper loginMapper;
    public void adminLogin(){

    }
    /*determine the account being
    @Param admin:user account
    * */
    public Boolean isAdminExist(Admin admin){
        return loginMapper.getAdminAccount(admin)>0?true:false;
    }
    /*根据账号密码返回用户账号
    * */
    public Admin findAdmin(Admin admin){
        return loginMapper.selectAdmin(admin);
    }
    /*根据用户名返回密码
    * */
    public String findPasswordByUserName(String userName){
        return loginMapper.selectPasswordByUserName(userName);
    }
    /*根据用户名返回账号
    * */
    public Admin findAdminByUserName(String userName){
        Admin admin=loginMapper.selectAdminByUserName(userName);
        if(admin!=null){
            Logger.getGlobal().info("根据用户名获取到的Admin:"+admin.toString());
        }else{
            Logger.getGlobal().info("根据用户名获取到的Admin为空");
        }
        return admin;
    }
}
