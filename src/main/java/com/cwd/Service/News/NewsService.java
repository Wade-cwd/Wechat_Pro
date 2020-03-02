package com.cwd.Service.News;

import com.cwd.Entity.GlobalConfig;
import com.cwd.Utils.HttpRequest.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class NewsService {

    @Autowired
    private GlobalConfig globalConfig;

    //获取新闻信息
    public String getNewsJsonFromNet(int index,int start,int count){
        String content= null;
        try {
            content = new HttpRequest().open("https://3g.163.com/touch/reconstruct/article/list/"
                    +globalConfig.getNewsType(index)
                    +"/"+start+"-"+count+".html","GET");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String str= content.substring(content.indexOf('['),content.lastIndexOf(']')+1);
        return  str;
    }
}
