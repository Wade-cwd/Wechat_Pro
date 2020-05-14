package com.cwd.Utils;

import com.alibaba.fastjson.JSONObject;

/*
* layui工具
* */
public class LayUI {
    /*返回LayUI支持的数据格式*/
    public static<T> Object getLayUIFormatData(T data,int count){
        JSONObject jsonObject= (JSONObject) JSONObject.toJSON(data);
        JSONObject newJsonObj=new JSONObject();
        newJsonObj.put("code",0);
        newJsonObj.put("msg","");
        newJsonObj.put("count",count);
        newJsonObj.put("data",jsonObject.get("list"));
        return newJsonObj;
    }
}
