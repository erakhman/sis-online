package com.beesinergi.util;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.beesinergi.util.SystemConstant;

/**
 * Date/Time function utility
 * @author eRyan
 *
 */
public class DateTimeFunction {
    private static String timeDelimiter = ":";
    private static final int MILLIS_IN_DAY = 1000 * 60 * 60 * 24;
    private static DatatypeFactory df;
    
    static {
    	try {
			df = DatatypeFactory.newInstance();
		} catch (DatatypeConfigurationException e) {
			// assume always success, no fallback plan :)
			e.printStackTrace();
		}
    }
    
    /**
     * Convert java.util.Date to XMLGregorianCalendar without time portions
     * @param date
     * @return XMLGregorianCalendar
     */
    public static XMLGregorianCalendar convertDateToXmlGregorian(Date date) {
    	if (date == null) {
    		return null;
    	}
    	GregorianCalendar gregCal = new GregorianCalendar();
    	gregCal.setTimeInMillis(date.getTime());

    	XMLGregorianCalendar xgc = df.newXMLGregorianCalendar(gregCal);
    	xgc.setHour(DatatypeConstants.FIELD_UNDEFINED);
    	xgc.setMinute(DatatypeConstants.FIELD_UNDEFINED);
    	xgc.setSecond(DatatypeConstants.FIELD_UNDEFINED);
    	xgc.setMillisecond(DatatypeConstants.FIELD_UNDEFINED);
    	xgc.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
    	return xgc;
    }

    public static Date convertXmlGregorianToDate(XMLGregorianCalendar timestamp) {
    	if (timestamp == null) {
    		return null;
    	}
		return timestamp.toGregorianCalendar().getTime();
	}
    
    public static Date getCurrentDate(){
    	Calendar now = Calendar.getInstance();
    	return now.getTime();
    }
    
    /**
     * To get current date, without hour, minute and second
     * 
     * @return Date
     */
    public static Date getCurrentDateWithoutTime() {
    	Calendar cal = Calendar.getInstance();
    	cal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
    	cal.set(Calendar.MONTH, cal.get(Calendar.MONTH));
    	cal.set(Calendar.DATE, cal.get(Calendar.DATE));
    	cal.set(Calendar.HOUR_OF_DAY, 0);
    	cal.set(Calendar.MINUTE, 0);
    	cal.set(Calendar.SECOND, 0);
    	cal.set(Calendar.MILLISECOND, 0);

    	return cal.getTime();
    }
    
    public static XMLGregorianCalendar getCurrentXMLGregorianDateWithoutTime() throws DatatypeConfigurationException {
    	XMLGregorianCalendar xgc = DatatypeFactory.newInstance().newXMLGregorianCalendar();
    	GregorianCalendar now = new GregorianCalendar();
    	xgc.setYear(now.get(Calendar.YEAR));
    	xgc.setMonth(now.get(Calendar.MONTH)+1);
    	xgc.setDay(now.get(Calendar.DAY_OF_MONTH));
        return xgc;
    }

    /***
     * Return current month in numeric (one-based index)
     * @return current month number
     */
    public static int getCurrentMonth(){
    	Calendar now = Calendar.getInstance();
    	return now.get(Calendar.MONTH)+1;
    }
    
    public static void main(String[] a) {
    	System.out.println(getCurrentMonth());
    }

    public static Integer getCurrentYear(){
    	Calendar now = Calendar.getInstance();
    	return now.get(Calendar.YEAR);
    }
    
    public static List<Integer> getYears(int start, int end){
    	List<Integer> years = new LinkedList<Integer>();
    	do {
    		years.add(start); start++;
		} while (start!=end);
    	return years;
    }
    
    public static Date getNextDate(Date d){
    	d.setTime(d.getTime()+MILLIS_IN_DAY);
    	return d;
    }
    
    public static Date getBeforeDate(Date d){
    	d.setTime(d.getTime()-MILLIS_IN_DAY);
    	return d;
    }
    
