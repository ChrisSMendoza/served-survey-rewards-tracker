package com.chris.codes.served;

import java.util.Calendar;
import java.util.Date;

/*
from https://stackoverflow.com/questions/428918/how-can-i-increment-a-date-by-one-day-in-java
needed to meet APIs under 24
*/
public class DateUtil {

    public static Date addDays(Date date, int numDays) {

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);
        calendar.add(Calendar.DATE, numDays);

        return calendar.getTime();
    }

}
