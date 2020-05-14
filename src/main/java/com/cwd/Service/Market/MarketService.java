package com.cwd.Service.Market;

import com.cwd.Entity.GlobalConfig;
import com.cwd.Entity.Lost;
import com.cwd.Entity.Market;
import com.cwd.Mapper.MarketMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarketService {
    @Autowired
    private MarketMapper marketMapper;
    @Autowired
    private Market market;



    //发布一条二手产品记录
    public void addOneMarket(Market market){
        if(this.market.getImages()!=""){
            GlobalConfig.getLog(this.getClass()).info("存在图片"+this.market.getImages());
            market.setImages(this.market.getImages());//添加已上传的图片列表
        }
        marketMapper.addOneMarket(market);//执行上传
        //重置图片
        this.market.setImages("");
    }

    //添加图片路径
    public void appendImageString(String imagePath){
        String newImagePath="";
        if(this.market.getImages()!=""){
            GlobalConfig.getLog(this.getClass()).info("不为空");
            newImagePath= this.market.getImages().concat("&"+imagePath);
        }
        else {
            newImagePath= this.market.getImages().concat(imagePath);
        }
        this.market.setImages(newImagePath);
    }


    public PageInfo<Market> getMarketList(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo,pageSize);//分页
        List<Market> markets=marketMapper.getMarketList();
        PageInfo<Market> pageInfo=new PageInfo<>(markets);
        return pageInfo;
    }
    /*设置字段值*/
    public Integer setMarketField(String fieldName,String value,String uid,String openid){
        Integer resultCount=marketMapper.updateMarket(fieldName,value,uid,openid);
        if(resultCount!=null&&resultCount>0){
            return resultCount;
        }else{
            return -1;
        }
    }
    /*删除一条记录*/
    public Integer delOneMarket(String uid,String openid){
        Integer delResult=marketMapper.deleteMarket(uid,openid);
        if(delResult!=null&&delResult>0){
            return delResult;
        }else {
            return -1;
        }
    }
    /*获取所有记录数量*/
    public Integer getMarketCount(){
        Integer count=marketMapper.selectMarketCount();
        if(count!=null&&count>0){
            return count;
        }else {
            return 0;
        }
    }
}
