package com.cwd.Controller.Market;

import com.alibaba.fastjson.JSONObject;
import com.cwd.Entity.GlobalConfig;
import com.cwd.Entity.Lost;
import com.cwd.Entity.Market;
import com.cwd.Service.Market.MarketService;
import com.cwd.Utils.FileUtil;
import com.cwd.Utils.LayUI;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

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
        market.setUid(UUID.randomUUID().toString());
        marketService.addOneMarket(market);
        GlobalConfig.getLog(this.getClass()).info("上传表单数据成功.....");
    }
    //
    @PostMapping("/getMarket/{pageNo}/{pageSize}")
    public Object getMarketList(@PathVariable(value = "pageNo")int pageNo,
                              @PathVariable(value = "pageSize")int pageSize){
        PageInfo<Market> marketList=marketService.getMarketList(pageNo,pageSize);
        return  marketList;
    }
    //获取二手市场数据LayUI格式
    @PostMapping("/getMarket_LayUI/{pageNo}/{pageSize}")
    public Object getMarketList_LayUI(@PathVariable(value = "pageNo")int pageNo,
                                @PathVariable(value = "pageSize")int pageSize){
        PageInfo<Market> marketList=marketService.getMarketList(pageNo,pageSize);
        Integer count=marketService.getMarketCount();
        return LayUI.getLayUIFormatData(marketList,count);
    }
    /*更新字段*/
    @PostMapping("/updateMarketField/{fieldName}/{value}/{uid}/{openid}")
    public Integer postMarketField(@PathVariable("fieldName")String fieldName,@PathVariable("value")String value,
                                   @PathVariable("uid")String uid,@PathVariable("openid")String openid)
    {
        return  marketService.setMarketField(fieldName,value,uid,openid);
    }
    /*删除一条记录*/
    @PostMapping("/deleteOneMarket/{uid}/{openid}")
    public Integer postDelOneMarket(@PathVariable("uid")String uid,@PathVariable("openid")String openid){
        return marketService.delOneMarket(uid,openid);
    }


}
