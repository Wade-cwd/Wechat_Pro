package com.cwd.Controller.Job;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cwd.Entity.GlobalConfig;
import com.cwd.Entity.Job;
import com.cwd.Entity.Lost;
import com.cwd.Service.Job.JobService;
import com.cwd.Utils.LayUI;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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
        job.setUid(UUID.randomUUID().toString());
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
        Integer count=jobService.getJobCount();
        return LayUI.getLayUIFormatData(jobs,count);
    }
    /*更新字段*/
    @PostMapping("/updateJobField/{fieldName}/{value}/{uid}/{openid}")
    public Integer postJobField(@PathVariable("fieldName")String fieldName,@PathVariable("value")String value,
            @PathVariable("uid")String uid,@PathVariable("openid") String openid){
            return jobService.setJobField(fieldName,value,uid,openid);
    }
    /*删除记录*/
    @PostMapping("/deleteOnJob/{uid}/{openid}")
    public  Integer postDelOneFound(@PathVariable("uid")String uid,@PathVariable("openid")String openid){
        return jobService.delOneFound(uid,openid);
    }

}
