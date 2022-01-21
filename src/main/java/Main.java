import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Main {
    public static void main(String[] args) throws ParseException {
        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime = localDateTime.plusMinutes(2);
        System.out.println(localDateTime);
        System.out.println(System.currentTimeMillis());
        System.out.println(localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());

        System.out.println(String.valueOf(null));

    }



    private static void testSb(StringBuilder sb) {
        sb.append(123);
    }
}
