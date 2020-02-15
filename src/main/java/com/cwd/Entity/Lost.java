package com.cwd.Entity;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.sql.Date;

public class Lost implements Serializable {
    private String uid;
    @NotBlank
    private String article;//物品名称
    @NotBlank
    private String feature;//物品特征描述
    @Past
    private Date lostdate;//拾取日期
    @NotBlank
    private String lostplace;//拾取地点
    @NotBlank
    private String phone;//电话号码
    private String mark;//备注
    private String image;//图片
    private Date issuedate;//发布日期
    @Override
    public String toString() {
        return "Lost{" +
                "uid='" + uid + '\'' +
                ", article='" + article + '\'' +
                ", feature='" + feature + '\'' +
                ", lostdate=" + lostdate +
                ", lostplace='" + lostplace + '\'' +
                ", phone='" + phone + '\'' +
                ", mark='" + mark + '\'' +
                ", image='" + image + '\'' +
                ", issuedate=" + issuedate +
                '}';
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

    public Date getLostdate() {
        return lostdate;
    }

    public void setLostdate(Date lostdate) {
        this.lostdate = lostdate;
    }

    public String getLostplace() {
        return lostplace;
    }

    public void setLostplace(String lostplace) {
        this.lostplace = lostplace;
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
