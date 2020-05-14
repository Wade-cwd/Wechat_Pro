package com.cwd.Controller.LostFound;


import com.alibaba.fastjson.JSONObject;
import com.cwd.Entity.GlobalConfig;
import com.cwd.Entity.Lost;
import com.cwd.Service.LostAndFound.LostService;
import com.cwd.Utils.FileUtil;
import com.cwd.Utils.LayUI;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.github.pagehelper.PageInfo;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@RestController
@RequestMapping("/lost")
public class LostController {
    @Autowired
    private GlobalConfig globalConfig;

    @Autowired
    private FileUtil fileUtil;

    @Autowired
    private LostService lostService;
    /*
    * 返回失物招领列表，分页每页10条
    * */
    @PostMapping("/getLost/{pageNo}/{pageSize}")
    public Object getLostList(@PathVariable(value = "pageNo")int pageNo,
                              @PathVariable(value = "pageSize")int pageSize){
        PageInfo<Lost> losts=lostService.getLostList(pageNo,pageSize);
        return  losts;
    }
    /*
     * 返回失物招领列表，分页每页10条,标准layUi框架json格式
     * */
    @PostMapping("/getLostJson/{pageNo}/{pageSize}")
    public Object getLostListJson(@PathVariable(value = "pageNo")int pageNo,
                              @PathVariable(value = "pageSize")int pageSize){
        PageInfo<Lost> losts=lostService.getLostList(pageNo,pageSize);
        Logger.getGlobal().info("失物招领分页格式:"+losts.toString());
        Integer count=lostService.getJobCount();
        return LayUI.getLayUIFormatData(losts,count);
    }
    /*
    * 获取前端数据文件,写入本地数据库
    * */
    @PostMapping(value = "/submitForm",consumes ={"multipart/form-data","application/json"} )
    public void submitForm(HttpServletRequest request ,@RequestParam("file") MultipartFile images) throws IOException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ParseException {
        String formData=request.getParameter("form_data");
        //处理表单数据
        Lost lost= lostService.jsonToLost(formData);
        Logger.getGlobal().info(lost.toString());
        //文件写入服务器文件系统
        String imgName= fileUtil.writeFileToDirectory(images);
        //数据写入数据库
        lost.setImage(imgName);
        lost.setUid(UUID.randomUUID().toString());
        Logger.getGlobal().info("图片"+lost.getImage());
        lostService.addLostItem(lost);
    }
    //下载文件请求
    @GetMapping(value = "/downloadImage/{image}")
    public void downImage(HttpServletResponse response,@PathVariable("image") String image)  {
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
    /*更新字段*/
    @PostMapping("/updateLostField/{fieldName}/{value}/{uid}/{openid}")
    public Integer postUpdateField(@PathVariable("fieldName")String fieldName,@PathVariable("value")String value,
                                   @PathVariable("uid")String uid,@PathVariable("openid")String openid)
    {
        return  lostService.setLostField(fieldName,value,uid,openid);
    }
    /*删除记录*/
    @PostMapping("/deleteOneLost/{uid}/{openid}")
    public Integer postDelOneLost(@PathVariable("uid")String uid,@PathVariable("openid")String openid){
        return lostService.delOneLost(uid,openid);
    }

}
