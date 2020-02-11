package com.cwd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//    扩展底层WebMvc配置
@Configuration
public class WebMvcConfig {
    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
            WebMvcConfigurer webConfig=new WebMvcConfigurer() {
                //注册拦截器
                @Override
                public void addInterceptors(InterceptorRegistry registry) {
//                    registry.addInterceptor().addPathPatterns("/**").excludePathPatterns();
                }
            };
            return  webConfig;
    }
}
