package com.example.jks.databse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by jks on 23.09.14.
 */
public class MyDateHandler {

    public static String getDateTime(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }


    public static String compareDates(String date1) throws ParseException {
        String re = "";
        Date d1 = null;
        Date d2 = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            d1 = format.parse(date1);
            d2 = format.parse(getDateTime());
            long diff = d2.getTime() - d1.getTime();
            long diffDays = diff/(24*60*60*1000);
            re = Long.toString(diffDays);
        } catch (Exception e){
            e.printStackTrace();
        }
        return re;
    }

}
