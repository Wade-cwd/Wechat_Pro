package com.cwd.Controller.Job;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cwd.Entity.GlobalConfig;
import com.cwd.Entity.Job;
import com.cwd.Entity.Lost;
import com.cwd.Service.Job.JobService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/job")
public class JobController {
    @Autowired
    private JobService jobService;

    /*
    * 添加工作兼职
    * */
    @PostMapping("/addJob")
    public void addJob(@RequestBody Job job){
        GlobalConfig.getLog(this.getClass()).info(job.toString());
        jobService.addOneJobRecord(job);
        GlobalConfig.getLog(this.getClass()).info("添加成功");
    }
    /*
     * 返回兼职工作列表，分页每页10条
     * */
    @PostMapping("/getJob/{pageNo}/{pageSize}")
    public Object getJobList(@PathVariable(value = "pageNo")int pageNo,
                              @PathVariable(value = "pageSize")int pageSize){
        PageInfo<Job> jobs=jobService.getJobList(pageNo,pageSize);
        return  jobs;
    }
    /*返回Layui标准格式Json分页数据
    * */
    @PostMapping("/getJob_LayUI/{pageNo}/{pageSize}")
    public Object getJobListJson(@PathVariable(value = "pageNo")int pageNo,
                             @PathVariable(value = "pageSize")int pageSize){
        PageInfo<Job> jobs=jobService.getJobList(pageNo,pageSize);
        Object objectData=  JSONObject.toJSON(jobs);
        JSONObject jsonObject= (JSONObject) objectData;
        JSONObject newJsonObj=new JSONObject();
        newJsonObj.put("code",0);
        newJsonObj.put("msg","");
        newJsonObj.put("count",1000);
        newJsonObj.put("data",jsonObject.get("list"));
        return  newJsonObj;
    }

}
