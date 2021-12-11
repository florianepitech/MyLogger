package fr.florian.mylogger;

import fr.florian.mylogger.enums.MyLogType;
import fr.florian.mylogger.saver.MyLoggerFile;
import fr.florian.mylogger.saver.MyLoggerNoSQL;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.math.BigInteger;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class MyLogger {

    //Param
    private static List<MyLoggerListener> listeners = new ArrayList<MyLoggerListener>();
    private static boolean printColored = true;

    /*
     *      LISTENER
     */

    public static void registerListener(MyLoggerListener myLoggerListener) {
        listeners.add(myLoggerListener);
    }

    /*
     *      PUBLIC FUNCTION
     */

    public static void trace(Object message) {
        log(MyLogType.TRACE, message);
    }

    public static void debug(Object message) {
        log(MyLogType.DEBUG, message);
    }

    public static void info(Object message) {
        log(MyLogType.INFO, message);
    }

    public static void warn(Object message) {
        log(MyLogType.WARN, message);
    }

    public static void error(Object message) {
        log(MyLogType.ERROR, message);
    }

    public static void error(Exception exception) {
        log(MyLogType.ERROR, ExceptionUtils.getMessage(exception));
    }

    public static void fatal(Object message) {
        log(MyLogType.FATAL, message);
        System.exit(2);
    }

    public static void fatal(Object message, int exitCode) {
        log(MyLogType.FATAL, message);
        System.exit(exitCode);
    }


    /*
     *      PRIVATE FUNCTION
     */

    private synchronized static void log(MyLogType logType, Object message) {
        if (message == null) message = "null";
        ZonedDateTime now = ZonedDateTime.now(MyLoggerFormatter.getZoneId());
        String messageTextTerminal = MyLoggerFormatter.formatMessage(logType, now, message.toString(), printColored);
        System.out.println(messageTextTerminal);
        MyLoggerFormatter.setNextLine(MyLoggerFormatter.getNextLine().add(new BigInteger("1")));
        //save to file
        if (MyLoggerFile.isSaveToFile()) MyLoggerFile.saveLogToFile(MyLoggerFormatter.formatMessage(logType, now, message.toString(), false));
        if (MyLoggerNoSQL.isStart()) {
            try {
                MyLoggerNoSQL.addLog(now, logType, message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //call listeners
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

}
