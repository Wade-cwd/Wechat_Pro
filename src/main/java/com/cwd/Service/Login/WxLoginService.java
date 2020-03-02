package com.cwd.Service.Login;

import com.cwd.Entity.GlobalConfig;
import com.cwd.Entity.User;
import com.cwd.Mapper.UserMapper;
import com.cwd.Utils.AES.WXCore;
import com.cwd.Utils.HttpRequest.HttpRequest;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/*
* 微信登录业务层
* */
@Service
public class WxLoginService {
    @Autowired
    private GlobalConfig globalConfig;
    @Autowired
    private WXCore wxCore;
    @Autowired
    private UserMapper userMapper;



    /*
    *从服务器获取用户的唯一标识和回话秘钥
     */
    public JSONObject getSessionFromWxServer(String code){
        JSONObject jsonObject = null;
        try {
            //联网获取微信服务器响应
            String wxSession= new HttpRequest().open("" +
                    "https://api.weixin.qq.com/sns/jscode2session?appid="+globalConfig.getAppid() +
                    "&secret="+globalConfig.getSecret_id()+"&js_code="+code+"&grant_type=authorization_code","GET");
             jsonObject =new JSONObject(wxSession);
            GlobalConfig.getLog(this.getClass()).info("转换成json成功:"+jsonObject.getString("session_key"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    /*
    校验解密数据
     */
//    public void decryptWith$Session_Key(String session_key,String encryptedData,String iv){
////        wxCore.decrypt(,session_key,)
//    }
    //添加一条用户
    public void addOneUser(User user){
        userMapper.addOneUser(user);
    }
    //是否存在openid对应用户
    public Boolean isOpenidExist(String openid){
        int count= userMapper.getByOpenidCount(openid);
        return count>0?true:false;
    }
}
