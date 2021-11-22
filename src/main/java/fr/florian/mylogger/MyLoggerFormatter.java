package fr.florian.mylogger;

import fr.florian.mylogger.enums.MyLogType;

import java.math.BigInteger;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class MyLoggerFormatter {

    private static String format = "{line} ({trace}) [{name}] [{type}] {date} : {message}";
    private static String name = "LOGGER", version = null;
    private static long processId = ProcessHandle.current().pid();
    private static String dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
    private static ZoneId zoneId = ZoneId.systemDefault();
    private static BigInteger nextLine = new BigInteger("1");


    public static String formatMessage(MyLogType myLogType, ZonedDateTime zonedDateTime, String message, boolean colored) {
        String result = format;
        result = result.replaceAll("\\{line}", String.format("%08d", nextLine));
        result = result.replaceAll("\\{trace}", getFormattedTrace());
        result = result.replaceAll("\\{name}", name);
        result = result.replaceAll("\\{version}", version);
        result = result.replaceAll("\\{process}", String.valueOf(processId));
        result = result.replaceAll("\\{type}", (colored) ? myLogType.getPrefixColored() : myLogType.getPrefix());
        result = result.replaceAll("\\{date}", getFormattedDate(zonedDateTime));
        result = result.replaceAll("\\{message}", message);
        return result;
    }

    private static String getFormattedTrace() {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        StackTraceElement stackTraceElement = stackTraceElements[5];
        return stackTraceElement.getFileName() + ":" + stackTraceElement.getMethodName() + ":" + stackTraceElement.getLineNumber();
    }

    private static String getFormattedDate(ZonedDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(getDateFormat());
        return formatter.format(date);
    }

    /*
     *      GETTER & SETTER
     */

    public static String getFormat() {
        return format;
    }

    public static void setFormat(String format) {
        MyLoggerFormatter.format = format;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        MyLoggerFormatter.name = name;
    }

    public static String getDateFormat() {
        return dateFormat;
    }

    public static void setDateFormat(String dateFormat) {
        MyLoggerFormatter.dateFormat = dateFormat;
    }

    public static ZoneId getZoneId() {
        return zoneId;
    }

    public static void setZoneId(ZoneId zoneId) {
        MyLoggerFormatter.zoneId = zoneId;
    }

    public static BigInteger getNextLine() {
        return nextLine;
    }

    public static void setNextLine(BigInteger nextLine) {
        MyLoggerFormatter.nextLine = nextLine;
    }
}
