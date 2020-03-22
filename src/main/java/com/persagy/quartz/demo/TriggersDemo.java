package com.persagy.quartz.demo;


import com.persagy.quartz.job.MyJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.HashSet;

import static org.quartz.CalendarIntervalScheduleBuilder.calendarIntervalSchedule;
import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.CronScheduleBuilder.monthlyOnDayAndHourAndMinute;
import static org.quartz.DateBuilder.tomorrowAt;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class TriggersDemo {

    public static void main(String[] args) throws SchedulerException {
//        simpleDemo
//        CronDemo();
//        CalendarIntervalTriggerDemo();
    }

    private static void simpleDemo() throws SchedulerException {
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();

        JobDetail job = newJob(MyJob.class)
                .withIdentity("job1", "group1")
                .build();
        Trigger trigger = newTrigger()
                .withIdentity("trigger3", "group1")
                .startAt(tomorrowAt(15, 0, 0))  // first fire time 15:00:00 tomorrow
                        .withSchedule(simpleSchedule()
                                .withIntervalInHours(14 * 24) // interval is actually set at 14 * 24 hours' worth of milliseconds
                                .repeatForever())
                        .build();

        sched.scheduleJob(job, trigger);
        HashSet set = new HashSet<Trigger>();
        set.add(trigger);
        sched.scheduleJob(job,set,true);
    }

    private static void CronDemo() throws SchedulerException {
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();

        JobDetail job = newJob(MyJob.class)
                .withIdentity("job1", "group1")
                .build();
        Trigger trigger = newTrigger()
                .withIdentity("trigger3", "group1")
                .startNow()
                .withSchedule(cronSchedule("0 0 15 5 * ?")) // fire on the 5th day of every month at 15:00
                .build();
        Trigger trigger2 = newTrigger()
                .withIdentity("trigger3", "group1")
                .startNow()
                .withSchedule(monthlyOnDayAndHourAndMinute(5, 15, 0)) // fire on the 5th day of every month at 15:00
                .build();

        sched.scheduleJob(job, trigger);
        HashSet set = new HashSet<Trigger>();
        set.add(trigger);
        sched.scheduleJob(job,set,true);
    }



    private static void CalendarIntervalTriggerDemo() throws SchedulerException {
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();

        JobDetail job = newJob(MyJob.class)
                .withIdentity("job1", "group1")
                .build();
        CalendarIntervalTrigger trigger = newTrigger()
                .withIdentity("trigger3", "group1")
                .startNow()
                .withSchedule(calendarIntervalSchedule()
                        .withIntervalInSeconds(20))
                .build();
//        sched.scheduleJob(job, trigger);
        HashSet set = new HashSet<Trigger>();
        set.add(trigger);
        sched.scheduleJob(job,set,true);
        sched.start();
    }
}
