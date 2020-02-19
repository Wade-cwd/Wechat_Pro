package com.cwd.Service.Login;

import com.cwd.Entity.GlobalConfig;
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
    /*
    *从服务器获取用户的唯一标识和回话秘钥
     */
    public JSONObject getSessionFromWxServer(String code){
        JSONObject jsonObject = null;
        try {
            //联网获取微信服务器响应
            String wxSession= new HttpRequest().openGET("" +
                    "https://api.weixin.qq.com/sns/jscode2session?appid="+globalConfig.getAppid() +
                    "&secret="+globalConfig.getSecret_id()+"&js_code="+code+"&grant_type=authorization_code");
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
    public void decryptWith$Session_Key(String session_key,String encryptedData,String iv){
//        wxCore.decrypt(,session_key,)
    }
}
