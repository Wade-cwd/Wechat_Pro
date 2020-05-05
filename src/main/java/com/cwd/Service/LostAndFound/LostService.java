package com.cwd.Service.LostAndFound;

import com.alibaba.fastjson.JSONObject;
import com.cwd.Entity.GlobalConfig;
import com.cwd.Entity.Lost;
import com.cwd.Mapper.LostMapper;
import com.cwd.Utils.FileUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.List;
import java.util.UUID;

/*失物招领业务层
* */
@Service
public class LostService {
    private final Logger logger= LoggerFactory.getLogger(LostService.class);
    @Autowired
    private GlobalConfig globalConfig;
    @Autowired
    private FileUtil fileUtil;
    @Autowired
    private LostMapper lostMapper;
    @Autowired
    private  Lost lost;

    //处理失物招领列表业务
    public PageInfo<Lost> getLostList(int pageNo,int pageSize){
        PageHelper.startPage(pageNo,pageSize);//PageHelper分页功能，需要写在获取数据函数前
        List<Lost> losts=lostMapper.getLostList();
        PageInfo<Lost> pageInfo=new PageInfo<>(losts);
        return pageInfo;
    }
    //添加一条失物招领列表业务
    public void addLostItem(Lost lost){
        lostMapper.addLostItem(lost);
        logger.info("LostService:======添加失物招领记录成功...");
    }
    //将json数据写入Lost中
    public Lost jsonToLost(String jsonString){
        if(jsonString!=null){
            ObjectMapper objectMapper=new ObjectMapper();
            try {
                lost= objectMapper.readValue(jsonString,Lost.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return lost;
    }

}
