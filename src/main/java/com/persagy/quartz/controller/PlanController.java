package com.persagy.quartz.controller;

import com.alibaba.fastjson.JSONObject;
import com.persagy.quartz.servie.CalendarInterval;
import com.persagy.quartz.servie.SimpleSchedule;
import com.persagy.quartz.servie.impl.QuartzService;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

/**
 * 
 * Description:  计划操作API 计划的增删改查
 * Company: Persagy 
 * @author luoguangyi 
 * @version 1.0
 * @since: 2019年8月2日: 下午4:44:04
 * Update By luoguangyi 2019年8月2日: 下午4:44:04
 */
@RestController
@RequestMapping("/plan")
public class PlanController {

    private static final Logger log = LoggerFactory.getLogger(PlanController.class);
	
    @Autowired
    QuartzService quartzService;
    @Autowired
    CalendarInterval calendarInterval;
    @Autowired
    SimpleSchedule simpleSchedule;

    @PostMapping("/calendarInterval")
    public void calendarInterval() throws SchedulerException, ParseException {
        calendarInterval.init();
    }

    @PostMapping("/simpleSchedule")
    public String simpleSchedule() throws SchedulerException, ParseException {
        return simpleSchedule.init();
    }

    @PostMapping("/cleanPlan")
    public void cleanPlan() {
        quartzService.cleanJob();
    }
    
    
    @PostMapping("/test")
    public void test(@RequestBody JSONObject jsonObject) {

    }

}
