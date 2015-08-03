package com.ttc.ch2.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.UnhandledException;
import org.apache.commons.lang.time.DurationFormatUtils;

import com.google.common.base.Preconditions;

public class DateHelper {

	public static final String DEFAULT_DATE_FORMAT_CR_AND_MSGS = "EEE, d MMM yyyy HH:mm:ss z";	
	public static final String DEFAULT_DATE_FORMAT="dd-MMM-yyyy";	
	public static final String DEFAULT_DATE_TIME_FORMAT=DEFAULT_DATE_FORMAT_CR_AND_MSGS;
//	public static final String DEFAULT_DATE_TIME_FORMAT="dd-MMM-yyyy HH:mm";
	
	public static final String XML_DATE_TIME_FORMAT="yyyy-MMM-dd'T'HH-mm-ss";
	
	
	
	public enum CalculateTimePattern{MILLISECOND,SECOND,MINUTE,HOUR,HMS};
	
		
	public static String getCurrentDateAsString()
	{
		return dateToString(new Date());
	}
	
	public static String getCurrentDateTimeAsString()
	{
		return dateTimeToString(new Date());
	}
	
	
	public static String dateTimeToString(Date date)
	{
		return dateToString(date,DEFAULT_DATE_TIME_FORMAT);
	}
	
	public static String dateToString(Date date)
	{
		return dateToString(date,DEFAULT_DATE_FORMAT);
	}
	
	public static String getCurrentDateTimeAsString(String format)
	{
		return dateToString(new Date(),format);
	}
	
	public static String dateToString(Date date,String format)
	{
		SimpleDateFormat dateFormater=new SimpleDateFormat(format);
		return dateFormater.format(date);
	}
	
	
	public static Date stringToDate(String date,String pattern)
	{
		Preconditions.checkArgument(StringUtils.isNotEmpty(date),"DateHelper.stringToDate -> Param date is empty");
		Preconditions.checkArgument(StringUtils.isNotEmpty(pattern),"DateHelper.stringToDate -> Param pattern is empty");

		SimpleDateFormat dateFormater=new SimpleDateFormat(pattern);
		try {
			return dateFormater.parse(date);
		} catch (ParseException e) {
			throw new UnhandledException(e) ;
		}	
	}
	
	public static XMLGregorianCalendar dateToXMLGregorianCalendar(Date date)
	{		
		try {
			GregorianCalendar c = new GregorianCalendar();
			c.setTime(date);
			XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
			return xmlDate;
		} catch (DatatypeConfigurationException e) {
			throw new UnhandledException(e);
		}
	}
	
	
	
	public static String calculateTime(Date start,Date end,CalculateTimePattern calculateTimePattern)
	{
		long time=end.getTime()-start.getTime();
		return calculateTime(time, calculateTimePattern);
	}
	
	public static String calculateTime(Long time,CalculateTimePattern calculateTimePattern)
	{
		//DurationFormatUtils to use in future
		if(time==null)
			time=0l;
		StringBuilder result=new StringBuilder();
		switch (calculateTimePattern) {
		case MILLISECOND:
			result=result.append(result);
			break;
		case SECOND:
			double r1=time/1000;			
			result=result.append(r1).append(" s");
			break;
		case MINUTE:
			throw new UnsupportedOperationException("to do in future");
		case HOUR:
			throw new UnsupportedOperationException("to do in future");
		case HMS:
			result.append(DurationFormatUtils.formatDurationHMS(time));
			break;
		default:
			break;
		}		
		return result.toString();
	}
	
}
