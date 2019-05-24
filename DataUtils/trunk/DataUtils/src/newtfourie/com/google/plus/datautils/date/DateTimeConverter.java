package newtfourie.com.google.plus.datautils.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * This class can be used to convert gregorian calendar dates into string and vice versa
 * 
 * This class can also be used to get time as a string
 * @author Newt
 *
 */
public class DateTimeConverter {

	/**********************************************************/
	/*
	 * Singleton
	 */
	/*********************************************************/
	
	private DateTimeConverter()
	{
		
	}
	
	private static DateTimeConverter _thisObj = null;
	
	/**
	 * This is a singleton class and you must use this instance to make use of the object methods
	 * @return this instance of the DateTimeConverter
	 */
	public static DateTimeConverter getInstance()
	{
		if (_thisObj == null)
		{
			_thisObj = new DateTimeConverter();
		}
		
		return _thisObj;
	}
	
	/**********************************************************/
	/*********************************************************/

	/**
	 * Returns today's date as a gregorian calendar object
	 * @return today's date as a gregorian calendar object
	 */
	public GregorianCalendar getToday()
	{
		return new GregorianCalendar();
	}
	
	/**
	 * Returns today's date as a string
	 * @return today's date as a string
	 */
	public String getTodayString()
	{
		GregorianCalendar todayDate = new GregorianCalendar();
		String currentDate = "";
		String currentYear = String.valueOf(todayDate.get(GregorianCalendar.YEAR));
		String currentMonth = "", currentDay = "";
		int intCurrentMonth = Integer.valueOf(todayDate.get(GregorianCalendar.MONTH));
		int intCurrentDay = Integer.valueOf(todayDate.get(GregorianCalendar.DAY_OF_MONTH));
		
		//Month from the gregorian calendar and calendar objects in java are one less than they should be, so increase the number first
		intCurrentMonth++;
		//Now add leading zeros to day and month if they are less than 10
		if (intCurrentMonth < 10)
		{
			currentMonth = "0" + String.valueOf(intCurrentMonth);
		}
		else
		{
			currentMonth = String.valueOf(intCurrentMonth);
		}
		
		if (intCurrentDay < 10)
		{
			currentDay = "0" + String.valueOf(intCurrentDay);
		}
		else
		{
			currentDay = String.valueOf(intCurrentDay);
		}
		
		currentDate = currentYear + "-" + currentMonth + "-" + currentDay;
		//Log.v("Today's date", currentDate);
		
		return currentDate;
	}
	
	/**
	 * Converts the provided calendar into a string formatted date
	 * This method excludes time and only returns a date formatted as a string
	 * @param calendar - the calendar to use
	 * @return - a date formatted as a string
	 */
	public String convertDateToString(GregorianCalendar calendar)
	{
		String toReturn;
		int currentValue;
		
		currentValue = calendar.get(Calendar.YEAR);
		toReturn = String.valueOf(currentValue);
		
		toReturn += "-";
		
		currentValue = calendar.get(Calendar.MONTH) + 1;
		if (currentValue < 10)
		{
			toReturn += "0" + currentValue;
		}
		else
		{
			toReturn += "" + currentValue;
		}
		
		toReturn += "-";
		
		currentValue = calendar.get(Calendar.DAY_OF_MONTH);
		if (currentValue < 10)
		{
			toReturn += "0" + currentValue;
		}
		else
		{
			toReturn += "" + currentValue;
		}
		
		return toReturn;
	}
	
