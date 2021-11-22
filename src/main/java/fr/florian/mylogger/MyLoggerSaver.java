package fr.florian.mylogger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.ZonedDateTime;

public class MyLoggerSaver {

    private static boolean saveToFile = false;
    private static String fileName = "mylogger-" + System.currentTimeMillis() + ".log";

    public static void saveLogToFile(String messageToSave) {
        try {
            File file = new File(fileName);
            if (!file.exists()) file.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
            writer.append(messageToSave);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isSaveToFile() {
        return saveToFile;
    }

    public static void setSaveToFile(boolean saveToFile) {
        MyLoggerSaver.saveToFile = saveToFile;
    }

    public static String getFileName() {
        return fileName;
    }

    public static void setFileName(String fileName) {
        MyLoggerSaver.fileName = fileName;
    }
}
