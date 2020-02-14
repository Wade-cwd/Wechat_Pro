package com.cwd.Repository;

import com.cwd.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

//继承接口来完成数据库操作
public interface UserRepository extends JpaRepository<User,Integer> {
}
