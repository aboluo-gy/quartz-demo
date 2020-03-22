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


/**
 * Description:   任务发单
 * Company: Persagy
 *
 * @author luoguangyi
 * @version 1.0
 * @since: 2019年8月2日: 下午6:03:12
 * Update By luoguangyi 2019年8月2日: 下午6:03:12
 */
@DisallowConcurrentExecution
@PersistJobDataAfterExecution
public class TaskSimpleJob extends QuartzJobBean {
    @Autowired
    Hello hello;
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskSimpleJob.class);

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        hello.index(context);
        try {
            Random random = new Random();
            Thread.sleep(random.nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
