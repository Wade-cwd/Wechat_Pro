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

    //通过网络获取数据
    public String open(String urlPath,String requestType) throws IOException {
        URL url=new URL(urlPath);//连接对象
        HttpURLConnection conn=(HttpURLConnection) url.openConnection();//打开连接
        conn.setRequestMethod(requestType);//设置请求类型
        InputStream inputStream=conn.getInputStream();//获取输入流
        InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
        BufferedReader reader=new BufferedReader(inputStreamReader);
        String line=null;
        String result="";
        while ((line=reader.readLine())!=null){//写入网络数据
            logger.info("网络回调获取的数据:"+line);
            result+=line;
        }
        reader.close();
        inputStreamReader.close();
        inputStream.close();
        conn.disconnect();
        return result;
 }
}
