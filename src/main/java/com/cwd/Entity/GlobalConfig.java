package com.cwd.Entity;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/*
* 全局数据
* */
@Component
public class GlobalConfig  implements Serializable {
    //微信小程序的请求url
    private String signature="";//rawData+session_key
    private  String openid="";//微信服务器返回的用户唯一id
    private String session_key="";//微信返回的加密解密key
    private  final String appid="wx4885352c449fe953";//小程序开发者id
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

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getSession_key() {
        return session_key;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    //获取日志
    public static Logger getLog(Class clazz){
        return LoggerFactory.getLogger(clazz);
    }

    @Override
    public String toString() {
        return "GlobalConfig{" +
                "signature='" + signature + '\'' +
                ", openid='" + openid + '\'' +
                ", session_key='" + session_key + '\'' +
                ", appid='" + appid + '\'' +
                ", secret_id='" + secret_id + '\'' +
                ", wxRequstUrl='" + wxRequstUrl + '\'' +
                ", localFilePath='" + localFilePath + '\'' +
                '}';
    }
}
