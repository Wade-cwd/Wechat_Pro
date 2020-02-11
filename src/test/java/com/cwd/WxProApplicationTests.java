package com.cwd;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


@SpringBootTest
class WxProApplicationTests {

    @Autowired
    DataSource dataSource;

    @Test
    public  void contextLoads() throws SQLException {
        Logger logger= LoggerFactory.getLogger(getClass());

        logger.trace("跟踪");
        logger.info("信息");
        logger.debug("debug日志");
        logger.error("错误");
    }

}
