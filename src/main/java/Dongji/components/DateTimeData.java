package Dongji.components;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeData implements Comparable<DateTimeData> {
    private LocalDateTime dateTime;
    private boolean hasTime;

    public DateTimeData(LocalDateTime dateTime, boolean hasTime) {
        this.dateTime = dateTime;
        this.hasTime = hasTime;
    }

    
    /** 
     * Returns the date and time of the DateTimeData as LocalDateTime
     * 
     * @return LocalDateTime
     */
    public LocalDateTime getDateTime() {
        return this.dateTime;
    }
    
    /** 
     * Returns whether the DateTimeData has time
     * 
     * @return boolean
     */
    public boolean hasTime() {
        return this.hasTime;
    }

    
    /** 
     * Compares the DateTimeData object with another DateTimeData object. 
     * Returns positive value if this object is later than the other object, 
     * negative value if this object is earlier than the other object, 
     * and 0 if they are the same.
     * 
     * @param other
     * @return int
     */
    public int compareTo(DateTimeData other) {
        return this.dateTime.compareTo(other.getDateTime());
    }
    
    /** 
     * Returns whether the input string is a valid date and time
     * 
     * @param input
     * @return boolean
     */
    public static boolean isDateTime(String input) {
        try {
            LocalDateTime.parse(input);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    
    /** 
     * Returns whether the input string is a valid date and time with time
     * 
     * @param input
     * @return boolean
     */
    public static boolean isDateTimeWithTime(String input) {
        try {
            LocalDateTime.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    
    /** 
     * @param dateTimeData
     * @return String
     */
    public static String formatDate(DateTimeData dateTimeData) {
        if (dateTimeData.hasTime()) {
            return dateTimeData.getDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } else {
            return dateTimeData.getDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
    }
}
