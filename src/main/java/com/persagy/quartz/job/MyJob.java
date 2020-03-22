package com.persagy.quartz.job;

import com.persagy.quartz.servie.Hello;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Random;

@DisallowConcurrentExecution
@PersistJobDataAfterExecution
public class MyJob extends QuartzJobBean {

    @Autowired
    Hello hello;
    private static final Logger LOGGER = LoggerFactory.getLogger(MyJob.class);

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        //context.isRecovering() 是否是恢复执行的任务
//        LOGGER.error("errpr");
//        LOGGER.warn("warn");
//        LOGGER.info("qqqqq");
        hello.index(context);
        try {
            Random random = new Random();
            if(random.nextInt(10)>10){
                throw  new RuntimeException("job 执行发生异常");
            }
            Thread.sleep(random.nextInt(10000));

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
