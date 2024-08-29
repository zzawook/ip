package components.parsers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import components.DateTimeData;

public class DateTimeParser {
    public static DateTimeData extractDateTime(String input) throws DateTimeParseException{
        boolean hasTime = input.contains(" ");
        if (! hasTime) {
            input += " 0000";
        }
        System.out.println(input);
        return new DateTimeData(LocalDateTime.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")), hasTime);
    }
}
