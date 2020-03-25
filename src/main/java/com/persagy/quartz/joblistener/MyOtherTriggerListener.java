package com.persagy.quartz.joblistener;

import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.listeners.TriggerListenerSupport;

public class MyOtherTriggerListener extends TriggerListenerSupport {

    private String name;

    public MyOtherTriggerListener(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext context) {
        System.out.println("MyOtherTriggerListener triggerFired()");
    }

    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {
        //return super.vetoJobExecution(trigger, context);
        return true;
    }
}