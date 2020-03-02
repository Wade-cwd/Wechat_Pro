package com.cwd.Entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class Certification implements Serializable {
    private int id=0;
    private String uid="";
    private String openid="";
    private String phone="";
    private String images="";
    private String wechat="";
    private String avatarUrl="";
    private String nickName="";
    private int isCheck=0;
    private String name="";
    private String idCard="";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public int getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(int isCheck) {
        this.isCheck = isCheck;
    }

    @Override
    public String toString() {
        return "certification{" +
                "id=" + id +
                ", uid='" + uid + '\'' +
                ", openid='" + openid + '\'' +
                ", phone='" + phone + '\'' +
                ", images='" + images + '\'' +
                ", wechat='" + wechat + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", nickName='" + nickName + '\'' +
                ",isCheck="+isCheck+'\''+
                ",name"+name+'\''+
                ",idCard="+idCard+'\''+
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }




}
