package com.cwd.config;

import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
//mybatis自定义配置
public class MybatisConfig {

    @Bean
    public ConfigurationCustomizer configurationCustomizer(){
       return new ConfigurationCustomizer(){

            @Override
            public void customize(Configuration configuration) {
                //开启驼峰命名
                configuration.setMapUnderscoreToCamelCase(true);
            }
        };
    }
}
