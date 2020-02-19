package com.cwd.Service.LostAndFound;

import com.cwd.Entity.Found;
import com.cwd.Entity.GlobalConfig;
import com.cwd.Entity.Lost;
import com.cwd.Mapper.FoundMapper;
import com.cwd.Utils.FileUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class FoundService {
    private final Logger logger= LoggerFactory.getLogger(LostService.class);
    @Autowired
    private GlobalConfig globalConfig;
    @Autowired
    private FileUtil fileUtil;
    @Autowired
    private Found found;
    @Autowired
    private FoundMapper foundMapper;
    //处理寻物启事列表业务
    public List<Found> getFoudnList(){
        return foundMapper.getFoundList();
    }
    //添加一条寻物启事列表业务
    public void addFoundItem(Found found){
        foundMapper.addFoundItem(found);
        logger.info("FoundService:======添加寻物启事记录成功...");
    }
    //将json数据写入Found中
    public Found jsonToFound(String jsonString){
        if(jsonString!=null){
            ObjectMapper objectMapper=new ObjectMapper();
            try {
                found= objectMapper.readValue(jsonString,Found.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return found;
    }
}
