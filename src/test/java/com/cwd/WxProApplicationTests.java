package com.cwd;

import com.alibaba.fastjson.JSONObject;
import com.cwd.Entity.GlobalConfig;
import com.cwd.Entity.Lost;
import com.cwd.Entity.Test.Person;
import com.cwd.Utils.HttpRequest.HttpRequest;
import com.cwd.Utils.JsonToEntityUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class WxProApplicationTests {
    @Autowired
    private Person person;
    @Test
    public void test(){
        BufferedImage bufferedImage=new BufferedImage(200,200,BufferedImage.TYPE_INT_RGB);
        File file=null;
        String os_Name= System.getProperty("os.name");
        if(os_Name.contains("Windows")){
            File[] deviceName=File.listRoots();
            file=new File("A:\\"+ UUID.randomUUID().toString()+".png");
        }else if(os_Name.contains("Linux")){

        }
    }
    public static void drawImg(BufferedImage bi,String picType,File file){
        Graphics g=bi.getGraphics();
        g.setColor(new Color(12, 123, 88));
        g.setFont(new Font("宋体",Font.PLAIN,20));
        
    }

}
