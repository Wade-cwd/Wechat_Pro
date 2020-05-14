package com.cwd.Service.LostAndFound;

import com.cwd.Entity.Found;
import com.cwd.Entity.GlobalConfig;
import com.cwd.Entity.Lost;
import com.cwd.Mapper.FoundMapper;
import com.cwd.Utils.FileUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    public PageInfo<Found> getFoundList(int pageNo,int pageSize){
        PageHelper.startPage(pageNo,pageSize);//分页
        List<Found> founds=foundMapper.getFoundList();
        PageInfo<Found> pageInfo=new PageInfo<>(founds);
        return pageInfo;
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
    /*更新字段*/
    public Integer setFoundField(String fieldName,String value,String uid,String openid){
        Integer resultCount=foundMapper.updateFound(fieldName,value,uid,openid);
        if(resultCount!=null&&resultCount>0){
            return resultCount;
        }else {
            return -1;
        }
    }
    /*删除一条记录*/
    public Integer delOneFound(String uid,String openid){
        Integer delFoundResult=foundMapper.deleteFound(uid,openid);
        if(delFoundResult!=null&&delFoundResult>0){
            return delFoundResult;
        }else {
            return -1;
        }
    }
    /*获取所有记录数量*/
    public  Integer getFoundCount(){
        Integer count=foundMapper.selectFoundCount();
        if(count!=null&&count>0){
            return count;
        }else {
            return 0;
        }
    }
}
