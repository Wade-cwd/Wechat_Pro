package com.cwd.Mapper;

import com.cwd.Entity.Found;
import org.apache.ibatis.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface FoundMapper {
    @Autowired
    List<Found> getFoundList();//获取寻物启事列表
    @Autowired
    void addFoundItem(Found lost);//添加一条寻物启事记录
    /*更新字段*/
    @Update("update found set ${fieldName}=#{value} where uid=#{uid} and openid=#{openid}")
    @ResultType(Integer.class)
    Integer updateFound(String fieldName,String value,String uid,String openid);
    /*删除记录*/
    @Delete("delete from found where uid=#{uid} and openid=#{openid}")
    @ResultType(Integer.class)
    Integer deleteFound(String uid,String openid);
    /*获取所有记录数量*/
    @Select("select count(*) from found")
    @ResultType(Integer.class)
    Integer selectFoundCount();
}
