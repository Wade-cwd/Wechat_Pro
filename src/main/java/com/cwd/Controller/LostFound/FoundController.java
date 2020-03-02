package com.cwd.Controller.LostFound;

import com.cwd.Entity.Found;
import com.cwd.Entity.GlobalConfig;
import com.cwd.Entity.Lost;
import com.cwd.Service.LostAndFound.FoundService;
import com.cwd.Utils.FileUtil;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;

@RestController
@RequestMapping("/found")
public class FoundController {
    private final Logger logger= LoggerFactory.getLogger(LostController.class);

    @Autowired
    private FileUtil fileUtil;
    @Autowired
    private FoundService foundService;
    @Autowired
    private GlobalConfig globalConfig;
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
    /*
     * 返回寻物启事列表，分页每页10条
     * */
    @PostMapping("/getFound/{pageNo}/{pageSize}")
    public Object getFoundList(@PathVariable(value = "pageNo")int pageNo,
                              @PathVariable(value = "pageSize")int pageSize){
        PageInfo<Found> founds=foundService.getFoundList(pageNo,pageSize);
        return  founds;
    }
    //下载文件请求
    @GetMapping(value = "/downloadImage/{image}")
    public void downImage(HttpServletResponse response, @PathVariable("image") String image)  {
        response.setContentType("application/octet-stream");//设置返回类型
        //获取本地文件
        File file=new File(globalConfig.getLocalFilePath()+image);
        if(file.exists()){
            GlobalConfig.getLog(this.getClass()).info("文件存在");
            try {
                FileInputStream fileInputStream=new FileInputStream(file);//文件读取流
                byte[] readBytes=new byte[1024];//一次读取1024字节
                BufferedInputStream bufferedInputStream=new BufferedInputStream(fileInputStream);
                while ((bufferedInputStream.read(readBytes))!=-1){
                    response.getOutputStream().write(readBytes);
                }
                bufferedInputStream.close();
                fileInputStream.close();
                response.setStatus(200);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }}

    }
}
