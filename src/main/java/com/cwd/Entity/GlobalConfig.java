package com.cwd.Entity;


import org.springframework.stereotype.Component;

import java.io.Serializable;

/*
* 全局数据
* */
@Component
public class GlobalConfig  implements Serializable {

    //微信小程序的请求url
    private  final String appid="wx4885352c449fe953";
    private  final String secret_id="b936b3b82d48d0eedc5286515d327a6c";
    private  final String wxRequstUrl="https://api.weixin.qq.com/sns/jscode2session?appid="+this.appid+
            "&secret=b936b3b82d48d0eedc5286515d327a6c&js_code="+this.secret_id+"&grant_type=authorization_code";
    //图片服务器的全局路径
    private  final String localFilePath="D:\\JavaWebProject\\Wechat\\Images\\";

    public String getAppid() {
        return appid;
    }

    public String getSecret_id() {
        return secret_id;
    }

    public String getLocalFilePath() {
        return localFilePath;
    }

    public String getWxRequstUrl() {
        return wxRequstUrl;
    }


}
