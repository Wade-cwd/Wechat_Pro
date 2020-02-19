package com.cwd.Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public interface JobMapper {
    //添加一条工作兼职记录
    @Autowired
    public void addOneJob();
}
