package dongji.components.tasks;

import dongji.exceptions.DongjiEmptyTaskNameException;
import dongji.exceptions.DongjiParseException;

/**
 * Represents a Recurring task.
 */
public class Recurring extends Task {
    private String cron;

    /**
     * Constructor for Recurring task.
     * 
     * @param name Name of the Recurring task.
     * @param cron Cron expression of the Recurring task.
     * @throws DongjiEmptyTaskNameException If the name of the Recurring task is empty.
     */
    public Recurring(String name, String cron) throws DongjiEmptyTaskNameException, DongjiParseException {
        super(name);
        if (!validateCron(cron)) {
            throw new DongjiParseException("Invalid cron expression");
        }
        this.cron = cron;
    }

    /**
     * Returns the cron expression of the Recurring task.
     * 
     * @return String
     */
    public String getCron() {
        return cron;
    }

    private boolean validateCron(String cron) {
        if (cron == null) {
            return false;
        }
        String[] splitted = cron.split(" ");
        if (splitted.length != 5) {
            return false;
        }

        for (String s : splitted) {
            if (!s.equals("*")) {
                try {
                    Integer.parseInt(s);
                } catch (NumberFormatException e) {
                    return false;
                }
            }
        }

        return true;
    }

    private String parseCronToString() {
        String[] splitted = this.cron.split(" ");
        String minuteString = parseCronPartToString(splitted[0], "minute");
        String hourString = parseCronPartToString(splitted[1], "hour");
        String monthString = this.parseMonthPartToString(splitted[3]);
        String dayAndDayOfWeekString = this.parseDayAndDayOfWeekPartToString(splitted[2], splitted[4]);

        return minuteString + " of " + hourString + " of " + dayAndDayOfWeekString + " in " + monthString;
    }

    private String parseDayAndDayOfWeekPartToString(String dayPart, String dayOfWeekPart) {
        if (dayPart.equals("*") && dayOfWeekPart.equals("*")) {
            return "every day";
        } else if (dayPart.equals("*")) {
            if (dayOfWeekPart.contains(",")) {
                return "every " + this.convertDayOfWeekPartsToString(dayOfWeekPart);
            }
            return "every " + this.convertDayOfWeekPartToString(dayOfWeekPart);
        } else if (dayOfWeekPart.equals("*")) {
            return "every " + this.parseCronPartToString(dayPart, "day of month");
        } else {
            return "every " + this.parseCronPartToString(dayPart, "day of month") + " and "
                    + this.convertDayOfWeekPartsToString(dayOfWeekPart) + "s";
        }
    }

    private String convertDayOfWeekPartsToString(String part) {
        String[] splitted = part.split(",");
        StringBuilder sb = new StringBuilder();
        for (String s : splitted) {
            sb.append(this.convertDayOfWeekPartToString(s)).append(", ");
        }
        sb.deleteCharAt(sb.length() - 2);

        return sb.toString();
    }

    private String convertDayOfWeekPartToString(String part) {
        switch (part) {
        case "1":
            return "Monday";
        case "2":
            return "Tuesday";
        case "3":
            return "Wednesday";
        case "4":
            return "Thursday";
        case "5":
            return "Friday";
        case "6":
            return "Saturday";
        case "7":
            return "Sunday";
        case "*":
            return "every";
        default:
            return "Invalid day of week";
        }
    }

    private String convertIntToMonth(String number) {
        switch (number) {
        case "1":
            return "January";
        case "2":
            return "February";
        case "3":
            return "March";
        case "4":
            return "April";
        case "5":
            return "May";
        case "6":
            return "June";
        case "7":
            return "July";
        case "8":
            return "August";
        case "9":
            return "September";
        case "10":
            return "October";
        case "11":
            return "November";
        case "12":
            return "December";
        case "*":
            return "every month";
        default:
            return "Invalid month";
        }
    }

    private String parseMonthPartToString(String monthPart) {
        if (monthPart.equals("*")) {
            return "every month";
        } else {
            if (monthPart.contains(",")) {
                String[] splitted = monthPart.split(",");
                StringBuilder sb = new StringBuilder();
                for (String s : splitted) {
                    sb.append(this.convertIntToMonth(s)).append(", ");
                }
                sb.deleteCharAt(sb.length() - 2);
                return sb.toString();
            }
            return this.convertIntToMonth(monthPart);
        }
    }

    private String parseCronPartToString(String part, String unit) {
        if (part.equals("*")) {
            return "every " + unit;
        } else {
            if (part.contains(",")) {
                String[] splitted = part.split(",");
                StringBuilder sb = new StringBuilder();
                for (String s : splitted) {
                    sb.append(this.convertToOrdinal(s)).append(", ");
                }
                sb.deleteCharAt(sb.length() - 2);
                return "every " + sb.toString() + unit + "s";
            }
            return this.convertToOrdinal(part) + " " + unit + "s";
        }
    }

    private String convertToOrdinal(String number) {
        assert number.length() == 1;

        int num = Integer.parseInt(number) % 10;
        if (num == 1) {
            return number + "st";
        } else if (num == 2) {
            return number + "nd";
        } else if (num == 3) {
            return number + "rd";
        } else {
            return number + "th";
        }
    }

    @Override
    public String toString() {
        return "[R]" + super.toString() + " (" + this.parseCronToString() + ")";
    }

}
