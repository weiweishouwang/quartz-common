package com.zhw.demo.controller;

import com.zhw.quartz.bean.JobTriggerInfo;
import com.zhw.quartz.core.QuartzService;
import com.zhw.quartz.properties.TaskProperties;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * Created by zhw on 2020/7/29.
 * Quartz配置controller
 */
@RestController
public class JobTriggerController {

    @Autowired
    private QuartzService jobService;

    @Autowired
    private TaskProperties taskProperties;

    @RequestMapping(value = "/deleteJobAndTrigger")
    public List<JobTriggerInfo> deleteJobAndTrigger(String jobName) throws SchedulerException {
        jobService.deleteJobAndTrigger(taskProperties.getTriggerGroup(), taskProperties.getJobGroup(), jobName);
        return jobService.getJobAndTriggers(taskProperties.getTriggerGroup());
    }

    @RequestMapping(value = "/addJobAndTrigger")
    public List<JobTriggerInfo> addJobAndTrigger(String jobName, String cronEx, String description) throws ParseException, SchedulerException {
        jobService.deleteJobAndTrigger(taskProperties.getTriggerGroup(), taskProperties.getJobGroup(), jobName);
        jobService.addJobAndTrigger(taskProperties.getTriggerGroup(), taskProperties.getJobGroup(), jobName, cronEx, description);
        return jobService.getJobAndTriggers(taskProperties.getTriggerGroup());
    }

    @RequestMapping(value = "/getJobAndTriggers")
    public List<JobTriggerInfo> getJobAndTriggers() throws SchedulerException {
        return jobService.getJobAndTriggers(taskProperties.getTriggerGroup());
    }

    @RequestMapping(value = "/getJobAndTriggers2")
    public List<Map<String, Object>> getJobAndTriggers2() throws SchedulerException {
        return jobService.getJobAndTriggers2(taskProperties.getTriggerGroup());
    }

    @RequestMapping(value = "/execJob")
    @Async
    public String execJob(String jobName) {
        jobService.execJob(jobName);
        return "success";
    }


}
