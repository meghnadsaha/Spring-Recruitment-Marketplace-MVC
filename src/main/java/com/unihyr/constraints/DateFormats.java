package com.unihyr.constraints;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateFormats 
{
	public static SimpleDateFormat ddMMMMyyyy = new SimpleDateFormat("dd/MM/yyyy");

	public static SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat ddMMMMyyyyathhmm = new SimpleDateFormat("dd/MM/yyyy hh:mma");
	
	public static String getTimeValue(Date date)
	{
		String st  = null;
		Date ct = new Date();
		if((ct.getTime() - date.getTime() ) < 60*1000)
		{
			st = ((ct.getTime()-date.getTime())/1000)+1 + " secs ago";
		}
		else if((ct.getTime() - date.getTime() ) < 60*60*1000)
		{
			st = ((ct.getTime()-date.getTime())/(60*1000))+1 + " mins ago";
		}
		else if((ct.getTime() - date.getTime() ) < 12*60*60*1000)
		{
			st = ((ct.getTime()-date.getTime())/(60*60*1000))+1 + " hours ago";
		}
		else
		{
			st = DateFormats.ddMMMMyyyy.format(date);
		}
		
		return st;
	}
	
	public static Date getLastMonday(Date date)
	{
		
		Calendar c = GregorianCalendar.getInstance();

        System.out.println("Current week = " + Calendar.DAY_OF_WEEK);

        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        System.out.println("Current week = " + Calendar.DAY_OF_WEEK);

        System.out.println("Start Date = " + c.getTime());
		
		return c.getTime();
		
	}
	
}
