package fr.florian.mylogger.saver;

import java.time.ZonedDateTime;

public class MyLoggerSQL {

    private static String tableName;
    private static String host, database, user, password;
    private static boolean start = false;

    public static void start() {

    }

    public static void stop() {

    }

    public static void setConfiguration(String host, String database, String user, String password) {
        MyLoggerSQL.host = host;
        MyLoggerSQL.database = database;
        MyLoggerSQL.user = user;
        MyLoggerSQL.password = password;
    }

    public static void setTableName(String tableName) {
        MyLoggerSQL.tableName = tableName;
    }

    private static boolean tableExist() {
        return false;
    }

    private static boolean createTable() {
        return false;
    }

    /*
     *      DELETE
     */

    public static void deleteAllLogs() {

    }

    public static void deleteLogsBefore(ZonedDateTime zonedDateTime) {

    }

    public static void deleteLogsAfter(ZonedDateTime zonedDateTime) {

    }

    public static void addLog() {

    }

}
