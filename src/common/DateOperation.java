package common;

import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.*;

public class DateOperation {

	// Takes an integer between 0 to 11 (0 = "January" & 11 = "December") and
	// returns the respective month.

	public static String getCurrentMonth(int monthInInt) {

		String month = null;

		switch (monthInInt) {
		case 0:
			month = "January";
			break;
		case 1:
			month = "February";
			break;
		case 2:
			month = "March";
			break;
		case 3:
			month = "April";
			break;
		case 4:
			month = "May";
			break;
		case 5:
			month = "June";
			break;
		case 6:
			month = "July";
			break;
		case 7:
			month = "August";
			break;
		case 8:
			month = "September";
			break;
		case 9:
			month = "October";
			break;
		case 10:
			month = "November";
			break;
		case 11:
			month = "December";
			break;
		}

		return month;

	}

	// Takes previous month in integer form and returns in string form

	public static String getPreviousMonth(int monthInInt) {

		String month = "";

		switch (monthInInt) {
		case 0:
			month = "January";
			break;
		case 1:
			month = "February";
			break;
		case 2:
			month = "March";
			break;
		case 3:
			month = "April";
			break;
		case 4:
			month = "May";
			break;
		case 5:
			month = "June";
			break;
		case 6:
			month = "July";
			break;
		case 7:
			month = "August";
			break;
		case 8:
			month = "September";
			break;
		case 9:
			month = "October";
			break;
		case 10:
			month = "November";
			break;
		case -1:
			month = "December";
			break;
		}

		return month;

	}

	// Takes a String type date in dd/mm/yyyy format and returns the Calendar
	// object

	public static Calendar getCalendarDate(String date) {

		Calendar calendar = null;
		try {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			calendar = Calendar.getInstance();
			calendar.setTime(format.parse(date));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return calendar;

	}

	// Takes a String type date in dd/mm/yyyy format and returns the
	// java.sql.Date object

	public static java.sql.Date getSqlDate(String date) {

		java.sql.Date sqlDate = null;
		try {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			java.util.Date javaDate = format.parse(date);
			sqlDate = new java.sql.Date(javaDate.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sqlDate;

	}

}