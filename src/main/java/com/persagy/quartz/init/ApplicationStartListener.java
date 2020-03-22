package com.persagy.quartz.init;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

/**
 * 
 * Description:   服务初始化类，初始化数据库DBAgent，添加elastic-job任务
 * Company: Persagy 
 * @author luoguangyi 
 * @version 1.0
 * @since: 2019年8月2日: 下午4:53:01
 * Update By luoguangyi 2019年8月2日: 下午4:53:01
 */
@Service
public class ApplicationStartListener implements CommandLineRunner {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	@Qualifier("quartzScheduler")
	Scheduler quartzScheduler;
	@Override
	public void run(String... args) throws Exception {
		try {
			if (!quartzScheduler.isStarted()) {
				log.info("quartz定时任务将在延迟5秒后启动...");
				quartzScheduler.startDelayed(2);
			} else {
				log.info("quartz定时任务已经启动...");
			}
		} catch (SchedulerException e) {
			log.error("quartz定时任务启动失败", e);
		}
	}

}
