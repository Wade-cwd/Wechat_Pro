package com.cwd.Mapper;

import com.cwd.Entity.Job;
import org.apache.ibatis.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface JobMapper {
    //添加一条工作兼职记录
    @Autowired
     void addOneJob(Job job);
    @Autowired
    List<Job> getJobList();
    /*更新字段*/
    @Update("update job set ${fieldName}=#{value} where uid=#{uid} and openid=#{openid}")
    @ResultType(Integer.class)
    Integer updateFound(String fieldName,String value,String uid,String openid);
    /*删除一条记录*/
    @Delete("delete from job where uid=#{uid} and openid=#{openid}")
    @ResultType(Integer.class)
    Integer deleteFound(String uid,String openid);
    /*查询所有记录数*/
    @Select("select count(*) from job")
    @ResultType(Integer.class)
    public Integer selectJobCount();
}
