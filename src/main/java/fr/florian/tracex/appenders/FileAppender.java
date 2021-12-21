package fr.florian.tracex.appenders;

import fr.florian.tracex.TraceListener;
import fr.florian.tracex.objects.TraceMessage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileAppender implements TraceListener {

    private static boolean saveToFile = false;
    private static String fileName = "tracex-" + System.currentTimeMillis() + ".log";

    public FileAppender(String name) {
        this.fileName = name + ".log";
    }

    public FileAppender() {

    }

    @Override
    public void onLogEvent(TraceMessage message) {

    }

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

    /*
     *      GETTER & SETTER
     */

    public static boolean isSaveToFile() {
        return saveToFile;
    }

    public static void setSaveToFile(boolean saveToFile) {
        FileAppender.saveToFile = saveToFile;
    }

    public static String getFileName() {
        return fileName;
    }

    public static void setFileName(String fileName) {
        FileAppender.fileName = fileName;
    }

}
