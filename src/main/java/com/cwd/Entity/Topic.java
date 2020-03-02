package com.cwd.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

/*话题
 * */
@Component
public class Topic implements Serializable {
    private Integer id = 0;
    private String uid = "";
    private String openid = "";
    private String image = "";

    private String article = "";
    private String content = "";
    private String avatarUrl = "";
    private String nickName = "";
    private Date startTime = null;
    private Date endTime = null;
    private String type = "";
    private Integer isCheck = 0;
    private Integer viewCount = 0;
    private Integer peopleSize = 0;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date publicDatetime = new Date(System.currentTimeMillis());
    private  Integer thumbUp=0;

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", uid='" + uid + '\'' +
                ", openid='" + openid + '\'' +
                ", image='" + image + '\'' +
                ", article='" + article + '\'' +
                ", content='" + content + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", nickName='" + nickName + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", type='" + type + '\'' +
                ", isCheck=" + isCheck +
                ", viewCount=" + viewCount +
                ", peopleSize=" + peopleSize +
                ", publicDatetime=" + publicDatetime +
                ", thumbUp=" + thumbUp +
                '}';
    }

    public Integer getThumbUp() {
        return thumbUp;
    }

    public void setThumbUp(Integer thumbUp) {
        this.thumbUp = thumbUp;
    }

    public Date getPublicDatetime() {
        return publicDatetime;
    }

    public void setPublicDatetime(Date publicDatetime) {
        this.publicDatetime = publicDatetime;
    }


    public Integer getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(Integer isCheck) {
        this.isCheck = isCheck;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getPeopleSize() {
        return peopleSize;
    }

    public void setPeopleSize(Integer peopleSize) {
        this.peopleSize = peopleSize;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
