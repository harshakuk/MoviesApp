package com.example.moviesapp.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilDates {

    static public String formatDates(String date) {

        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd");

        Date myDate = null;
        try {
            myDate = dateFormat.parse(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat timeFormat = new SimpleDateFormat("dd MMM yyyy");
        String finalDate = timeFormat.format(myDate);

        return finalDate;
    }
}
