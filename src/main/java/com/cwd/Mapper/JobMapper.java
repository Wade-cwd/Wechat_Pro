package com.cwd.Mapper;

import com.cwd.Entity.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobMapper {
    //添加一条工作兼职记录
    @Autowired
     void addOneJob(Job job);
    @Autowired
    List<Job> getJobList();
}
