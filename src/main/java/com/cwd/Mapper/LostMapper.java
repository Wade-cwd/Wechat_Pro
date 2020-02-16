package com.cwd.Mapper;

import com.cwd.Entity.Lost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LostMapper {
    @Autowired
    List<Lost> getLostList();//获取失物招领列表
    @Autowired
    void addLostItem(Lost lost);//添加一条失物招领记录
}
