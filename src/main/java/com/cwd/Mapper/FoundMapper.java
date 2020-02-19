package com.cwd.Mapper;

import com.cwd.Entity.Found;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoundMapper {
    @Autowired
    List<Found> getFoundList();//获取寻物启事列表
    @Autowired
    void addFoundItem(Found lost);//添加一条寻物启事记录
}
