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
public class WorkorderSimpleJob extends QuartzJobBean {
	@Autowired
	Hello hello;
	private static final Logger LOGGER = LoggerFactory.getLogger(WorkorderSimpleJob.class);
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
