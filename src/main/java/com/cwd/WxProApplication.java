package com.cwd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;



@MapperScan(basePackages = "com.cwd.Mapper")//扫描mapper接口
@SpringBootApplication
public class WxProApplication {

    public static void main(String[] args) {
        SpringApplication.run(WxProApplication.class, args);
    }

}
