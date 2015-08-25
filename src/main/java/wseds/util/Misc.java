

package wseds.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Misc {
         
    public static String getDateAndTimeAsString() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy @ HH mm");
        return sdf.format(date);
    }
    
    // Prints an exception's stacktrace.
    public static void printStackTrace(Exception e) { 
        System.out.println(e.getMessage());
        StackTraceElement[] stackTrace = e.getStackTrace();
        for (StackTraceElement stackTraceElem : stackTrace) {
            System.out.println(stackTraceElem.toString());
        }
    }     
}