package fr.florian.tracex;

import fr.florian.tracex.enums.Priority;
import fr.florian.tracex.exceptions.UnknownPackageException;
import fr.florian.tracex.objects.TraceXMessage;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URISyntaxException;

public class TraceX {

    private Package p;

    /*
     *      CONSTRUCTOR
     */

    public TraceX(Package p) {
        TraceXCore.addTraceX(this);
        this.p = p;
    }

    public TraceX(String packageName) throws UnknownPackageException {
        Package[] packages = Package.getPackages();
        for (Package p : packages) {
            if (p.getName().equals(packageName)) {
                TraceXCore.addTraceX(this);
                this.p = p;
                return;
            }
        }
        throw new UnknownPackageException(packageName);
    }

    public TraceX(Class c) {
        Package p = c.getPackage();
        TraceXCore.addTraceX(this);
        this.p = c.getPackage();
    }

    /*
     *      STATIC FUNCTION
     */

    public static void readConfigurationFile(String filePath) throws ParserConfigurationException, IOException, URISyntaxException, SAXException, UnknownPackageException {
        ConfigurationFile.readConfiguration(filePath);
    }

    /*
     *      PUBLIC FUNCTION
     */

    public void trace(Object message) {
        log(Priority.TRACE, message);
    }

    public void debug(Object message) {
        log(Priority.DEBUG, message);
    }

    public void info(Object message) {
        log(Priority.INFO, message);
    }

    public void warn(Object message) {
        log(Priority.WARN, message);
    }

    public void error(Object message) {
        log(Priority.ERROR, message);
    }

    public void error(Exception exception) {
        log(Priority.ERROR, ExceptionUtils.getMessage(exception));
        exception.printStackTrace();
    }

    public void fatal(Object message) {
        log(Priority.FATAL, message);
        System.exit(2);
    }

    public void fatal(Object message, int exitCode) {
        log(Priority.FATAL, message);
        System.exit(exitCode);
    }


    /*
     *      PRIVATE FUNCTION
     */

    private synchronized void log(Priority logType, Object message) {
        TraceXMessage traceXMessage = new TraceXMessage(logType, message);
        for (TraceXListener txl : TraceXCore.getListeners()) txl.onLogEvent(traceXMessage);
    }

    /*
     *      GETTER
     */

    public Package getPackage() {
        return p;
    }
}
