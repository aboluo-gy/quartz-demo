package com.persagy.quartz.servie.impl;

import com.persagy.quartz.job.MyJob;
import com.persagy.quartz.job.Simjob;
import com.persagy.quartz.joblistener.MyOtherJobListener;
import com.persagy.quartz.joblistener.MyOtherTriggerListener;
import com.persagy.quartz.util.DateUtils;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.KeyMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.CronScheduleBuilder.monthlyOnDayAndHourAndMinute;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
@Service
public class CronSchedule {

    @Autowired
    @Qualifier("quartzScheduler")
    Scheduler quartzScheduler ;

    public void init() throws SchedulerException {
//        SchedulerFactory sf = new StdSchedulerFactory();
//        Scheduler quartzScheduler = sf.getScheduler();

        JobDetail job = newJob(Simjob.class)
                .withIdentity("job1", "group1")
                .build();
        Trigger trigger = newTrigger()
                .withIdentity("cron1", "group1")
                .startNow()
                .withSchedule(cronSchedule("0/10 * * * * ?")) // cronSchedule("0 0 15 5 * ?")fire on the 5th day of every month at 15:00
                .build();
        Trigger trigger2 = newTrigger()
                .withIdentity("cron2", "group1")
                .startNow()
                .withSchedule(cronSchedule("2/10 * * * * ?")) // monthlyOnDayAndHourAndMinute(5, 15, 0) fire on the 5th day of every month at 15:00
                .build();

        quartzScheduler.scheduleJob(job, trigger);
        quartzScheduler.scheduleJob(job, trigger2);

        //重复调用会报错 org.quartz.ObjectAlreadyExistsException: Unable to store Job
        Matcher<JobKey> matcher = KeyMatcher.keyEquals(job.getKey());
        quartzScheduler.getListenerManager().addJobListener(new MyOtherJobListener("myjob"),matcher);

        Matcher<TriggerKey> triggerMatcher = KeyMatcher.keyEquals(trigger.getKey());
        quartzScheduler.getListenerManager().addTriggerListener(new MyOtherTriggerListener("mytrigger"),triggerMatcher);
        HashSet set = new HashSet<Trigger>();
        set.add(trigger);
//        set.add(trigger2);
        quartzScheduler.scheduleJob(job,set,true);
        System.out.println(DateUtils.formatDate(trigger.getFireTimeAfter(new Date())));

    }
}
