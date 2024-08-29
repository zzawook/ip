package components;

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

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    public boolean getHasTime() {
        return this.hasTime;
    }

    public int compareTo(DateTimeData other) {
        return this.dateTime.compareTo(other.getDateTime());
    }

    public static boolean isDateTime(String input) {
        try {
            LocalDateTime.parse(input);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean isDateTimeWithTime(String input) {
        try {
            LocalDateTime.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static String formatDate(DateTimeData dateTimeData) {
        if (dateTimeData.getHasTime()) {
            return dateTimeData.getDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } else {
            return dateTimeData.getDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
    }
}
