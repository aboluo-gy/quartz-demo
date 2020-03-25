package com.persagy.quartz.job;

import com.persagy.quartz.servie.Hello;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

public class Simjob implements Job {

    @Autowired
    Hello hello;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        hello.index(context);
    }
}
