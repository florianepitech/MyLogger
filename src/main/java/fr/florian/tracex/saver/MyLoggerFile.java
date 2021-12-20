package fr.florian.tracex.saver;

import fr.florian.tracex.MyLoggerFormatter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MyLoggerFile {

    private static boolean saveToFile = false;
    private static String fileName = MyLoggerFormatter.getName().toLowerCase() + "-" + System.currentTimeMillis() + ".log";

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
        MyLoggerFile.saveToFile = saveToFile;
    }

    public static String getFileName() {
        return fileName;
    }

    public static void setFileName(String fileName) {
        MyLoggerFile.fileName = fileName;
    }
}
