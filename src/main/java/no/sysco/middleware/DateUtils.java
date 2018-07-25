package no.sysco.middleware;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 100tsa
 */
public class DateUtils {

    public static String convertDateFormat(String date) {
        try {
            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDateObj = org.apache.commons.lang.time.DateUtils.parseDateStrictly(date, new String[]{
                "yyyy-MM-dd",
                "dd.MM.yyyy",
                "dd-MM-yyyy",
                "dd/MM/yyyy",
                "ddMMyyyy"
            });
            return dateFormatter.format(parsedDateObj);
        } catch (ParseException ex) {
            Logger.getLogger(DateUtils.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
