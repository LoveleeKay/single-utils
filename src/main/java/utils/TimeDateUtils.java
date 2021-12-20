package utils;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;

public class TimeDateUtils {

    public static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String getDateTimeAsString(LocalDateTime localDateTime, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return localDateTime.format(formatter);
    }

    public static LocalDateTime getDateTimeOfTimestamp(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

    public static long getTimestampOfDateTime(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return instant.toEpochMilli();
    }

    public static LocalDateTime parseStringToDateTime(String time, String format) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(time, df);
    }

    public static Duration computeTime(Temporal startInclusive, Temporal endExclusive) {
        Duration duration = Duration.between(startInclusive, endExclusive);
        return duration;
    }

    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("localDateTime= " + localDateTime);

        String timeStr = getDateTimeAsString(localDateTime, TIME_FORMAT);
        System.out.println("timeStr= " + timeStr);

        long timeLong = getTimestampOfDateTime(localDateTime);
        System.out.println("timeLong= " + timeLong);

        long selfLong = 1630475012000L;
        LocalDateTime localDateTime1 = getDateTimeOfTimestamp(selfLong);
        System.out.println("localDateTime1= " + localDateTime1);

        LocalDateTime localDateTime2 = parseStringToDateTime("2021-08-05 00:00:00.0", TIME_FORMAT);
        System.out.println("localDateTime2= " + localDateTime2);
    }

}
