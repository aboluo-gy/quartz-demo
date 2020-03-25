package com.persagy.quartz.servie.impl;

import com.persagy.quartz.job.MyJob;
import org.quartz.*;
import org.springframework.stereotype.Service;

@Service
public class DailyTimeIntervalTriggerSchedule {

    public void init() throws SchedulerException {
        SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();
        // 获取一个调度器
        Scheduler sched = schedFact.getScheduler();
        System.out.println(sched.getSchedulerName());
        JobDetail job = JobBuilder.newJob(MyJob.class).withIdentity("job1", "group1").build();
        // 每两秒执行
        DailyTimeIntervalTrigger trigger = TriggerBuilder.newTrigger().withIdentity("triggerDai1", "group1").withSchedule(
                DailyTimeIntervalScheduleBuilder.dailyTimeIntervalSchedule().withInterval(2, DateBuilder.IntervalUnit.SECOND)
        ).build();
        DailyTimeIntervalTrigger trigger2 = TriggerBuilder.newTrigger()
//                .forJob(job.getKey())
                .withIdentity("triggerDai2", "group1")
                .withSchedule(
                DailyTimeIntervalScheduleBuilder.dailyTimeIntervalSchedule().withInterval(2, DateBuilder.IntervalUnit.SECOND)
        ).build();
        sched.scheduleJob(job, trigger);
//        sched.scheduleJob(job, trigger2);
        //sched.scheduleJob(trigger2);
        // 调度启动
        sched.start();
    }
}
