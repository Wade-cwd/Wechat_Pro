package com.cwd.Entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;

//身份证信息
@Component
public class IdCard implements Serializable {
    private Integer id=0;
    private String uid="";
    private String openid="";
    private String area="";
    private String number="";
    private String province="";

    @Override
    public String toString() {
        return "IdCard{" +
                "id=" + id +
                ", uid='" + uid + '\'' +
                ", openid='" + openid + '\'' +
                ", area='" + area + '\'' +
                ", number='" + number + '\'' +
                ", province='" + province + '\'' +
                ", addrcode='" + addrcode + '\'' +
                ", city='" + city + '\'' +
                ", sex='" + sex + '\'' +
                ", birth='" + birth + '\'' +
                ", region='" + region + '\'' +
                ", age='" + age + '\'' +
                '}';
    }

    private String addrcode="";
    private String city="";
    private String sex="";





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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getAddrcode() {
        return addrcode;
    }

    public void setAddrcode(String addrcode) {
        this.addrcode = addrcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    private String birth="";
    private String region="";
    private String age="";




}
