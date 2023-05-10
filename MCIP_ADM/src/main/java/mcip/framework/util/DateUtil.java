package mcip.framework.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.icu.util.Calendar;

public class DateUtil {
	private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

	/**
	 * format에 대항하는 String 으로 변환
	 * @param date
	 * @param format
	 * @return
	 */
	public String dateToString(Date date, String format){
		return new SimpleDateFormat(format).format(date);
	}
	/**
	 * yyyy-MM-dd HH:mm
	 * 해당 포맷의 String Date 변환
	 * @param dateString
	 * @return
	 */
	public Date StringToDate(String dateString) {
		Date date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.KOREA);
			date = sdf.parse(dateString);
		} catch (ParseException e) {
			logger.debug(e.toString());
		}
		return date;
	}
	/**
	 * yyyyMMdd 
	 * 오늘날짜 String Date 변환
	 * @param dateString
	 * @return
	 */
	public String todayDateFormat(String dateString) {
		Date date = new Date();
		String today = null ;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
			today = sdf.format(date);
		} catch (Exception e) {
			logger.debug(e.toString());
		}		
		return today;
	}
	/**
	 * 포매팅에 맞춘
	 * 해당 포맷의 String Date 변환
	 * @param dateString
	 * @return
	 * @param dateString
	 * @param format
	 * @return
	 */
	public Date StringToDate(String dateString, String format) {
		Date date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.KOREA);
			date = sdf.parse(dateString);
		} catch (ParseException e) {
			logger.debug(e.toString());
		}
		return date;
	}
	/**
	 * 년월일시분초의 Int 값을 받아 Date 만드는 함수
	 * @param year
	 * @param month
	 * @param day
	 * @param hour
	 * @param min
	 * @param sec
	 * @return
	 */
	public Date setDateTime(int year, int month, int day, int hour, int min, int sec) {
		Calendar cal = Calendar.getInstance();
		cal.set((2000 + year), month, day, hour, min, sec);
		return cal.getTime();
	}
}
