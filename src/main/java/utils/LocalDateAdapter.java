package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LocalDateAdapter {

    private static final Logger logger = LoggerFactory.getLogger(LocalDateAdapter.class);

    public static LocalDateTime unmarshal(String xmlGregorianCalendar) {
        try {
            LocalDateTime result = LocalDateTime.parse(xmlGregorianCalendar, DateTimeFormatter.ISO_DATE_TIME);
            return result;
        } catch (DateTimeParseException ex) {
            logger.error("Could not parse date" + xmlGregorianCalendar, ex);
            return null;
        }
    }

    public static String marshal(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ISO_DATE_TIME);
    }

}