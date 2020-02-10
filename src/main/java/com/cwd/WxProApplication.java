package com.cwd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class WxProApplication {

    public static void main(String[] args) {
        SpringApplication.run(WxProApplication.class, args);
    }

}
