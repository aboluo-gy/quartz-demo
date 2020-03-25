package com.persagy.quartz.servie.impl;

import com.persagy.quartz.job.MyJob;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashSet;

import static org.quartz.DateBuilder.dateOf;
import static org.quartz.DateBuilder.tomorrowAt;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @version v1.0
 * @Title: SimpleSchedule
 * @ProjectName spring-boot-examples
 * @Description: TODO
 * @Author LuoGuangyi
 * @Date: 2020/1/18 0018 12:03
 */
@Service
public class SimpleSchedule {
    @Autowired
    @Qualifier("quartzScheduler")
    Scheduler quartzScheduler ;


    public String init() throws SchedulerException {
        JobDetail job = newJob(MyJob.class)
                .withIdentity("job_simple", "group_sim")
                .build();
        Trigger trigger = newTrigger()
                .withIdentity("trigger_simple", "group_sim")
                .startAt(dateOf(15,35,11,18,1))
//                .endAt(dateOf(15,40,11,18,1))
                //tomorrowAt(15, 0, 0)  first fire time 15:00:00 tomorrow
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(20)
//                        .repeatForever()
                        .withRepeatCount(2)
                        .withMisfireHandlingInstructionNextWithExistingCount()
                )
                .build();
        HashSet set = new HashSet<Trigger>();
        set.add(trigger);
        quartzScheduler.scheduleJob(job,set,true);
        return "add simple!";
    }
}
