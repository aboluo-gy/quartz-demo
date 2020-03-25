package com.persagy.quartz.joblistener;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.listeners.JobListenerSupport;

public class MyOtherJobListener extends JobListenerSupport {

    private String name;

    public MyOtherJobListener(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    @Override
    public void jobWasExecuted(JobExecutionContext context,
                               JobExecutionException jobException) {
        System.out.println("MyOtherJobListener jobWasExecuted()");
    }
}
