package com.persagy.quartz.demo;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author LuoGuangyi
 * @title: SpringSchedule
 * @projectName spring-boot-examples
 * @description: TODO
 * @date 2019/09/17 16:54
 */
@EnableScheduling
//@Configuration
public class SpringSchedule {

    @Scheduled(fixedDelay = 10000)
	public void fixedDelay() {
        ////上一次执行完毕时间点之后5秒再执行
		System.out.println("我帥故我在！---fixedDelay");
	}

    @Scheduled(cron="*/5 * * * * *")
    public void cron() {
        System.out.println("我帥故我在！---cron");
    }

    @Scheduled(fixedDelayString = "5000")
    public void fixedDelayString() {
        //上一次执行完毕时间点之后5秒再执行
        System.out.println("我帥故我在！---fixedDelayString");
    }

    //fixedRateString
    @Scheduled(fixedRate = 5000)
    public void fixedRate() {
        //上一次开始执行时间点之后5秒再执行
        System.out.println("我帥故我在！---fixedRate");
    }

    //initialDelayString
    @Scheduled(initialDelay=1000, fixedRate=5000)
    public void initialDelay() {
        //第一次延迟1秒后执行，之后按fixedRate的规则每5秒执行一次
        System.out.println("我帥故我在！---initialDelay");
    }
}
