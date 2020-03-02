package com.cwd.Entity;


import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.sql.Date;

/*
* 失物拾取实体
* */
@Component
public class Found implements Serializable {

    private  Integer id=0;
    private String uid="";
    private String avatarUrl="";
    private String nickName="";
    private String openid="";
    @NotBlank
    private String article="";//物品名称
    @NotBlank
    private String feature="";//物品特征描述
    @Past
    private Date founddate=new Date(System.currentTimeMillis());//拾取日期
    @NotBlank
    private String foundplace="";//拾取地点

    @Override
    public String toString() {
        return "Found{" +
                "id=" + id +
                ", uid='" + uid + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", nickName='" + nickName + '\'' +
                ", openid='" + openid + '\'' +
                ", article='" + article + '\'' +
                ", feature='" + feature + '\'' +
                ", founddate=" + founddate +
                ", foundplace='" + foundplace + '\'' +
                ", phone='" + phone + '\'' +
                ", mark='" + mark + '\'' +
                ", image='" + image + '\'' +
                ", issuedate=" + issuedate +
                '}';
    }

    @NotBlank
    private String phone="";//电话号码
    private String mark="";//备注
    private String image="";//图片
    private Date issuedate=new Date(System.currentTimeMillis());//发布日期

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
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

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public Date getFounddate() {
        return founddate;
    }

    public void setFounddate(Date founddate) {
        this.founddate = founddate;
    }

    public String getFoundplace() {
        return foundplace;
    }

    public void setFoundplace(String foundplace) {
        this.foundplace = foundplace;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getIssuedate() {
        return issuedate;
    }

    public void setIssuedate(Date issuedate) {
        this.issuedate = issuedate;
    }


}
