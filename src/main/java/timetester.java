import Util.CurrentTime;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Natox on 3/26/2017.
 */
public class timetester {


    public static void main(String[] args) {


        //////TIME CONVERTER
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd HH:mm");

        LocalDateTime dateTime1= LocalDateTime.parse(DialogSubjectSelector.time, formatter);
        LocalDateTime dateTime2= LocalDateTime.parse(CurrentTime.systemtime(), formatter);

        long diffInSeconds = java.time.Duration.between(dateTime1, dateTime2).getSeconds();
        long diffInMinutes = java.time.Duration.between(dateTime1, dateTime2).toMinutes();


        System.out.println(diffInMinutes + diffInSeconds);

    }


}
