package com.cwd.Controller.LostFound;


import com.cwd.Entity.Lost;
import com.cwd.Service.LostAndFound.LostService;
import com.cwd.Utils.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/lost")
public class LostController {
    private final Logger logger=LoggerFactory.getLogger(LostController.class);

    @Autowired
    private FileUtil fileUtil;

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
    * 获取前端数据文件
    * */
    @PostMapping(value = "/submitForm",consumes ={"multipart/form-data","application/json"} )
    public void submitForm(HttpServletRequest request ,@RequestParam("file") MultipartFile images) throws IOException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ParseException {
        String formData=request.getParameter("form_data");
        //表单数据
        Lost lost= lostService.jsonToLost(formData);
        logger.info(lost.toString());
        //文件写入文件夹
        String imgName= fileUtil.writeFileToDirectory(images);
        //写入数据库
        lost.setImage(imgName);
        logger.info("图片"+lost.getImage());
        lostService.addLostItem(lost);
    }}
