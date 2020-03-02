package com.cwd.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

/*评论表*/
@Component
public class Comment implements Serializable {
    private Integer id = 0;
    private String uid = "";
    private String openid = "";
    private String targetOpenid = "";
    private String targetUid = "";
    private String content = "";
    private String nickName = "";
    private String avatarUrl = "";
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date commentDatetime=new Date(System.currentTimeMillis());


    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", uid='" + uid + '\'' +
                ", openid='" + openid + '\'' +
                ", targetOpenid='" + targetOpenid + '\'' +
                ", targetUid='" + targetUid + '\'' +
                ", content='" + content + '\'' +
                ", nickName='" + nickName + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", commentDatetime=" + commentDatetime +
                '}';
    }

    public Date getCommentDatetime() {
        return commentDatetime;
    }

    public void setCommentDatetime(Date commentDatetime) {
        this.commentDatetime = commentDatetime;
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

    public String getTargetOpenid() {
        return targetOpenid;
    }

    public void setTargetOpenid(String targetOpenid) {
        this.targetOpenid = targetOpenid;
    }

    public String getTargetUid() {
        return targetUid;
    }

    public void setTargetUid(String targetUid) {
        this.targetUid = targetUid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
