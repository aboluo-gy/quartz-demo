package com.persagy.quartz.joblistener;

import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.TriggerListener;
import org.quartz.Trigger.CompletedExecutionInstruction;

public class MyTriggerListener implements TriggerListener {

    private String name;

    public MyTriggerListener(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void triggerComplete(Trigger trigger, JobExecutionContext context,
                                CompletedExecutionInstruction triggerInstructionCode) {
        // do something with the event

    }

    public void triggerFired(Trigger trigger, JobExecutionContext context) {
        // do something with the event
    }

    public void triggerMisfired(Trigger trigger) {
        // do something with the event
    }

    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {
        // do something with the event
        return false;
    }

}
