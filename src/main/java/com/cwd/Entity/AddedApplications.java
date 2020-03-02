package com.cwd.Entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/*已参加的申请
 * */
@Component
public class AddedApplications implements Serializable {
    private Integer id = 0;
    private String openid = "";//用户openid
    private String addedOpenid = "";//用户加入的话题的openid
    private String uid = "";
    private String addedUid = "";//已加入的话题的uid


    @Override
    public String toString() {
        return "AddtionApplications{" +
                "id=" + id +
                ", openid='" + openid + '\'' +
                ", addedOpenid='" + addedOpenid + '\'' +
                ", uid='" + uid + '\'' +
                ", addedUid='" + addedUid + '\'' +
                '}';
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

    public String getAddedOpenid() {
        return addedOpenid;
    }

    public void setAddedOpenid(String addedOpenid) {
        this.addedOpenid = addedOpenid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAddedUid() {
        return addedUid;
    }

    public void setAddedUid(String addedUid) {
        this.addedUid = addedUid;
    }
}
