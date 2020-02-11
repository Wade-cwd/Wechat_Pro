package com.cwd.mybatis.GeneratorTool;


import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
//mybatis逆向工程启动类
public class GeneratorDisplay{
    public void generator() throws Exception {
        List<String> warnings = new ArrayList<>();
        boolean overwrite = true;
        //指定逆向工程配置文件
        File configFile = new File(GeneratorDisplay.class.getResource("/generatorConfig.xml").getFile());
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,callback,warnings);
        myBatisGenerator.generate(null);
    }

    public static void main(String[] args) throws Exception {
        GeneratorDisplay generator = new GeneratorDisplay();
        generator.generator();
    }
}
