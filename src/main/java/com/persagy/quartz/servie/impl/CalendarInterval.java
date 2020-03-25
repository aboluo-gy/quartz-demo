package com.persagy.quartz.servie.impl;

import com.persagy.quartz.job.MyJob;
import com.persagy.quartz.util.DateUtils;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;

import static org.quartz.CalendarIntervalScheduleBuilder.calendarIntervalSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;


/**
 * @author LuoGuangyi
 * @title: CalendarIntervalMain
 * @projectName spring-boot-2.0.3
 * @description: TODO
 * @date 2019/07/02 18:42
 */
@Service
public class CalendarInterval {

    @Autowired
    @Qualifier("quartzScheduler")
    Scheduler quartzScheduler ;
    private static final Logger LOGGER = LoggerFactory.getLogger(CalendarInterval.class);
    public void init() throws SchedulerException, ParseException {
//        // 获取一个调度器  quartzScheduler   DefaultQuartzScheduler
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        Scheduler scheduler2 = new StdSchedulerFactory().getScheduler();
        System.out.println("StdSchedulerFactory= "+scheduler);
        System.out.println("StdSchedulerFactory2= "+scheduler);
        System.out.println("AutowiredScheduler= "+quartzScheduler);
        Date date = DateUtils.parseDate("20200118112456");
        Date endDate = DateUtils.parseDate("20200118143356");

//       quartzScheduler = SpringUtil.getBean("quartzScheduler",StdScheduler.class);
        JobDetail job = newJob(MyJob.class)
                .withIdentity("Calendar", "group")
                .build();
        CalendarIntervalTrigger trigger = newTrigger()
                .withIdentity("Calendar", "group")
                .startAt(date)
                .endAt(endDate)
                .withSchedule(calendarIntervalSchedule()
                        .withIntervalInMinutes(1)
                )
                .build();
        LOGGER.info("calendar misfire:"+trigger.getMisfireInstruction());
//        sched.scheduleJob(job, trigger);
        HashSet set = new HashSet<Trigger>();
        set.add(trigger);
        quartzScheduler.scheduleJob(job,set,true);

    }

}
