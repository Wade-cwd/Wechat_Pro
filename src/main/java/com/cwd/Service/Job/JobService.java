package com.cwd.Service.Job;

import com.cwd.Entity.Job;
import com.cwd.Entity.Lost;
import com.cwd.Mapper.JobMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {
    @Autowired
    private JobMapper jobMapper;


    //添加一条兼职工作记录
    public void addOneJobRecord(Job job){
        jobMapper.addOneJob(job);
    }
    //处理兼职就业列表业务
    public PageInfo<Job> getJobList(int pageNo, int pageSize){
        PageHelper.startPage(pageNo,pageSize);//分页
        List<Job> jobs=jobMapper.getJobList();
        PageInfo<Job> pageInfo=new PageInfo<>(jobs);
        return pageInfo;
    }
    /*更新字段*/
    public Integer setJobField(String fieldName,String value,String uid,String openid){
        Integer resultCount=jobMapper.updateFound(fieldName,value,uid,openid);
        if(resultCount!=null&&resultCount>0){
            return resultCount;
        }else {
            return -1;
        }
    }
    /*删除记录*/
    public  Integer delOneFound(String uid,String openid){
        Integer delResult=jobMapper.deleteFound(uid,openid);
        if(delResult!=null&&delResult>0){
            return delResult;
        }else {
            return -1;
        }
    }
    /*获取所有记录数*/
    public Integer getJobCount(){
        Integer count=jobMapper.selectJobCount();
        if(count!=null&&count>0){
            return count;
        }else {
            return 0;
        }
    }

}
