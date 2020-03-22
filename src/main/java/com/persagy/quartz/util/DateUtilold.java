/**
 * @包名称 com.sagacloud.common
 * @文件名 DateUtil.java
 * @创建者 wanghailong
 * @邮箱 wanghailong@persagy.com  
 * @修改描述 
 */

package com.persagy.quartz.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 功能描述： 日期工具类
 * 
 * @类型名称 DateUtil
 * @创建者 wanghailong
 * @邮箱 wanghailong@persagy.com
 * @修改描述
 */
public class DateUtilold {
	public static final String sdftime = "yyyyMMddHHmmss";
	public static final String sdftimemilli = "yyyyMMddHHmmssSSS";
	public static final String sdfDay = "yyyyMMdd";
	public static final String sdf_time = "yyyy-MM-dd HH:mm:ss";
	public static final String sdf_time_mon = "yyyy-MM-dd HH:mm";
	public static final String customStateTimeFormat = "yyyyMMddHHmm";
	public static final String sdfHour = "yyyyMMddHH";
	public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	public static final SimpleDateFormat sdf_custom = new SimpleDateFormat("yyyyMMddHHmm");
	public static String getTimeStr(Calendar calendar,SimpleDateFormat sdf) {
		return sdf.format(calendar.getTime());
	}
	public static int getNowMonth() {
		return Calendar.getInstance().get(Calendar.MONTH);
	}
	public static int getNowYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}
	
	public static Date getDate(String dateStr,SimpleDateFormat sdfminute) throws ParseException {
		return sdfminute.parse(dateStr);
	}
	public static Date getDate(String dateStr) throws ParseException {
		return getDate(dateStr,sdf);
	}
	
	
	public static String getNowTimeStrIgnoreSecond() {
		Calendar calendar = Calendar.getInstance();	
		return sdf_custom.format(calendar.getTime());
	}
	
	public static String getNowTimeStr() {
		return sdf.format(Calendar.getInstance().getTime());
	}

	

	public static String getNowTimeStrSSS() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss.SSS");
		return sdf.format(new Date());
	}

	public static String getNowDate() {
		SimpleDateFormat sdf = new SimpleDateFormat(sdfDay);
		return sdf.format(new Date());
	}

	public static String getNowDateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat(sdf_time);
		return sdf.format(new Date());
	}
	
	/**
	 * 获取当前的UTC时间,精确到毫秒
	 */
	public static Long getUtcTimeNow() {
		Date dateNow = new Date();
		Long lRes = dateNow.getTime();
		return lRes;
	}

	/**
	 * 转换时间格式
	 * 
	 * @param dateStr
	 * @param fromDateFormat
	 * @param toDateFormat
	 * @return
	 * @throws ParseException
	 */
	public static String transferDateFormat(String dateStr, String fromDateFormat, String toDateFormat)
			throws ParseException {
		SimpleDateFormat fromSdf = new SimpleDateFormat(fromDateFormat);
		SimpleDateFormat toSdf = new SimpleDateFormat(toDateFormat);
		return toSdf.format(fromSdf.parse(dateStr));
	}

	/**
	 * 获取时间小时数
	 *
	 * @return
	 */
	public static String getNowTimeMinutes() {
		Date date = new Date();
		long time = date.getTime();
		return (time / 100) + "";
	}

	public static String getTimeMinutes(String timeStr, String timeFormat) {
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(timeFormat);
			Date date;
			date = simpleDateFormat.parse(timeStr);
			long time = date.getTime();
			return (time / 100) + "";
		} catch (ParseException e) {
		}
		return null;
	}

	/**
	 * 获取时间 yyyyMMdd 转换成int
	 * 
	 * @return
	 */
	public static String getDayFromDateString(String dateStr, String timeFormat) throws ParseException {
		Date date = parseDate(timeFormat, dateStr);
		return formatStr(sdfDay, date);
	}

	/**
	 * 
	 * @param timeStr
	 * @param timeFormat
	 * @param aheadHour
	 * @return
	 * @throws Exception
	 */
	public static Calendar getBeforeHour(String timeStr, String timeFormat, int aheadHour) throws Exception {
		Calendar calendar = Calendar.getInstance();
		Date date = parseDate(timeFormat, timeStr);
		calendar.setTime(date);
		if (aheadHour != 0) {
			calendar.add(Calendar.HOUR, aheadHour * -1);
		}
		return calendar;
	}

	public static int getTimeMinute() throws Exception {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.MINUTE);
	}

	public static int getTimeDayOfMonth(Date date) throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	public static void main(String[] args) {
		try {
//			Calendar effective_time_calendar = Calendar.getInstance();
//			System.out.println("effective_time_calendar: " + DateUtil.getTimeStr(effective_time_calendar, DateUtil.sdf));
//			Thread.sleep(8031L);
//			Calendar nexTcalendar = effective_time_calendar.getInstance();
//			System.out.println("nexTcalendar: " + DateUtil.getTimeStr(nexTcalendar, DateUtil.sdf));
			
/*			for (int i = 0; i <100; i++) {
				array.add(i);
				Random random1 = new Random();
				array.add(i+random1.nextInt(10));
			}*/
//			array = new JSONArray(array.stream().distinct().sorted().collect(Collectors.toList()));
			Calendar cale = Calendar.getInstance();
			cale.set(Calendar.DATE,cale.getActualMaximum(Calendar.DATE));
			cale.set(Calendar.HOUR_OF_DAY, 20);
			cale.set(Calendar.SECOND, 0);
			cale.set(Calendar.MINUTE, 0);
			cale.set(Calendar.MILLISECOND, 0);
			System.out.println("cale: " + getTimeStr(cale, DateUtilold.sdf));
			cale.add(Calendar.HOUR_OF_DAY, 120);
			System.out.println("cale: " + getTimeStr(cale, DateUtilold.sdf));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param timeStr
	 * @param timeFormat
	 * @return
	 * @throws Exception
	 */
	public static Calendar getBeforeOrAfterTimeMinutes(String timeStr, String timeFormat, int aheadMinutes)
			throws Exception {
		Calendar calendar = Calendar.getInstance();
		Date date = parseDate(timeFormat, timeStr);
		calendar.setTime(date);
		if (aheadMinutes != 0) {
			calendar.add(Calendar.MINUTE, aheadMinutes);
		}
		return calendar;
	}

	public static String getTimeByMinutes(String timeStr, int aheadMinutes, int cycle) throws Exception {
		Calendar calendar = getBeforeOrAfterTimeMinutes(timeStr, sdftime, aheadMinutes);
		return testGetCron(calendar.getTime(), cycle);
	}

	public static String testGetCron(Date date, int cycle) {
		String dateFormat = "ss mm HH dd MM ? yyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		String formatTimeStr = "";
		String cron = "";
		String cronRelax = "";
		if (date != null) {
			formatTimeStr = sdf.format(date);
			cronRelax = formatTimeStr.substring(0, 5);
			String time = formatTimeStr.substring(6, 8);
			if (cycle > 0) {
				// // cron = cronRelax ;
				// // } else {
				//// 5/15 * * 24 3 ？ 2018
				//
				cron = cronRelax + "/" + cycle;

				formatTimeStr = formatTimeStr.replace(cronRelax, cron);

				return formatTimeStr;
				// return cron + " * * * ? *";
				// return cron + " " + time + " * * ? *";
			}
		}
		return formatTimeStr;
	}

	public static String getTimeByMinutesToday(String timeStr, int aheadMinutes, int cycle) throws Exception {
		Calendar calendar = getBeforeOrAfterTimeMinutes(timeStr, sdftime, aheadMinutes);
		return testGetCronToday(calendar.getTime(), cycle);
	}

	public static String testGetCronToday(Date date, int cycle) {
		String dateFormat = "ss mm HH dd MM ? yyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		String formatTimeStr = "";
		String cron = "";
		String cronRelax = "";
		if (date != null) {
			formatTimeStr = sdf.format(date);
			cronRelax = formatTimeStr.substring(0, 5);
			// String time = formatTimeStr.substring(6, 8);
			if (cycle > 0) {
				// // cron = cronRelax ;
				// // } else {
				//// 5/15 * * 24 3 ？ 2018
				//
				cron = cronRelax + "/" + cycle;

				// formatTimeStr = formatTimeStr.replace(cronRelax, cron);

				// return formatTimeStr;
				return cron + " * * * ? *";
				// return cron + " " + time + " * * ? *";
			}
		}
		return formatTimeStr;
	}

	/**
	 * 根据传入的时间，获取与当前的毫秒值
	 * 
	 * @param timeStr
	 * @param hour
	 * @return
	 * @throws ParseException
	 *             Long
	 * @date 2019年5月9日: 下午8:20:18
	 * @author laixingqi
	 * @version 1.0
	 */
	public static Long getTimeByParamTime(String timeStr, int hour) throws ParseException {
		Long calendar = getAfterDateOfHour(timeStr, hour);
		Long nowCalendar = getUtcTimeNow();
		Long time = 0L;
		if (calendar > nowCalendar) {
			time = calendar - nowCalendar;
			return time;
		}
		// else {
		// time = nowCalendar - calendar;
		// }
		return time;
	}

	public static Long getTimeByParamTime(Long startTtime) throws ParseException {
		Long nowCalendar = getUtcTimeNow();
		Long time = 0L;
		if (startTtime > nowCalendar) {
			time = startTtime - nowCalendar;
			return time;
		}
		// else {
		// time = nowCalendar - calendar;
		// }
		return time;
	}

	public static Long getTimeByParamStartTime(Long referTime) throws ParseException {
		Long nowCalendar = getUtcTimeNow();
		Long time = 0L;
		if (referTime > nowCalendar) {
			time = (referTime - nowCalendar);
			return time;
		}
		// else {
		// time = nowCalendar - calendar;
		// }
		return time;
	}

	public static Long getTimeByDay(Integer hour, Integer min, Integer sec) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, hour);
		c.set(Calendar.MINUTE, min);
		c.set(Calendar.SECOND, sec);
		Date date = c.getTime();

		return date.getTime();
	}

	/**
	 * 根据日历获取时间yyyyMMdd
	 * 
	 * @param calendar
	 * @return
	 */
	public static String getDayFromCalendar(Calendar calendar) {
		Date date = calendar.getTime();
		return formatStr(sdftime, date);
	}

	/**
	 * 
	 * @param timeStr
	 * @param timeFormat
	 * @param aheadHour
	 * @return
	 * @throws Exception
	 */
	public static String getAfterDateOfDay(String timeStr, String timeFormat, int aheadHour) {
		Calendar calendar = Calendar.getInstance();
		Date date = new Date();
		try {
			date = parseDate(timeFormat, timeStr);
		} catch (ParseException e) {
		}
		calendar.setTime(date);
		if (aheadHour != 0) {
			calendar.add(Calendar.DATE, aheadHour);
		}
		return new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());
	}

	/**
	 * 获取m个天后的日期
	 * 
	 * @param afterDay
	 * @return yyyyMMdd
	 */
	public static String getAfterDateOfDay(int afterDay) {
		Calendar calendar = Calendar.getInstance();
		if (afterDay != 0) {
			calendar.add(Calendar.DATE, afterDay);
		}
		return new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());
	}

	/**
	 * 获取m个小时后的日期
	 * 
	 * @param afterHour
	 * @return
	 */
	public static String getAfterDateOfHour(int afterHour) {
		Calendar calendar = Calendar.getInstance();
		if (afterHour != 0) {
			calendar.add(Calendar.HOUR_OF_DAY, afterHour);
		}
		return new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());
	}

	/**
	 * 获取指定时间，m个小时后的日期
	 * 
	 * @param afterHour
	 * @return
	 * @throws ParseException
	 */
	public static Long getAfterDateOfHour(String str, int afterHour) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		Date date = parseDate(sdftime, str);
		calendar.setTime(date);
		if (afterHour != 0) {
			calendar.add(Calendar.HOUR_OF_DAY, afterHour);
		}
		return calendar.getTime().getTime();
	}

	/**
	 * 获取指定时间，m分钟后的日期
	 *
	 * @return
	 * @throws ParseException
	 */
	public static Long getBeforeOrAfterTimeMinutes(String timeStr, int aheadMinutes) throws Exception {
		Calendar calendar = Calendar.getInstance();
		Date date = parseDate(sdftime, timeStr);
		calendar.setTime(date);
		if (aheadMinutes != 0) {
			calendar.add(Calendar.MINUTE, aheadMinutes);
		}
		return calendar.getTime().getTime();
	}

	public static Long getBeforeTimeMinutes(String timeStr) throws Exception {
		Calendar calendar = Calendar.getInstance();
		Date date = parseDate(sdftime, timeStr);
		calendar.setTime(date);

		return calendar.getTime().getTime();
	}

	/**
	 * 获取n天、m个小时后的日期
	 * 
	 * @param afterHour
	 * @return
	 */
	public static String getAfterDate(int afterYear, int afterMonth, int afterDay, int afterHour) {
		Calendar calendar = Calendar.getInstance();
		if (afterYear != 0) {
			calendar.add(Calendar.YEAR, afterYear);
		}
		if (afterMonth != 0) {
			calendar.add(Calendar.MONTH, afterMonth);
		}
		if (afterDay != 0) {
			calendar.add(Calendar.DATE, afterDay);
		}
		if (afterHour != 0) {
			calendar.add(Calendar.HOUR_OF_DAY, afterHour);
		}
		return new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());
	}

	/**
	 * 获取n天、m个小时后的日期
	 * 
	 * @param afterHour
	 * @return
	 */
	public static String getAfterDate(Date date, String timeFormat, int afterYear, int afterMonth, int afterDay,
			int afterHour) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		if (afterYear != 0) {
			calendar.add(Calendar.YEAR, afterYear);
		}
		if (afterMonth != 0) {
			calendar.add(Calendar.MONTH, afterMonth);
		}
		if (afterDay != 0) {
			calendar.add(Calendar.DATE, afterDay);
		}
		if (afterHour != 0) {
			calendar.add(Calendar.HOUR_OF_DAY, afterHour);
		}
		return new SimpleDateFormat(timeFormat).format(calendar.getTime());
	}

	/**
	 * 获取时间差值-格式**天**小时**分钟
	 * 
	 * @param fromTime
	 * @param toTime
	 * @param timeFormat
	 * @return
	 * @throws ParseException
	 */
	public static String getTimeDiffence(String fromTime, String toTime, String timeFormat) throws ParseException {
		if (fromTime == null || toTime == null){
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(timeFormat);
		Date startTime = sdf.parse(fromTime);
		Date endTime = sdf.parse(toTime);
		long diffenceValue = endTime.getTime() - startTime.getTime();
		long days = diffenceValue / (24 * 60 * 60 * 1000);
		long hours = (diffenceValue / (60 * 60 * 1000) - days * 24);
		long min = ((diffenceValue / (60 * 1000)) - days * 24 * 60 - hours * 60);
		String str = "";
		if (days != 0) {
			str += days + "天";
		}
		if (hours != 0 || days != 0) {
			str += hours + "小时";
		}
		if (min != 0 || days != 0 || hours != 0 || (days == 0 && hours == 0)) {
			str += min + "分钟";
		}
		return str;
	}

	public static String formatStr(String pattern, Date date) {
		String str = new SimpleDateFormat(pattern).format(date);
		return str;
	}

	public static Date parseDate(String pattern, String str) throws ParseException {
		SimpleDateFormat sim = new SimpleDateFormat(pattern);
		Date date = sim.parse(str);
		return date;
	}

	public static String formatStrToMon(String date) {

		try {
			Date dateMon = parseDate(DateUtilold.sdf_time, date);
			String str = new SimpleDateFormat(DateUtilold.sdf_time).format(dateMon);
			return str;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将毫秒转成时间格式的字符串
	 * 
	 * @param pattern
	 * @param milis
	 * @return
	 */
	public static String parseDate(String pattern, long milis) {
		String date = new SimpleDateFormat(pattern).format(milis);
		return date;
	}

	/**
	 * 当前时间到第二日凌晨的秒数
	 */
	public static Long getSecondsNextEarlyMorning() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return (cal.getTimeInMillis() - System.currentTimeMillis()) / 1000;
	}
}
