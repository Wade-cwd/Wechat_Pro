package com.cwd.Entity;

import org.springframework.stereotype.Component;

import javax.naming.StringRefAddr;
import java.io.Serializable;

@Component
public class User implements Serializable {
    public String uid="";
    private Integer id=0;
    private String openid="";
    private String code="";//登录时获取的 code
    private String userName="";//微信名
    private String avatarUrl="";//微信头像地址
    private String signature="";
    private String rawData="";
    private String encryptedData="";

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", id=" + id +
                ", openid='" + openid + '\'' +
                ", code='" + code + '\'' +
                ", userName='" + userName + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", signature='" + signature + '\'' +
                ", rawData='" + rawData + '\'' +
                ", encryptedData='" + encryptedData + '\'' +
                '}';
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getRawData() {
        return rawData;
    }

    public void setRawData(String rawData) {
        this.rawData = rawData;
    }

    public String getEncryptedData() {
        return encryptedData;
    }

    public void setEncryptedData(String encryptedData) {
        this.encryptedData = encryptedData;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }


}
