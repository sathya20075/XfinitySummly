package com.apps.summly.utilities;

import java.util.Calendar;
import java.text.SimpleDateFormat;

public class Utilities
{

	public static void main(String args[])
	{
		Utilities util = new Utilities();
		System.out.println(util.getCurrentDateAndTime());
	}
	
	public static String getCurrentDateAndTime()
	{
		String currentDateAndTime = null;
		Calendar calc = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHH:mm:ss");
		currentDateAndTime = sdf.format(calc.getTime());
	    
		return currentDateAndTime;
	}
	
}
