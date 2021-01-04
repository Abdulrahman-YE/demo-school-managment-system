package utils;

import java.sql.Date;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    private static final String DATE_PATTERN = "YYYY-MM-dd";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

    /**
     * Returns the given date as a well formatted String. The above defined
     * {@link DateUtil#DATE_PATTERN} is used.
     *
     * @param date the date to be returned as a string
     * @return formatted string
     */
    public static String format(LocalDate date) {
        if (date == null) {
            return null;
        }
        return DATE_FORMATTER.format(date);
    }

    /**
     * Converts a String in the format of the defined {@link DateUtil#DATE_PATTERN}
     * to a {@link LocalDate} object.
     * <p>
     * Returns null if the String could not be converted.
     *
     * @param date String the date as String
     * @return the date object or null if it could not be converted
     */
    public static LocalDate parse(String date) {
        try {
            LocalDate localDate = LocalDate.parse(date);
            return localDate;

        } catch (DateTimeException e) {
            return null;
        }
    }

    /**
     * Converts a {@link LocalDate}to {@link java.sql.Date}
     * <p>
     * <p>
     * Returns null if date is null.
     *
     * @param date the {@link LocalDate} object
     * @return the {@link Date} objecto null if  date is null
     */

    public static Date toSqlDate(LocalDate date) {
        if (date == null) {
            return null;
        }
        return Date.valueOf(date);
    }

    /**
     * Converts a {@link Date}to {@link LocalDate}
     * <p>
     * <p>
     * Returns null if sqlDate is null.
     *
     * @param sqlDate the {@link Date} object
     * @return the {@link LocalDate} object null if  sqlDate is null
     */
    public static LocalDate toLocalDate(Date sqlDate) {
        if (sqlDate == null) {
            return null;
        }
        return sqlDate.toLocalDate();
    }

    /**
     * Checks the String whether it is a valid date.
     *
     * @param dateString
     * @return true if the String is a valid date
     */
    public static boolean validDate(String dateString) {
        // Try to parse the String.
        return DateUtil.parse(dateString) != null;
    }

}
