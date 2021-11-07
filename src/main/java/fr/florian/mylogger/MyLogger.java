package fr.florian.mylogger;

import org.apache.commons.lang3.exception.ExceptionUtils;

import java.math.BigInteger;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class MyLogger {

    //Param
    private static String PREFIX = "[LOGGER]";
    private static String dateFormat = "dd/MM/yyyy HH'h'mm:ss.SSS";
    private static ZoneId zoneId = ZoneId.systemDefault();
    private static BigInteger nextLine = new BigInteger("1");
    private static boolean printColored = true, printDebug = false;

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

    public static void success(String message) {
        log(MyLoggerType.SUCCESS, message);
    }

    public static void fail(String message) {
        log(MyLoggerType.FAIL, message);
    }


    /*
     *      PRIVATE FUNCTION
     */

    private synchronized static void log(MyLoggerType logType, String message) {
        if (logType == MyLoggerType.DEBUG && printDebug == false) return;
        ZonedDateTime now = ZonedDateTime.now(getZoneId());
        String messageTextTerminal = getNextLine() + " " +  PREFIX + " " + logType.getPrefixColored() + " " + getFormattedDate(now) + " : " + message;
        if (!isPrintColored()) messageTextTerminal = getNextLine() + " " +  PREFIX + " " + logType.getPrefix() + " " + getFormattedDate(now) + " : " + message;
        System.out.println(messageTextTerminal);
        nextLine = nextLine.add(new BigInteger("1"));
    }

    /*
     *      PUBLIC FUNCTION
     */

    public static String getFormattedDate(ZonedDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(getDateFormat());
        return formatter.format(date);
    }

    /*
     *      PRIVATE FUNCTION
     */

    public static void resetLine() {
        MyLogger.nextLine = new BigInteger("0");
    }

    public static void setLine(BigInteger line) {
        MyLogger.nextLine = line;
    }

    public static String getNextLine() {
        return String.format("%08d", nextLine);
    }

    public static String getPrefix() {
        return PREFIX;
    }

    public static void setPrefix(String name) {
        if (name == null) {
            PREFIX = "[NULL]";
        } else {
            PREFIX = "[" + name.toUpperCase() + "]";
        }
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

    public static boolean isPrintColored() {
        return printColored;
    }

    public static void setPrintColored(boolean printColored) {
        MyLogger.printColored = printColored;
    }

    public static boolean isPrintDebug() {
        return printDebug;
    }

    public static void setPrintDebug(boolean printDebug) {
        MyLogger.printDebug = printDebug;
    }
}
