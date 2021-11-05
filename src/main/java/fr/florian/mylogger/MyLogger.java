import org.apache.commons.lang3.exception.ExceptionUtils;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class MyLogger {

    //Param
    private static String PREFIX = "[LOGGER]";
    private static String dateFormat = "dd/MM/yyyy HH'h'mm:ss.SSS";
    private static ZoneId zoneId = ZoneId.systemDefault();
    private static BigInteger line = new BigInteger("1");

    /*
     *      PUBLIC FUNCTION
     */

    public static void debug(String message) {
        log(MyLoggerType.DEBUG, message);
    }

    public static void info(String message) {
        log(MyLoggerType.INFO, message);
    }

    public static void error(String message) {
        log(MyLoggerType.ERROR, message);
    }

    public static void error(Exception exception) {
        log(MyLoggerType.ERROR, ExceptionUtils.getMessage(exception));
    }

    public static void exit(String message) {
        log(MyLoggerType.EXIT, message);
        System.exit(2);
    }

    public static void exit(String message, int exitCode) {
        log(MyLoggerType.EXIT, message);
        System.exit(exitCode);
    }

    /*
     *      PRIVATE FUNCTION
     */

    private synchronized static void log(MyLoggerType logType, String message) {
        ZonedDateTime now = ZonedDateTime.now(getZoneId());
        String messageTextTerminal = getLine() + " " +  PREFIX + " " + logType.getPrefixTerminal() + " " + getFormattedDate(now) + " : " + message;
        System.out.println(messageTextTerminal);
        line.add(new BigInteger("1"));
    }

    /*
     *      PUBLIC FUNCTION
     */

    public static String getFormattedDate(ZonedDateTime date) {
        SimpleDateFormat formatter = new SimpleDateFormat(getDateFormat());
        return formatter.format(date);
    }

    /*
     *      PRIVATE FUNCTION
     */

    public static void setLine(BigInteger line) {
        MyLogger.line = line;
    }

    public static String getLine() {
        return String.format("%08d", line);
    }

    public static String getPrefix() {
        return PREFIX;
    }

    public static void setPrefix(String name) {
        PREFIX = "[" + name.toUpperCase() + "]";
    }

    public static String getDateFormat() {
        return dateFormat;
    }

    public static void setDateFormat(String dateFormat) {
        MyLogger.dateFormat = dateFormat;
    }

    public static ZoneId getZoneId() {
        return zoneId;
    }

    public static void setZoneId(ZoneId zoneId) {
        MyLogger.zoneId = zoneId;
    }

}
