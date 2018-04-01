package com.swof.utils;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class DateUtils {
    private static String[] weekDays = new String[]{"MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY"};

    public static long getDate(Date now) {
        long dateMillis = now.getTime();

        List<String> strings = Arrays.stream(weekDays).collect(Collectors.toList());
        SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE");
        String dayStr = simpleDateformat.format(now);

        while (!strings.contains(dayStr.toUpperCase())) {
            dateMillis = dateMillis + (24 * 60 * 60 * 1000);
            now = new Date(dateMillis);
            dayStr = simpleDateformat.format(now);
        }
        return dateMillis;
    }
}
