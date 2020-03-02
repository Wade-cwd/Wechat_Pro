package com.cwd.Controller.Commons;

import com.cwd.Entity.Certification;
import com.cwd.Entity.GlobalConfig;
import com.cwd.Service.Commons.CommonService;
import com.cwd.Utils.AliTool;
import com.cwd.Utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/common")
public class CommonController {
    @Autowired
    private GlobalConfig globalConfig;
    @Autowired
    private FileUtil fileUtil;


    @Autowired
    private CommonService commonService;
    //根据经纬度获取天气数据
    @PostMapping("/getWeather/{longitude}/{latitude}")
    public String getWeather(@PathVariable("longitude") double longitude,
                             @PathVariable("latitude") double latitude){
        GlobalConfig.getLog(this.getClass()).info(longitude+","+latitude);
        return commonService.getWeather(longitude,latitude);
    }
    //根据城市名获取天气数据
    @PostMapping("/getCityWeather/{city}")
    public String getCityWeather(@PathVariable("city") String city){
        return commonService.getWeatherCity(city);
    }
    //快递物流查询expressId:快递类型
    @PostMapping("/getExpress/{expressId}/{expressNo}")
    public String getExpress(@PathVariable("expressId") String expressId,
                             @PathVariable("expressNo") String expressNo){
        return commonService.getAliExpress(expressNo,expressId);
    }
    //身份证图片上传
    @PostMapping(value = "/uploadCertification",consumes = {"multipart/form-data"})
    public void uploadImage( @RequestParam("file") MultipartFile multipartFiles){
        //图片写入服务器
        String filePath=fileUtil.writeFileToDirectory(multipartFiles);
         commonService.appendImageString(filePath);
        GlobalConfig.getLog(this.getClass()).info("图片写入成功...");
    }
    //身份证信息验证申请数据
    @PostMapping(value = "/check")
    public void checkCert(@RequestBody Certification certification){

        GlobalConfig.getLog(this.getClass()).info("certification:"+certification.toString());
        commonService.addOneCheck(certification);
        GlobalConfig.getLog(this.getClass()).info("上传表单数据成功.....");
    }
    //身份证审核结果查询
    @PostMapping("/checkResult/{openid}")
    public void  checkResult(@PathVariable("openid") String openid){
//        return commonService.getCheckStatus(openid);
    }
    //实名认证审核是否提交过
    @PostMapping("/checkStatus/{openid}")
    public int checkStatus(@PathVariable("openid") String openid){
        return commonService.getCheckStatus(openid);
    }
}
