package dongji.components;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a date and time data.
 * Wrapper for LocalDateTime with additional information on whether the date has
 * time.
 */
public class DateTimeData implements Comparable<DateTimeData> {
    private LocalDateTime dateTime;
    private boolean hasTime;

    /**
     * Constructor for DateTimeData
     * 
     * @param dateTime
     * @param hasTime
     */
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
     * @return boolean
     */
    public boolean hasTime() {
        return this.hasTime;
    }

    /**
     * Compares the DateTimeData object with another DateTimeData object. Returns
     * positive value if this object is later than the other object, negative value
     * if this object is earlier than the other object, and 0 if they are the same.
     * 
     * @param other - DateTimeData object to be compared with
     * @return int
     */
    public int compareTo(DateTimeData other) {
        return this.dateTime.compareTo(other.getDateTime());
    }

    /**
     * Returns DateTimeData object parsed from input String.
     * 
     * @param input - input string to be parsed
     * @return DateTimeData - parsed DateTimeData object
     * @throws DateTimeParseException
     */
    public static DateTimeData fromString(String input) throws DateTimeParseException {
        boolean hasTime = input.contains(" ");
        if (!hasTime) {
            input += " 0000";
        }
        return new DateTimeData(LocalDateTime.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")), hasTime);
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
