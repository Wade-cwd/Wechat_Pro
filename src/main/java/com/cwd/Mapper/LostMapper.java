package com.cwd.Mapper;

import com.cwd.Entity.Lost;
import org.apache.ibatis.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface LostMapper {
    @Autowired
    List<Lost> getLostList();//获取失物招领列表
    @Autowired
    void addLostItem(Lost lost);//添加一条失物招领记录
    /*更新字段*/
    @Update("update lost set ${fieldName}=#{value} where uid=#{uid} and openid=#{openid}")
    @ResultType(Integer.class)
    Integer updateLost(String fieldName,String value,String uid,String openid);
    @Delete("delete from lost where uid=#{uid} and openid=#{openid}")
    @ResultType(Integer.class)
    Integer deleteLost(String uid,String openid);
    /*查询所有记录数*/
    @Select("select count(*) from lost")
    @ResultType(Integer.class)
    Integer selectLostCount();
}
