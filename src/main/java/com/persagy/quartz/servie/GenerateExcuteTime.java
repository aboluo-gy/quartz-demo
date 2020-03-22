package com.persagy.quartz.servie;

import com.persagy.quartz.util.DateUtils;
import org.quartz.DateBuilder;
import org.quartz.impl.triggers.CalendarIntervalTriggerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.expression.ParseException;
import org.springframework.scheduling.support.CronSequenceGenerator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author LuoGuangyi
 * @title: Test
 * @projectName spring-boot-helloworld
 * @description: TODO
 * @date 2019/08/15 11:31
 */
public class GenerateExcuteTime {
    private static final Logger LOGGER = LoggerFactory.getLogger(GenerateExcuteTime.class);
    public static void main(String[] args) throws Exception {
        Long startmill = System.currentTimeMillis();
        List<String> list = getCronExcuteTime("0 0 * * * ?");
        Long endtmill = System.currentTimeMillis();
        System.out.println(endtmill - startmill);
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i));
//        }

//        getCalendarExcuteTime();
    }




    private static void getCalendarExcuteTime() throws java.text.ParseException {
        Date startTime = DateUtils.localDateTime2Date( DateUtils.parse("20190131101010"));
        Date endTime = DateUtils.localDateTime2Date( DateUtils.parse("20200131101010"));
        CalendarIntervalTriggerImpl aa = new CalendarIntervalTriggerImpl("name","group",startTime,endTime, DateBuilder.IntervalUnit.MINUTE,1);
//        Calendar a = new BaseCalendar();
////        aa.triggered(a);
//        System.out.println(aa.getNextFireTime());
        Date first = aa.computeFirstFireTime(null);
        System.out.println(aa.getNextFireTime());
//        aa.triggered(a);
        Date time = new Date();
        int i = 0;
        startTime = DateUtils.parseDate("18290131101010");
        Long startmill = System.currentTimeMillis();
        while (i++<120000){
            startTime = aa.getFireTimeAfter(startTime);
            if(startTime == null){
                return;
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            LOGGER.info(sdf.format(startTime));
            System.out.println(sdf.format(startTime));
        }
        //1秒钟就可以计算14万次分钟间隔的
        Long endtmill = System.currentTimeMillis();
        System.out.println(endtmill - startmill);
    }


    /**
     *
     * @desc 计算表达式近20次时间
     * @auth josnow
     * @date 2017年5月31日 下午12:16:25
     * @param cron
     * @return
     */
    public static List<String> getCronExcuteTime(String cron) throws ParseException, IllegalArgumentException {
        if (CronSequenceGenerator.isValidExpression(cron)) {
            throw new IllegalArgumentException("cron表達式錯誤");
        }
        CronSequenceGenerator cronSequenceGenerator = new CronSequenceGenerator(cron);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<String> list = new ArrayList<>(20);
        Date nextTimePoint = new Date();
        for (int i = 0; i < 200000; i++) {
            // 计算下次时间点的开始时间
            nextTimePoint = cronSequenceGenerator.next(nextTimePoint);
            list.add(sdf.format(nextTimePoint));
        }
        return list;
    }


}