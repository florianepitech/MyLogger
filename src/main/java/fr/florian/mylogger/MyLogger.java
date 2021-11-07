package fr.florian.mylogger;

import org.apache.commons.lang3.exception.ExceptionUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
    private static boolean saveToFile = false;

    private static String fileName = "mylogger-" + System.currentTimeMillis() + ".log";

    /*
     *      PUBLIC FUNCTION
     */

    public static void debug(String message) {
        log(MyLogType.DEBUG, message);
    }

    public static void info(String message) {
        log(MyLogType.INFO, message);
    }

    public static void error(String message) {
        log(MyLogType.ERROR, message);
    }

    public static void error(Exception exception) {
        log(MyLogType.ERROR, ExceptionUtils.getMessage(exception));
    }

    public static void exit(String message) {
        log(MyLogType.EXIT, message);
        System.exit(2);
    }

    public static void exit(String message, int exitCode) {
        log(MyLogType.EXIT, message);
        System.exit(exitCode);
    }

    public static void success(String message) {
        log(MyLogType.SUCCESS, message);
    }

    public static void fail(String message) {
        log(MyLogType.FAIL, message);
    }


    /*
     *      PRIVATE FUNCTION
     */

    private synchronized static void log(MyLogType logType, String message) {
        if (logType == MyLogType.DEBUG && printDebug == false) return;
        ZonedDateTime now = ZonedDateTime.now(getZoneId());
        String messageTextTerminal = getNextLine() + " " +  PREFIX + " " + logType.getPrefixColored() + " " + getFormattedDate(now) + " : " + message;
        if (!isPrintColored()) messageTextTerminal = getNextLine() + " " +  PREFIX + " " + logType.getPrefix() + " " + getFormattedDate(now) + " : " + message;
        System.out.println(messageTextTerminal);
        nextLine = nextLine.add(new BigInteger("1"));
        if (isSaveToFile()) saveLogToFile(logType, message);
    }

    private static void saveLogToFile(MyLogType logType, String message) {
        ZonedDateTime now = ZonedDateTime.now(getZoneId());
        String strtToAdd = getNextLine() + " " +  PREFIX + " " + logType.getPrefix() + " " + getFormattedDate(now) + " : " + message + "\n";
        try {
            File file = new File(fileName);
            if (!file.exists()) file.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
            writer.append(strtToAdd);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public static boolean isSaveToFile() {
        return saveToFile;
    }

    public static void setSaveToFile(boolean saveToFile) {
        MyLogger.saveToFile = saveToFile;
    }
}
