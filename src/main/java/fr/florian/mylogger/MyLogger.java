package fr.florian.mylogger;

import fr.florian.mylogger.enums.MyLogType;
import fr.florian.mylogger.saver.MyLoggerSaver;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.math.BigInteger;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class MyLogger {

    //Param
    private static List<MyLoggerListener> listeners = new ArrayList<MyLoggerListener>();
    private static boolean printColored = true, printDebug = false;

    /*
     *      LISTENER
     */

    public static void registerListener(MyLoggerListener myLoggerListener) {
        listeners.add(myLoggerListener);
    }

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
        for (MyLoggerListener mll : listeners) mll.onLogEvent(messageTextTerminal);
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
