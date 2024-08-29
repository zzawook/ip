package Dongji.components.parsers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import Dongji.components.DateTimeData;

public class DateTimeParser {
    
    /** 
     * Extracts the date and time from the input string and returns a DateTimeData object
     * 
     * @param input
     * @return DateTimeData
     * @throws DateTimeParseException
     */
    public static DateTimeData extractDateTime(String input) throws DateTimeParseException{
        boolean hasTime = input.contains(" ");
        if (! hasTime) {
            input += " 0000";
        }
        return new DateTimeData(LocalDateTime.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")), hasTime);
    }
}
