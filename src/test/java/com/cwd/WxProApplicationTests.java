package com.cwd;

import com.cwd.Entity.Lost;
import com.cwd.Utils.JsonToEntityUtil;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;


@SpringBootTest
class WxProApplicationTests {

    @Autowired
    DataSource dataSource;

    @Test
    public  void contextLoads() throws SQLException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        Logger logger= LoggerFactory.getLogger(getClass());
        Lost lost = new Lost();
        JsonToEntityUtil<Lost> jsonToEntityUtil=new JsonToEntityUtil<>(lost);
//        jsonToEntityUtil
        logger.info("信息");

        logger.error("错误");
    }

}
