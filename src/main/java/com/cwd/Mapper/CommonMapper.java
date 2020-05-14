package com.cwd.Mapper;

import com.cwd.Entity.Certification;
import com.cwd.Entity.IdCard;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommonMapper {
    //添加身份证审核
    @Autowired
    void addOneCheck(Certification certification);
    //查询审核状态
    Integer getByOpenid(String openid);
    //是否提交过审核
    Integer isSubmitCheck(String openid);
    //添加身份证信息
    void addIdCardInfo(IdCard idCard);
    //查询实名认证表数据
    @Select("select * from certification")
    @ResultType(Certification.class)
    public List<Certification> selectCerts();
    //查询实名认证表条数
    @Select("select count(*) from certification")
    @ResultType(Integer.class)
    public Integer selectCertCount();
    //更新实名认证字段
    @Update("update certification set ${fieldName}=#{value} where uid=#{uid} and openid=#{openid}")
    @ResultType(Integer.class)
    Integer updateTopic(String fieldName,String value,String uid,String openid);
}

