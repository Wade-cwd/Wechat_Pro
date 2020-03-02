package com.cwd.Entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigDecimal;

/*
* 二手市场实体类
* */
@Component
public class Market implements Serializable {
    private int id;
    private String uid="";
    private String openid="";
    private String avatarUrl="";
    private String nickName="";
    private String article="";
    private BigDecimal price;

    @Override
    public String toString() {
        return "Market{" +
                "id=" + id +
                ", uid='" + uid + '\'' +
                ", openid='" + openid + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", nickName='" + nickName + '\'' +
                ", article='" + article + '\'' +
                ", price=" + price +
                ", feature='" + feature + '\'' +
                ", mark='" + mark + '\'' +
                ", images='" + images + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }

    private String feature="";
    private String mark="";
    private String images="";
    private String contact="";

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
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
    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
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

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }
}
