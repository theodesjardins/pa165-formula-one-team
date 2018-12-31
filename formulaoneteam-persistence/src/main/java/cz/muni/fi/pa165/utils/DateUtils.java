package cz.muni.fi.pa165.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
public class DateUtils {

    private static final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    public static String getFormattedDate(Date date) {
        return format.format(date);
    }

    public static Date parseDate(String formattedDate) {
        try {
            return format.parse(formattedDate);
        } catch (ParseException e) {
            return new Date();
        }
    }

    private DateUtils() {
        throw new IllegalAccessError("This constructor should never get called.");
    }
}
