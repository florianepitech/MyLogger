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
import java.util.Arrays;

public class MyLogger {

    //Param
    private static boolean printColored = true, printDebug = false;

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

    public static void done(String message) {
        log(MyLogType.DONE, message);
    }

    public static void fail(String message) {
        log(MyLogType.FAIL, message);
    }


    /*
     *      PRIVATE FUNCTION
     */

    private synchronized static void log(MyLogType logType, String message) {
        ZonedDateTime now = ZonedDateTime.now(MyLoggerFormatter.getZoneId());
        String messageTextTerminal = MyLoggerFormatter.formatMessage(logType, now, message, printColored);
        System.out.println(messageTextTerminal);
        MyLoggerFormatter.setNextLine(MyLoggerFormatter.getNextLine().add(new BigInteger("1")));
        if (MyLoggerSaver.isSaveToFile()) MyLoggerSaver.saveLogToFile(MyLoggerFormatter.formatMessage(logType, now, message, false));
    }

    /*
     *      PRIVATE FUNCTION
     */

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
