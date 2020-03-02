package com.cwd.Controller.News;

import com.cwd.Service.News.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("news")
public class NewsController {
    @Autowired
    private NewsService newsService;

    //获取新闻信息
    @PostMapping("/getNewsInfo/{index}/{start}/{count}")
    public String getNews(@PathVariable("index") int index,
                          @PathVariable("start") int start,
                          @PathVariable("count") int count){
        return newsService.getNewsJsonFromNet(index,start,count);
    }
}
