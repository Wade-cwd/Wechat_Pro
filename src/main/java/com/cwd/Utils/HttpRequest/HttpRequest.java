package com.cwd.Utils.HttpRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
/*
* HTTP网络工具类
* */
public class HttpRequest {
    private final Logger logger= LoggerFactory.getLogger(HttpRequest.class);

    //通过GET请求网络获取数据
    public String openGET(String urlPath) throws IOException {
        URL url=new URL(urlPath);
        HttpURLConnection conn=(HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        InputStream inputStream=conn.getInputStream();
        InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
        BufferedReader reader=new BufferedReader(inputStreamReader);
        String line=null;
        String result="";
        while ((line=reader.readLine())!=null){
            logger.info("网络回调获取的数据:"+line);
            result+=line;
        }
        logger.info("返回结果字符串:"+result);
        return result;
 }
}
