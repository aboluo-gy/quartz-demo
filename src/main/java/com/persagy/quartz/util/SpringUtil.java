package com.persagy.quartz.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringUtil implements ApplicationContextAware {
	/** 持有的spring上下文 */
	private static ApplicationContext applicationContext = null;

	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		if (SpringUtil.applicationContext == null) {
			SpringUtil.applicationContext = arg0;
		}
	}

	// 获取applicationContext
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}


	/**
	 *
	 * 根据Bean名称得到Bean
	 *
	 * @param clazz
	 *            bean的类型
	 * @param beanName
	 *            bean名称
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String beanName,Class<T> clazz) {
//		return (T) applicationContext.getBean(beanName);
		return applicationContext.getBean(beanName, clazz);
	}

	//通过name获取 Bean.
	public static Object getBean(String name){
		return getApplicationContext().getBean(name);
	}
}
