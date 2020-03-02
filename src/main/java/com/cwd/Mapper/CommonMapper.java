package com.cwd.Mapper;

import com.cwd.Entity.Certification;
import com.cwd.Entity.IdCard;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}
