package com.cwd.Controller.Job;

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

}
