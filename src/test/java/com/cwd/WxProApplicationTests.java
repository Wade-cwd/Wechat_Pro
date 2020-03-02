package com.cwd;

import com.alibaba.fastjson.JSONObject;
import com.cwd.Entity.GlobalConfig;
import com.cwd.Entity.Lost;
import com.cwd.Utils.HttpRequest.HttpRequest;
import com.cwd.Utils.JsonToEntityUtil;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;


@SpringBootTest
class WxProApplicationTests {

    @Autowired
    DataSource dataSource;
    @Autowired
    GlobalConfig globalConfig;

    @Test
    public  void contextLoads() throws SQLException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        String KEY="a6e5940171ee40589f5aa0283df94107";
        try {
            String resultJson=  new HttpRequest().open("https://free-api.heweather.net/s6/weather/now?location="
                    +"德化"+"&key="+KEY,"GET");
            GlobalConfig.getLog(this.getClass()).info(resultJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
