package com.cwd.Controller.LostController;


import com.alibaba.fastjson.JSONObject;
import com.cwd.Entity.Lost;
import com.cwd.Service.LostAndFound.LostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LostController {
    private final Logger logger=LoggerFactory.getLogger(LostController.class);


    @Autowired
    private LostService lostService;

    /*
    * 获取失物招领列表
    * */
    @PostMapping("/getlosts")
    public Object getLostList(){
        List<Lost> losts=lostService.getLostList();
        logger.info(losts.get(0).toString());
        return  lostService.getLostList();
    }
    /*
    * 拿到前端上传的数据文件
    * */
    @PostMapping("/submitForm")
    public  void submitForm(@RequestBody Lost lost){
        logger.info("正在获取数据..."+JSONObject.toJSONString(lost));
        lostService.addLostItem(lost);
        logger.info("添加一条失物招领成功>>>");
    }
    /*
    * 上传的本地资源
    * */
    @PostMapping("/uploadFile")
    public void uploadFile(){
        logger.info("正在获取文件...");
    }
}
