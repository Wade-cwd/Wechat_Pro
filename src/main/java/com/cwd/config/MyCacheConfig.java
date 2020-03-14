package com.cwd.config;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.Arrays;


/*缓存配置*/
@Configuration
public class MyCacheConfig {
    /*自定义key生成策略*/
    @Bean("cwdKeyGenerator")
    public KeyGenerator keyGenerator(){
       return new KeyGenerator(){
            @Override
            public Object generate(Object target, Method method, Object... params) {
                return method.getName()+"["+ Arrays.asList(params).toString()+"]";
            }
        };
    }
}
