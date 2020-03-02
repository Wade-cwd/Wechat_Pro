package com.cwd.Mapper;

import com.cwd.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
/*处理用户模块的持久层*/
@Repository
public interface UserMapper {
    //获取用户
     User getUserById(int id);
    //添加用户
    @Autowired
    void addOneUser(User user);
    //根据openid返回用户数量
    int getByOpenidCount(String openid);

}
