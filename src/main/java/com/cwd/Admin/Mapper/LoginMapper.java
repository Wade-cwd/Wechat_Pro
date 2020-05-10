package com.cwd.Admin.Mapper;

import com.cwd.Entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface LoginMapper {
    @Select("select count(*) from admin where userName=#{userName} and password=#{password}")
    @ResultType(Integer.class)
    Integer getAdminAccount(Admin admin);
    @Select("select * from admin where userName=#{userName} and password=#{password}")
    @ResultType(Admin.class)
    Admin selectAdmin(Admin admin);
    @Select("select password from admin where userName=#{userName}")
    @ResultType(String.class)
    String selectPasswordByUserName(String userName);
    @Select("select * from admin where userName=#{userName}")
    @ResultType(Admin.class)
    Admin selectAdminByUserName(@Param("userName") String userName);

}
