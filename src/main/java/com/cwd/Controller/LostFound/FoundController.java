package com.cwd.Controller.LostFound;

import com.cwd.Entity.Found;
import com.cwd.Service.LostAndFound.FoundService;
import com.cwd.Utils.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;

@Controller
@RequestMapping("/found")
public class FoundController {
    private final Logger logger= LoggerFactory.getLogger(LostController.class);

    @Autowired
    private FileUtil fileUtil;
    @Autowired
    private FoundService foundService;
    /*
     * 获取前端数据文件
     * */
    @PostMapping(value = "/submitForm",consumes ={"multipart/form-data","application/json"} )
    public void submitForm(HttpServletRequest request , @RequestParam("file") MultipartFile images) throws IOException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ParseException {
        String formData=request.getParameter("form_data");
        //表单数据
        Found found= foundService.jsonToFound(formData);
        logger.info(found.toString());
        //文件写入文件夹
        String imgName= fileUtil.writeFileToDirectory(images);
        //写入数据库
        found.setImage(imgName);
        logger.info("图片"+found.getImage());
        foundService.addFoundItem(found);
    }
}
