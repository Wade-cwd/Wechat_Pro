package com.cwd.Entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/*点赞表*/
@Component
public class PlusThumb implements Serializable {
    private Integer id;
    private String openid;
    private String uid;
    private String topicOpenid;
    private String topicUid;
    private String commentOpenid;
    private Integer isTopicPlusThumb;//是否点赞

    public PlusThumb(){}

    public PlusThumb(String openid, String uid, String topicOpenid, String topicUid, String commentOpenid, Integer isTopicPlusThumb) {
        this.openid = openid;
        this.uid = uid;
        this.topicOpenid = topicOpenid;
        this.topicUid = topicUid;
        this.commentOpenid = commentOpenid;
        this.isTopicPlusThumb = isTopicPlusThumb;
    }

    @Override
    public String toString() {
        return "PlusThumb{" +
                "id=" + id +
                ", openid='" + openid + '\'' +
                ", uid='" + uid + '\'' +
                ", topicOpenid='" + topicOpenid + '\'' +
                ", topicUid='" + topicUid + '\'' +
                ", commentOpenid='" + commentOpenid + '\'' +
                ", isTopicPlusThumb=" + isTopicPlusThumb +
                '}';
    }

    public Integer getIsTopicPlusThumb() {
        return isTopicPlusThumb;
    }

    public void setIsTopicPlusThumb(Integer isTopicPlusThumb) {
        this.isTopicPlusThumb = isTopicPlusThumb;
    }

    public String getTopicOpenid() {
        return topicOpenid;
    }

    public void setTopicOpenid(String topicOpenid) {
        this.topicOpenid = topicOpenid;
    }

    public String getTopicUid() {
        return topicUid;
    }

    public void setTopicUid(String topicUid) {
        this.topicUid = topicUid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getCommentOpenid() {
        return commentOpenid;
    }

    public void setCommentOpenid(String commentOpenid) {
        this.commentOpenid = commentOpenid;
    }

}
