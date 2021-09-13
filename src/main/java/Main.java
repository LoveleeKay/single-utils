import utils.OptionalTest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.HOURS;

public class Main {
    public static void main(String[] args) throws ParseException {
        Date date1 = new Date();
        Instant instant1 = date1.toInstant();
        LocalDateTime localDate1 = instant1.atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime localDate2 = instant1.atZone(ZoneId.systemDefault()).toLocalDateTime();
        localDate2 = localDate2.plusMinutes(60 * 25);
        System.out.println("localDate1=" + localDate1);
        System.out.println("localDate2=" + localDate2);
        Duration duration = Duration.between(localDate1, localDate2);
        long millis = duration.toMillis();
        System.out.println(millis);
        long day = millis/(24 * 60 * 60 * 1000);
        long moreDay = millis % (24 * 60 * 60 * 1000) > 0 ? 1 : 0;
        System.out.println(day + moreDay);
        System.out.println(localDate1.format(DateTimeFormatter.ofPattern("yyyy.MM.dd")));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(simpleDateFormat.parse("2021-08-05 00:00:00.0"));
        ClassLoader classLoader = Main.class.getClassLoader();
        System.out.println(classLoader);
    }
}