    public static Date getDateFromMillis(long millis) {
    	Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
    }

/*    public static String getStringOfTimesNoDelimiter(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        Date toDate = convertToUtilDate(timestamp);
        return (new SimpleDateFormat(SystemConstant.HOUR_MINUTE_SECOND_MASK_NO_DELIMITER)).format(toDate);
    }*/

    public static int[] stringToTimeArray(String time) throws Exception {
        int[] t = new int[3];
        StringTokenizer st = new StringTokenizer(time, timeDelimiter);
        if (st.countTokens() == 2) {
            try {
                String hour = st.nextToken();
                t[0] = Integer.parseInt(hour);

                String minute = st.nextToken();
                t[1] = Integer.parseInt(minute);
            } catch (NumberFormatException nfe) {
                throw nfe;
            }
        } else if (st.countTokens() >= 3) {
            try {
                String hour = st.nextToken();
                t[0] = Integer.parseInt(hour);

                String minute = st.nextToken();
                t[1] = Integer.parseInt(minute);

                String second = st.nextToken();
                t[2] = Integer.parseInt(second);

            } catch (NumberFormatException nfe) {
                throw nfe;
            }
        } else {
            throw new Exception("Unknown time format");
        }
        return t;
    }
    
    /**
     * To extract Hour from given date time. 
     * Note that date and time is separated by space character.
     * 
     * @param dateTime String from which hour will be extracted. E.g "28-02-2011 22:33:11" 
     * @return Hour in int, from the example: 22
     * @throws Exception
     */
    public static int getHourFromDateTime(String dateTime) throws Exception {
    	String[] str = dateTime.split(" ");
    	if(str.length > 1) {
    		int[] time = stringToTimeArray(str[1]);
    		return time[0];
    	}
    	
    	return 0;
    }
    
    /**
     * To extract Minute from given date time. 
     * Note that date and time is separated by space character.
     * 
     * @param dateTime String from which minute will be extracted. E.g "28-02-2011 22:33:11" 
     * @return Minute in int, from the example: 33
     * @throws Exception
     */
    public static int getMinuteFromDateTime(String dateTime) throws Exception {
    	String[] str = dateTime.split(" ");
    	if(str.length > 1) {
    		int[] time = stringToTimeArray(str[1]);
    		return time[1];
    	}
    	
    	return 0;
    }
    
    /**
     * To extract Second from given date time. 
     * Note that date and time is separated by space character.
     * 
     * @param dateTime String from which second will be extracted. E.g "28-02-2011 22:33:11" 
     * @return Second in int, from the example: 11
     * @throws Exception
     */
    public static int getSecondFromDateTime(String dateTime) throws Exception {
    	String[] str = dateTime.split(" ");
    	if(str.length > 1) {
    		int[] time = stringToTimeArray(str[1]);
    		return time[2];
    	}
    	
    	return 0;
    }

    public static String date2String(Date date, String mask) {
        SimpleDateFormat sdf = new SimpleDateFormat(mask);
        return date == null ? "" : sdf.format(date);
    }

    public static String date2String(XMLGregorianCalendar date, String mask) {
        SimpleDateFormat sdf = new SimpleDateFormat(mask);
        return date == null ? "" : sdf.format(date.toGregorianCalendar().getTime());
    }

    /*public static String time2String(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(SystemConstant.HOUR_MINUTE_SECOND_MASK);
        return date == null ? "" : sdf.format(date);
    }*/

    /*public static String systemDate2String(Date date) {
        if (date == null) {
            return "";
        }
        return date2String(date, SystemConstant.SYSTEM_DATE_MASK);
    }*/
    
    public static Date string2Date(String dateString, String dateFormat) {
        Date d = null;
        if (StringFunction.checkString(dateString) != null) {
            ParsePosition pos = new ParsePosition(0);
            d = (new SimpleDateFormat(dateFormat)).parse(dateString, pos);
        }
        return d;
    }

