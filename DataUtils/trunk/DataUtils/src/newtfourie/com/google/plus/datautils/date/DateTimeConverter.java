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
	
}
