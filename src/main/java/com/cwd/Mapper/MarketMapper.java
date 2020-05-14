package com.cwd.Mapper;

import com.cwd.Entity.Lost;
import com.cwd.Entity.Market;
import org.apache.ibatis.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MarketMapper {

    //添加一条消息
    @Autowired
    void addOneMarket(Market market);

    @Autowired
    List<Market> getMarketList();
    /*更新字段*/
    @Update("update market set ${fieldName}=#{value} where uid=#{uid} and openid=#{openid}")
    @ResultType(Integer.class)
    Integer updateMarket(String fieldName,String value,String uid,String openid);
    /*删除记录*/
    @Delete("delete from market where uid=#{uid} and openid=#{openid}")
    @ResultType(Integer.class)
    Integer deleteMarket(String uid,String openid);
    /*获取所有记录数*/
    @Select("select count(*) from market")
    @ResultType(Integer.class)
    Integer selectMarketCount();
}
