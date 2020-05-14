package com.cwd.Service.Commons;

import com.alibaba.fastjson.JSONObject;
import com.cwd.Entity.Certification;
import com.cwd.Entity.GlobalConfig;
import com.cwd.Entity.IdCard;
import com.cwd.Entity.Market;
import com.cwd.Mapper.CommonMapper;
import com.cwd.Utils.AliExpress;
import com.cwd.Utils.AliTool;
import com.cwd.Utils.FileUtil;
import com.cwd.Utils.HttpRequest.HttpRequest;
import com.cwd.Utils.KdniaoTrackQueryAPI;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class CommonService {
    @Autowired
    private Certification certification;
    @Autowired
    private CommonMapper commonMapper;
    @Autowired
    private AliTool aliTool;

    private  String KEY="a6e5940171ee40589f5aa0283df94107";
    //和风天气
    public  String getWeather(double longitude,double latitude){
        try {
            String resultJson=  new HttpRequest().open("https://free-api.heweather.net/s6/weather/now?location="
                    +longitude+","+latitude+"&key="+this.KEY,"GET");
            GlobalConfig.getLog(this.getClass()).info(resultJson);
            return resultJson;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
    public String getWeatherCity(String city){
        try {
            String resultJson=  new HttpRequest().open("https://free-api.heweather.net/s6/weather/now?location="
                    +city+"&key="+this.KEY,"GET");
            return  resultJson;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
    //快递鸟，每天500次查询上限,仅支持中通ZTO，申通STO，圆通YTO
    public String getExpress(String expressId,String expressNo){
        KdniaoTrackQueryAPI api=new KdniaoTrackQueryAPI();
        try {
            String jsonStr= api.getOrderTracesByJson(expressId,expressNo);
            GlobalConfig.getLog(this.getClass()).info("快递"+jsonStr);
            return jsonStr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    //阿里的快递接口
    public  String getAliExpress(String expressCode){
        String expressInfo=new AliExpress().getExpress(expressCode);
        Logger.getGlobal().info("快递信息:"+FileUtil.decodeUnicode(expressInfo));
        return FileUtil.decodeUnicode(expressInfo);
    }
    //添加图片路径
    public void appendImageString(String imagePath){
        String newImagePath="";
        if(this.certification.getImages()!=""){
            GlobalConfig.getLog(this.getClass()).info("不为空");
            newImagePath= this.certification.getImages().concat("&"+imagePath);
        }
        else {
            newImagePath= this.certification.getImages().concat(imagePath);
        }
        this.certification.setImages(newImagePath);
    }
    //添加一条实名认证审核记录
    public void addOneCheck(Certification certification){
        if(this.certification.getImages()!=""){
            GlobalConfig.getLog(this.getClass()).info("存在图片"+this.certification.getImages());
            certification.setImages(this.certification.getImages());//添加已上传的图片列表
        }
        else {
            GlobalConfig.getLog(CommonService.class).info("图片未加载");
        }
        //调用阿里接口读取身份证信息
        GlobalConfig.getLog(this.getClass()).info("身份证:"+certification.getIdCard());
       String jsonInfo= aliTool.getIdcardInfo(certification.getIdCard());
       JSONObject jsonObject=JSONObject.parseObject(jsonInfo);
       GlobalConfig.getLog(this.getClass()).info("获取身份证信息json:"+jsonObject.getString("data"));
       //写入数据库
        IdCard idCard= JSONObject.parseObject(jsonObject.getString("data"),IdCard.class);
        idCard.setOpenid(certification.getOpenid());//添加对应openid
        idCard.setUid(UUID.randomUUID().toString());
        GlobalConfig.getLog(this.getClass()).info("转为实体成功:"+idCard.toString());
        commonMapper.addIdCardInfo(idCard);
        //设置为审核中
        certification.setIsCheck(2);
        commonMapper.addOneCheck(certification);//执行上传
        //重置图片
        this.certification.setImages("");
    }
    //查询审核状态
    public int getCheckStatus(String openid){
        Integer status= commonMapper.getByOpenid(openid);
        GlobalConfig.getLog(this.getClass()).info("状态:"+status);

        if(status!=null){
            if(status==2&&isSubmitCheck(openid)=="OK"){
                //审核中
                return 2;
            }else if(status==-1&&isSubmitCheck(openid)=="OK"){
                //审核未通过
                return -1;
            }else if(status==1&&isSubmitCheck(openid)=="OK"){
                //审核通过
                return 1;
            }
            else{
                //未提交
                return 0;
            }
        }else {
            return 0;
        }

    }
    //是否提交过实名认证审核申请
    private String isSubmitCheck(String openid){
        Integer result=commonMapper.isSubmitCheck(openid);
        if(result!=null){
            if(result>0){
                return "OK";
            }
        }
        return "NO";
    }
    //获取实名认证列表
    public PageInfo<Certification> getCertList(int pageNo,int pageSize){
        PageHelper.startPage(pageNo,pageSize);//分页
        List<Certification> certifications=commonMapper.selectCerts();
        PageInfo<Certification> pageInfo=new PageInfo<>(certifications);
        return  pageInfo;
    }
    //获取实名认证列表数据条数
    public Integer getCertCount(){
        Integer count=commonMapper.selectCertCount();
        if(count!=null&&count>0){
            return count;
        }else {
            return 0;
        }
    }
}
