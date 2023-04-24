package com.kk.generator.util;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * @ProjectName: hyspgxptkf
 * @Package: com.vdata.cloud.hyspgxptkf.util
 * @ClassName: DateTimeUtils
 * @Author: HK
 * @Description:
 * @Date: 2023/4/4 14:25
 * @Version: 1.0
 */
public class DateTimeUtils {

    public static long getCurrentDateTimeSeconds() {
        Clock clock = Clock.systemUTC();
        Instant instant = Instant.now(clock);
        return instant.getEpochSecond();
    }

    public static String formatDateTime(Instant instant) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
        return formatter.format(zonedDateTime);
    }

    public static String formatDateTime(long timestamp) {
        Instant instant = Instant.ofEpochSecond(timestamp);
        return formatDateTime(instant);
    }

    public static long formateDateTime(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(dateString, formatter);
        return dateTime.toInstant(ZoneOffset.of("+8")).getEpochSecond();
    }
}