    /**
     * convert string to date
     * @param dateString
     * @return
     */
    /*public static Date string2systemDate(String dateString) {
        return string2Date(dateString, SystemConstant.SYSTEM_DATE_MASK);
    }*/

    /**
     * convert from java.util.Date to java.sql.Date
     * @param dt the date
     * @return date in sql date format or null if dt is null.
     */
    public static java.sql.Date convertToSQLDate(java.util.Date dt) {
        if (dt == null)
            return null;
        return new java.sql.Date(dt.getTime());
    }

    public static Timestamp convertToTimestamp(java.util.Date dt) {
        if (dt == null)
            return null;
        return new Timestamp(dt.getTime());
    }

    /**
     * Convert sql date to util Date
     * @param dt
     * @return date in util date format or null if dt == null
     */
    public static java.util.Date convertToUtilDate(java.sql.Date dt) {
        if (dt == null)
            return null;
        return new java.util.Date(dt.getTime());
    }

    public static java.util.Date convertToUtilDate(Timestamp timestamp) {
        if (timestamp == null)
            return null;
        return new java.util.Date(timestamp.getTime());
    }

    /**
     * Convert a formated date of year date into java.util.Date
     * @param formatedDate
     * @return
     */
    public static java.util.Date convertDayOfYear(String formatedDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyDDD");
        try {
            return sdf.parse(formatedDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * format date that has pattern as system date mask.
     * @param str
     * @return
     * @throws ParseException
     * @see SystemConstant#SYSTEM_DATE_MASK
     */
    /*public static long getLongDate(String str) throws ParseException {
        if (str == null || str.equals("null"))
            return -1; // prevent uneccesary parse exception -- for Rule
        final SimpleDateFormat systemDateFormat = new SimpleDateFormat(SystemConstant.SYSTEM_DATE_MASK);
        return systemDateFormat.parse(str).getTime();
    }*/

    /**
     * Subtract between two date (date1 > date0), time (hour, minute, second are ignored)
     * @return number of days
     * */
    public static long calcDateDifference(int CAL_CONST, Date date0, Date date1) throws RuntimeException {
        long time = 1000;
        if (CAL_CONST == Calendar.DAY_OF_MONTH) {
            time = time * 60 * 60 * 24;
        } else if (CAL_CONST == Calendar.HOUR_OF_DAY) {
            time = time * 60 * 60;
        } else if (CAL_CONST == Calendar.MINUTE) {
            time = time * 60;
        } else {
            throw new RuntimeException("Unknown calendar constant");
        }
        return (date1.getTime() - date0.getTime()) / time;
    }
    
    /**
     * Subtract between two date (date1 > date0), time (hour, minute, second are ignored) with bankDate
     * e.g: bankDate = 30, date1 = 12 September 2010, date2 = 22 August 2010 then ( (30-22 = 8 days) + 12 days) -1 as previous day = 19 days 
     * @return number of days
     * */
    public static long calcBankDateDifference(int CAL_CONST, long bankDate, Date date0, Date date1) throws RuntimeException {
        long time = 1000;
        long days = 0;
        if (CAL_CONST == Calendar.DAY_OF_MONTH) {
            time = time * 60 * 60 * 24;
        } else if (CAL_CONST == Calendar.HOUR_OF_DAY) {
            time = time * 60 * 60;
        } else if (CAL_CONST == Calendar.MINUTE) {
            time = time * 60;
        } else {
            throw new RuntimeException("Unknown calendar constant");
        }
        
        if ( DateTimeFunction.date2String(date0, "yyyyMM").equalsIgnoreCase(DateTimeFunction.date2String(date1, "yyyyMM")) ) {
        	Calendar cal1 = Calendar.getInstance();
            cal1.setTime(date1);
            long days1 = cal1.get(Calendar.DAY_OF_MONTH);
            if (days1 > bankDate)
            	cal1.set(Calendar.DAY_OF_MONTH, (int) bankDate);
            days = (cal1.getTimeInMillis() - date0.getTime()) / time;
        }
        else
        {
        	Calendar cal0 = Calendar.getInstance();
            cal0.setTime(date0);
            int month0 = cal0.get(Calendar.MONTH);
            int year0 = cal0.get(Calendar.YEAR);
            
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(date1);
            int month1 = cal1.get(Calendar.MONTH);
            int year1 = cal1.get(Calendar.YEAR);
            
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date1);
            
            long firstMonthDays = bankDate - ( cal0.get(Calendar.DAY_OF_MONTH)>bankDate?bankDate:cal0.get(Calendar.DAY_OF_MONTH) );
            long lastMonthDays = cal1.get(Calendar.DAY_OF_MONTH)>bankDate?bankDate:cal1.get(Calendar.DAY_OF_MONTH);
            
            days = firstMonthDays + lastMonthDays;

            if (year1>year0) 
            {
        		for (int i=0 ; i< (11-month0) ; i++) {
        			days += bankDate;
        		}
        		
        		for (int i=0 ; i< (month1-0) ; i++) {
        			days += bankDate;
        		}
            	
        		if (year1-year0 > 1) {
        			for (int i=1 ; i< (year1-year0) ; i++) {
        				for (int j=0 ; j<12 ; j++) {
        					days += bankDate;
        				}
        			}
        		}
            }
            else
            {
            	if ((month1-month0)>1 ) {
            		for (int i=1 ; i< (month1-month0) ; i++) {
            			days += bankDate;
            		}
            	}
            	
            }
        }
        
        return days;
    }

    public static String Integer2String(Integer integer) {
    	if (integer == null) {
    		return "";
    	}
    	return int2String(integer);
    }
    
    public static String int2String(int integer) {
        return long2String(integer);
    }

    public static String long2String(long number) {
        return "" + number;
    }

    /*public static String double2String(double d) {
        return (new DecimalFormat(SystemConstant.DECIMAL_MASK)).format(d);
    }*/

    /**
     * return 1'st date of current month
     * @return
     */
    public static Date getFirstDateOfCurrentMonth(){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH , 1);
        return cal.getTime();
    }
    
    /**
     * return 1'st date of current week
     * @return
     */
    public static Date getFirstDateOfCurrentWeek(){
    	Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK , 1);
        return cal.getTime();
    }
    
