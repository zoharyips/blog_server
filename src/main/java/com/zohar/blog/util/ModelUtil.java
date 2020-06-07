package com.zohar.blog.util;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Project:      com.zohar.blog.util
 * Class:        blog
 * Description:
 * Time:         6/7/2020 10:39 PM
 *
 * @author zohar
 **/
public class ModelUtil {
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static Long updateTimeSecondInterval(Date date, Date updateTime) {
        return date.getTime() - updateTime.getTime();
    }

    public static Long updateTimeMinInterval(LocalDateTime localDateTime, String updateTime) {
        Duration duration = Duration.between(localDateTime, LocalDateTime.parse(updateTime, DATE_TIME_FORMATTER));
        return duration.toMinutes();
    }

    public static String currentTime() {
        return DATE_TIME_FORMATTER.format(LocalDateTime.now());
    }
}
