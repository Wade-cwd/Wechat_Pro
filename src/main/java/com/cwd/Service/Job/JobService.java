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

}
