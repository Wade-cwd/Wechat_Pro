package com.cwd.Controller.Market;

import com.cwd.Entity.GlobalConfig;
import com.cwd.Entity.Lost;
import com.cwd.Entity.Market;
import com.cwd.Service.Market.MarketService;
import com.cwd.Utils.FileUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/market")
public class MarketController {
    @Autowired
    private MarketService marketService;
    @Autowired
    private FileUtil fileUtil;


    //图片上传
    @PostMapping(value = "/uploadImage",consumes = {"multipart/form-data"})
    public void uploadImage( @RequestParam("file") MultipartFile multipartFiles){
        //图片写入服务器
        String filePath=fileUtil.writeFileToDirectory(multipartFiles);
        marketService.appendImageString(filePath);
        GlobalConfig.getLog(this.getClass()).info("图片写入成功...");
    }
    //上传表单数据
    @PostMapping("/submitForm")
    public  void submitForm(@RequestBody Market market){
        marketService.addOneMarket(market);
        GlobalConfig.getLog(this.getClass()).info("上传表单数据成功.....");
    }
    //
    @PostMapping("/getMarket/{pageNo}/{pageSize}")
    public Object getLostList(@PathVariable(value = "pageNo")int pageNo,
                              @PathVariable(value = "pageSize")int pageSize){
        PageInfo<Market> marketList=marketService.getMarketList(pageNo,pageSize);
        return  marketList;
    }
}
