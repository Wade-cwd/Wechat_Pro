package com.cwd.Entity.Test;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@PropertySource(value = {"classpath:Properties/person.properties"})
@Component
@ConfigurationProperties(prefix = "person")
public class Person {
    private String lastName;
    private String age;

    @Override
    public String toString() {
        Logger.getGlobal().info("姓名:"+this.lastName);
        return super.toString();
    }
}
