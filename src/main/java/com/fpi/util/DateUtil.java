package com.fpi.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil {

	static SimpleDateFormat dayOfTheWeekFormat = new SimpleDateFormat("EEEE");
	static SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	
	public static Date[] getDateLimits(Date date, int duration, List<Date> holidates) {
		Date[] dates = new Date[2];
		Date startDate = null;
		Date endDate = null;
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		
		// compute for the start date of the task
		getStartDate(cal, holidates);
		startDate = cal.getTime();
		
		// compute for the end date of the task
		getEndDate(cal, duration, holidates);
		endDate = cal.getTime();
		
		dates[0] = startDate;
		dates[1] = endDate;
		return dates;
	}
	
	private static void getStartDate(Calendar cal, List<Date> holidates) {
		String dayOfTheWeek = dayOfTheWeekFormat.format(cal.getTime()).toUpperCase();
		
		while ("SATURDAY".equals(dayOfTheWeek) || "SUNDAY".equals(dayOfTheWeek) || holidates.contains(
				sdf.format(cal.getTime()))) {
			cal.add(Calendar.DATE, 1);
			dayOfTheWeek = dayOfTheWeekFormat.format(cal.getTime()).toUpperCase();
		}
	}
	
	private static void getEndDate(Calendar cal, int duration, List<Date> holidates) {
		int ctr = 0;
		
		do {
			cal.add(Calendar.DATE, 1);
			
			String dayOfTheWeek = dayOfTheWeekFormat.format(cal.getTime()).toUpperCase();
			
			if (!"SATURDAY".equals(dayOfTheWeek) && !"SUNDAY".equals(dayOfTheWeek) && !holidates.contains(
					sdf.format(cal.getTime()))) {
				ctr++;
			}
			
		} while (ctr < duration - 1);
	}
}
