package com.cwd.Mapper;

import com.cwd.Entity.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
/*处理用户模块的持久层*/
@Repository
public interface UserMapper {
    public User getUserById(int id);
}
