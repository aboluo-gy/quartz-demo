package com.persagy.quartz.servie;


import com.persagy.quartz.util.DateUtils;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author LuoGuangyi
 * @title: Hello
 * @projectName spring-boot-helloworld
 * @description: TODO
 * @date 2019/08/12 17:53
 */
@Service
public class Hello {
    private static final Logger log = LoggerFactory.getLogger(Hello.class);

    public String index(JobExecutionContext context) {
//        log.info(context.getJobDetail().getKey()+"善良没用，你得漂亮，还得有钱！");
        int misfireInstruction = context.getTrigger().getMisfireInstruction();
        String nextTime = Objects.isNull(context.getNextFireTime())?"null":DateUtils.formatDate(context.getNextFireTime());
        log.info(context.getJobDetail().getKey()+"====["+ DateUtils.formatDate(context.getFireTime()) +"],["+ DateUtils.formatDate(context.getScheduledFireTime())+"]"+misfireInstruction+"[nextFireTime]"+nextTime);
        return "Hello World";
    }
}
