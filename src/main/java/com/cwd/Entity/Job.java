package com.cwd.Entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.sql.Date;

/*
* 工作兼职实体
* */
@Component
public class Job implements Serializable {
    private int id;
    private String uid="";
    private String workName="";
    private String companyName="";
    private String workPlace="";
    private String workContent="";
    private Date workTime=new Date(System.currentTimeMillis());
    private String salary="";
    private String contact="";
    private String mark="";

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", uid='" + uid + '\'' +
                ", workName='" + workName + '\'' +
                ", companyName='" + companyName + '\'' +
                ", workPlace='" + workPlace + '\'' +
                ", workContent='" + workContent + '\'' +
                ", workTime=" + workTime +
                ", salary='" + salary + '\'' +
                ", contact='" + contact + '\'' +
                ", mark='" + mark + '\'' +
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

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    public String getWorkContent() {
        return workContent;
    }

    public void setWorkContent(String workContent) {
        this.workContent = workContent;
    }

    public Date getWorkTime() {
        return workTime;
    }

    public void setWorkTime(Date workTime) {
        this.workTime = workTime;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
