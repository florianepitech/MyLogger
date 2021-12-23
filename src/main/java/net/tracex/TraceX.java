package net.tracex;

import net.tracex.priority.CustomPriority;
import net.tracex.priority.Priority;
import net.tracex.objects.TraceMessage;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class TraceX {

    private static List<TraceX> loggers = new ArrayList<>();

    private String appName = "TRACE-X", appVersion = "1";
    private List<TraceListener> listeners = new ArrayList<TraceListener>();
    private Class classReferenced;

    /*
     *      CONSTRUCTOR
     */

    private TraceX(Class classReferenced) {
        this.classReferenced = classReferenced;
        loggers.add(this);
    }

    /*
     *      STATIC FUNCTION
     */

    public static TraceX getInstance(Class c) {
        for (TraceX traceX : loggers) {
            if (c.getName().equals(traceX.getClassReference().getName())) return traceX;
        }
        return new TraceX(c);
    }

    public static TraceX getInstance(String className) throws ClassNotFoundException {
        Class c = Class.forName(className);
        return getInstance(c);
    }

    private static void addTraceX(TraceX traceX) {
        if (!loggers.contains(traceX)) loggers.add(traceX);
    }

    public static void readConfigurationFile(String filePath) throws ParserConfigurationException, IOException, URISyntaxException, SAXException, ClassNotFoundException {
        ConfigurationFile.readConfiguration(filePath);
    }

    /*
     *      PUBLIC FUNCTION
     */

    public void log(CustomPriority customPriority, Object message) {
        sendLog(customPriority, message);
    }

    public void trace(Object message) {
        sendLog(Priority.TRACE.toCustomPriority(), message);
    }

    public void debug(Object message) {
        sendLog(Priority.DEBUG.toCustomPriority(), message);
    }

    public void info(Object message) {
        sendLog(Priority.INFO.toCustomPriority(), message);
    }

    public void warn(Object message) {
        sendLog(Priority.WARN.toCustomPriority(), message);
    }

    public void error(Object message) {
        sendLog(Priority.ERROR.toCustomPriority(), message);
    }

    public void error(Exception exception) {
        sendLog(Priority.ERROR.toCustomPriority(), exception);
    }

    public void fatal(Object message) {
        sendLog(Priority.FATAL.toCustomPriority(), message);
    }

    public void fatal(Object message, int exitCode) {
        sendLog(Priority.FATAL.toCustomPriority(), message);
        System.exit(exitCode);
    }


    /*
     *      PRIVATE FUNCTION
     */

    private void sendLog(CustomPriority customPriority, Object message) {
        TraceMessage traceMessage = new TraceMessage(customPriority, message);
        for (TraceListener txl : getListeners()) txl.onLogEvent(traceMessage);
    }

    /*
     *      LISTENER
     */

    public void registerListener(TraceListener traceListener) {
        listeners.add(traceListener);
    }

    /*
     *      GETTER
     */

    public static List<TraceX> getAll() {
        return loggers;
    }

    public List<TraceListener> getListeners() {
        return listeners;
    }

    public Class getClassReference() {
        return classReferenced;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }
}
