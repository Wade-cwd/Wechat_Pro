package com.cwd.Utils;

import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
* json对象注入实体类工具类
* */
public class JsonToEntityUtil<T extends Object> {
    private T type;
    public JsonToEntityUtil(){}
    public JsonToEntityUtil(T entity){
        this.type=entity;
    }
    public void setInject(JSONObject jsonObject) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException, ParseException {

        if(this.type!=null){
//            JSONObject jsonObject= (JSONObject) JSONObject.toJSON(this.type);
            System.out.println("需要解析的json:"+jsonObject);
            Class<T> entityClass= (Class<T>) this.type.getClass();//获取对象
            Constructor<T> cons=entityClass.getConstructor();
            T target=cons.newInstance();//实例化目标对象
            //获取所有的方法
            Method[] methods=entityClass.getDeclaredMethods();
            //取消访问权限检查,遍历所有方法
            for(int i=0;i<methods.length;++i){
                methods[i].setAccessible(false);
                //拿到set开头的方法
               if(methods[i].getName().startsWith("set")){
                   System.out.println("要执行的方法:"+methods[i].getName());
                   String properrtieStr=methods[i].getName().substring(3).toLowerCase();//得到属性名
                   if(!jsonObject.containsKey(properrtieStr)) continue;
                       System.out.println("存在key:"+properrtieStr+"----"+jsonObject.containsKey(properrtieStr));
                       //获取方法
                       Method method = methods[i];
                       //获取参数类型
                       Type type= method.getGenericParameterTypes()[0];
                        System.out.println("参数类型:"+type.getTypeName());
                        //得到json值
                    Object jsonValue=jsonObject.get(properrtieStr);
                        if(type.getTypeName().equals("java.sql.Date")){
                            continue;
//                            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
//                            Date date= simpleDateFormat.parse(jsonValue.toString());
//                            method.invoke(target, date );
                        }
                       //开始调用方法把json对应的值设置到实体类中
                       method.invoke(target, jsonValue );
                       System.out.println("调用完成...");
//                   }
               }
            }
        }
    }
}