    /**
     * get last date of previous month
     * @return
     */
    public static Date getLastDateOfPrevMonth(){
        Calendar cal = Calendar.getInstance();
        int month  = cal.get(Calendar.MONTH);
        if(month<0) month = 11;
        cal.set(Calendar.MONTH,month-1);
        cal.set(Calendar.DATE,cal.getActualMaximum(Calendar.DATE));
        return cal.getTime();
    }
    /**
     * get first date of previous month
     * @return
     */
    public static Date getFirstDateOfPrevMonth(){
        Calendar cal = Calendar.getInstance();
        int month =  cal.get(Calendar.MONTH);
        if(month<0)  month =11;
        cal.set(Calendar.MONTH,month-1);
        cal.set(Calendar.DATE, 1);
        return cal.getTime();
    }
    
    /**
     * get first date of next month
     * @return
     */
    public static Date getFirstDateOfNextMonth(){
        Calendar cal = Calendar.getInstance();
        int month =  cal.get(Calendar.MONTH);
        if(month<0)  month =11;
        cal.set(Calendar.MONTH,month+1);
        cal.set(Calendar.DATE, 1);
        return cal.getTime();
    }
    /**
     * get last date of current month
     * @return
     */
    public static Date getLastDateOfCurrentMonth(){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DATE,cal.getActualMaximum(Calendar.DATE));
        return cal.getTime();
    }
    
    public static Date getLastDateOfMonth(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DATE,cal.getActualMaximum(Calendar.DATE));
        return cal.getTime();
    }
    public static int getCurrentDayOfMonth(){
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.DAY_OF_MONTH);
    }
    public static String getMonthName(int intMonthNumber) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, intMonthNumber - 1);
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM");
        String monthName = sdf.format(cal.getTime());
        return monthName;
    }

    /**
     * Compare two date, ignoring the time
     *
     * the value 0 if date1 is equal to date2
     * the value less than 0 date1 is before date2
     * the value greater than 0 if date1 is after date2.
     * @param date1
     * @param date2
     * @return
     */
    public static int compareDate(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        cal1.set(Calendar.HOUR_OF_DAY, 0);
        cal1.set(Calendar.MINUTE, 0);
        cal1.set(Calendar.SECOND, 0);
        cal1.set(Calendar.MILLISECOND, 0);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        cal2.set(Calendar.HOUR_OF_DAY, 0);
        cal2.set(Calendar.MINUTE, 0);
        cal2.set(Calendar.SECOND, 0);
        cal2.set(Calendar.MILLISECOND, 0);

        return cal1.getTime().compareTo(cal2.getTime());
    }
    
    

    /**
     * @param myDate
     * @param i
     * @param j
     * @return
     */
    public static Date addDate(Date myDate, int amount, int CAL_CONSTANT) {
        Calendar c = Calendar.getInstance();
        c.setTime(myDate);
        c.add(CAL_CONSTANT, amount);
        return c.getTime();
    }

    /**
     * @param curDate
     * @return
     */
    public static Date truncateDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date getDate(int year, int month, int day ) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);
        return cal.getTime();
    }
    //add by Dodi
    //private static Pattern timePattern = Pattern.compile("([0-1]\\d|2[1-3]):[0-5]\\d:[0-5]\\d"); // 24h format: 00:00:00 - 23:59:59
    private static Pattern timePattern = Pattern.compile("([0-1]\\d|2[1-3]):[0-5]\\d"); // 24h format: 00:00:00 - 23:59:59
    public static boolean isValidTimeFormat(String value){
        Matcher matcher = timePattern.matcher(value);
        return matcher.matches();
    }
    
    /**
     * To get number of days in specified year and month
     * 
     * @param year
     * @param month
     * @return
     */
    public static int getNumOfDaysInMonth(int year, int month) {
    	Calendar calendar = new GregorianCalendar(year, month, 1);
    	
    	return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
    
    /**
     * To get number of days in specified month and in the current year
     * 
     * @param month
     * @return
     */
    public static int getNumOfDaysInMonth(int month) {
    	Calendar calendar = Calendar.getInstance();
    	Calendar calendar2 = new GregorianCalendar(calendar.get(Calendar.YEAR), month, 1);
    	
    	return calendar2.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
    
    /**
     * To get the last Friday of the current month and year.
     * 
     * @return
     */
    public static Date getLastFriday() {
    	Calendar calendar = Calendar.getInstance();
    	calendar.roll(Calendar.MONTH, 1);
    	calendar.set(Calendar.DAY_OF_MONTH, 1);
    	calendar.add(Calendar.DAY_OF_MONTH, -(calendar.get(Calendar.DAY_OF_WEEK) % 7 + 1));
    	
    	return calendar.getTime();
    }

    /***
     * Validate date (year=should be between 1900 - 2100)
     * 
     * @param date - java.util.Date object
     * @return true if date is valid, false if date is invalid
     */
    public static boolean validateDate(Date date) {
    	if (date != null) {
    		Calendar cal = Calendar.getInstance();
    		cal.setTime(date);
    		
    		int year = cal.get(Calendar.YEAR);

    		if (year < 1900 || year > 2100) {
    			return false;
    		}
    		return true;
    	}
    	return false;
    }
    
    /***
     * Validate date (year=should be between 1900 - 2100)
     * 
     * @param date - XMLGregorianCalendar object
     * @return true if date is valid, false if date is invalid
     */
    public static boolean validateDate(XMLGregorianCalendar date) {
    	if (date != null) {
    		int year = date.getYear();

    		if (year < 1900 || year > 2100) {
    			return false;
    		}
    		return true;
    	}
    	return false;
    }
}