	/**
	 * Tokenization of the date string needs to be done outside of this method. This method only accepts a date string
	 * in the format yyyy-MM-dd
	 * @param dateString - the string to convert to gregorian calendar
	 * @return - a gregorian calendar result from the string provided
	 * @throws ParseException - if the provided string is of the incorrect format
	 */
	public GregorianCalendar convertDateToCalendar(String dateString) throws ParseException
	{
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.JAPAN);
		Date date = dateFormatter.parse(dateString);
		GregorianCalendar dateToReturn = new GregorianCalendar();
		dateToReturn.setTime(date);
		return dateToReturn;
	}
	
	/**
	 * Tokenization of the time string needs to be done outside of this method. This method only accepts a time string
	 * in the format hh:mm:ss
	 * @param timeString - the string to convert to gregorian calendar
	 * @return - a gregorian calendar result from the string provided
	 * @throws ParseException - if the provided string is of the incorrect format
	 */
	public GregorianCalendar convertTimeToCalendar(String timeString) throws ParseException
	{
		SimpleDateFormat dateFormatter = new SimpleDateFormat("HH:mm:ss", Locale.JAPAN);
		Date time = dateFormatter.parse(timeString);
		GregorianCalendar timeToReturn = new GregorianCalendar();
		timeToReturn.setTime(time);
		return timeToReturn;
	}
	
	/**
	 * Gets a string formatted time from a greogrian calendar
	 * @param calendar - the calendar to get the time from
	 * @return - a string formatted result of the time from the calendar
	 */
	public String getTimeFromCalendar(GregorianCalendar calendar)
	{
		String strHour;
		String strMin;
		String strSec;
		
		int hour = getHourFromCalendar(calendar);
		int min = getMinFromCalendar(calendar);
		int sec = getSecFromCalendar(calendar);
		
		String toReturn;
		
		if (hour < 10)
		{
			strHour = "0" + hour;
		}
		else
		{
			strHour = hour + "";
		}
		if (min < 10)
		{
			strMin = "0" + min;
		}
		else
		{
			strMin = min + "";
		}
		if (sec < 10)
		{
			strSec = "0" + sec;
		}
		else
		{
			strSec = sec + "";
		}
		toReturn = strHour + ":" + strMin + ":" + strSec;
		
		return toReturn;
	}
	
	/**
	 * Returns the hour from the calendar provided
	 * @param calendar - the calendar to use
	 * @return - the hour from the calendar provided
	 */
	public int getHourFromCalendar(GregorianCalendar calendar)
	{
		return calendar.get(Calendar.HOUR_OF_DAY);
	}
	
	/**
	 * Returns the min from the calendar provided
	 * @param calendar - the calendar to use
	 * @return - the min from the calendar provided
	 */
	public int getMinFromCalendar(GregorianCalendar calendar)
	{
		return calendar.get(Calendar.MINUTE);
	}
	
	/**
	 * Returns the sec from the calendar provided
	 * @param calendar - the calendar to use
	 * @return - the sec from the calendar provided
	 */
	public int getSecFromCalendar(GregorianCalendar calendar)
	{
		return calendar.get(Calendar.SECOND);
	}
	
	/**
	 * Gets a string formatted time from a new greogrian calendar
	 * @return - a string formatted result of the time from the calendar
	 */
	public String getCurrentTime()
	{
		GregorianCalendar calendar = new GregorianCalendar();
		
		String strHour;
		String strMin;
		String strSec;
		
		int hour = getHourFromCalendar(calendar);
		int min = getMinFromCalendar(calendar);
		int sec = getSecFromCalendar(calendar);
		
		String toReturn;
		
		if (hour < 10)
		{
			strHour = "0" + hour;
		}
		else
		{
			strHour = hour + "";
		}
		if (min < 10)
		{
			strMin = "0" + min;
		}
		else
		{
			strMin = min + "";
		}
		if (sec < 10)
		{
			strSec = "0" + sec;
		}
		else
		{
			strSec = sec + "";
		}
		toReturn = strHour + ":" + strMin + ":" + strSec;
		
		return toReturn;
	}
	
	/**
	 * Returns the hour from the current time (using new gregorian calendar)
	 * @return - the hour from the calendar
	 */
	public int getCurrentHour()
	{
		GregorianCalendar calendar = new GregorianCalendar();
		
		return calendar.get(Calendar.HOUR_OF_DAY);
	}
	
	/**
	 * Returns the min from the current time (using new gregorian calendar)
	 * @return - the min from the calendar 
	 */
	public int getCurrentMin()
	{
		GregorianCalendar calendar = new GregorianCalendar();
		
		return calendar.get(Calendar.MINUTE);
	}
	
	/**
	 * Returns the sec from the current time (using new gregorian calendar)
	 * @return - the sec from the calendar
	 */
	public int getCurrentSec()
	{
		GregorianCalendar calendar = new GregorianCalendar();
		
		return calendar.get(Calendar.SECOND);
	}
	
	/**
	 * This method will calculate the number of days difference between the two calendars
	 * provided. It will take calendar - oldCalendar and calculate how many days are between them
	 * 
	 * IMPORTANT: this method expects the date to be in the format yyyy-MM-dd
	 * this format is provided by the {@link #convertDateToString}
	 * 
	 * @param calendar - the first calendar as a string
	 * @param oldCalendar - the second calendar as a string
	 * @return number of days as a result from calendar - oldCalendar
	 */
	public long dayDifference(String calendar, String oldCalendar) throws ParseException
	{
		//check if years are different
		GregorianCalendar first = convertDateToCalendar(calendar);
		GregorianCalendar second = convertDateToCalendar(oldCalendar);
		
		Date endDate = first.getTime();
		Date startDate = second.getTime();
		
		long endTime = endDate.getTime();
		long startTime = startDate.getTime();
		long diffTime = endTime - startTime;
		
		long diffDays = diffTime / (1000 * 60 * 60 * 24);
		
		return diffDays;
	}
	
	/**
	 * This method will calculate the number of days difference between the two calendars
	 * provided. It will take calendar - oldCalendar and calculate how many days are between them
	 * 
	 * @param calendar - the first calendar as a string
	 * @param oldCalendar - the second calendar as a string
	 * @return number of days as a result from calendar - oldCalendar
	 */
	public long dayDifference(GregorianCalendar calendar, GregorianCalendar oldCalendar)
	{
		Date endDate = calendar.getTime();
		Date startDate = oldCalendar.getTime();
		
		long endTime = endDate.getTime();
		long startTime = startDate.getTime();
		long diffTime = endTime - startTime;
		
		long diffDays = diffTime / (1000 * 60 * 60 * 24);
		
		return diffDays;
	}
	
	/**
	 * This method will compare the two dates provided in a string format as (yyyy-MM-dd). see {@link #convertDateToString(GregorianCalendar)}
	 * It follows the normal compare standard whereby it will
	 * return val > 0 if dateOne > dateTwo
	 * return val = 0 if dateOne = dateTwo
	 * return val < 0 if dateOne < dateTwo
	 * @param dateOne - the first date to compare
	 * @param dateTwo - the second date to compare
	 * @return a comparison integer
	 * @throws ParseException - if the string date's provided are not in the correct format
	 */
	public int compareDateStrings(String dateOne, String dateTwo) throws ParseException
	{
		GregorianCalendar date = convertDateToCalendar(dateOne);
		GregorianCalendar anotherDate = convertDateToCalendar(dateTwo);
		
		return date.compareTo(anotherDate);
	}
	
	/**
	 * This method will compare the two times provided in a string format as (hh:mm:ss). see {@link #getTimeFromCalendar(GregorianCalendar)}
	 * It follows the normal compare standard whereby it will
	 * return val > 0 if timeOne > timeTwo
	 * return val = 0 if timeOne = timeTwo
	 * return val < 0 if timeOne < timeTwo
	 * @param dateOne - the first time to compare
	 * @param dateTwo - the second time to compare
	 * @return a comparison integer
	 * @throws ParseException - if the string time's provided are not in the correct format
	 */
	public int compareTimeStrings(String timeOne, String timeTwo) throws ParseException
	{
		GregorianCalendar date = convertTimeToCalendar(timeOne);
		GregorianCalendar anotherDate = convertTimeToCalendar(timeTwo);
		
		return date.compareTo(anotherDate);
	}
	
}
