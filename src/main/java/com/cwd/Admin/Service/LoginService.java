package com.cwd.Admin.Service;

import com.cwd.Admin.Mapper.LoginMapper;
import com.cwd.Entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Admin findByUserName(String userName){
        Admin adminAccount=loginMapper.selectByUserName(userName);
        return adminAccount;
    }
    /*返回用户账号
    * */
    public Admin findAdmin(Admin admin){
        return loginMapper.selectAdmin(admin);
    }
}
