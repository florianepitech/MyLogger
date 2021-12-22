package fr.florian.tracex;

import fr.florian.tracex.priority.CustomPriority;
import fr.florian.tracex.priority.Priority;
import fr.florian.tracex.exceptions.UnknownPackageException;
import fr.florian.tracex.objects.TraceMessage;
import org.apache.commons.lang3.exception.ExceptionUtils;
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
    private Package p;

    /*
     *      CONSTRUCTOR
     */

    private TraceX(Package p) {
        this.p = p;
        loggers.add(this);
    }

    /*
     *      STATIC FUNCTION
     */

    public static TraceX getInstance(Package p) {
        for (TraceX traceX : loggers) {
            if (p.equals(traceX.getPackage())) return traceX;
        }
        return new TraceX(p);
    }

    public static TraceX getInstance(Class c) {
        return getInstance(c.getPackage());
    }

    public static TraceX getInstance(String packageName) throws UnknownPackageException {
        Package p = getPackage(packageName);
        if (p == null) throw new UnknownPackageException(packageName);
        return getInstance(p);
    }

    private static Package getPackage(String packageName) {
        for (Package p : Package.getPackages()) {
            if (p.getName().equals(packageName)) return p;
            if (p.getName().startsWith(packageName)) return p;
        }
        return null;
    }

    private static void addTraceX(TraceX traceX) {
        if (!loggers.contains(traceX)) loggers.add(traceX);
    }

    public static void readConfigurationFile(String filePath) throws ParserConfigurationException, IOException, URISyntaxException, SAXException, UnknownPackageException {
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

    public Package getPackage() {
        return p;
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
