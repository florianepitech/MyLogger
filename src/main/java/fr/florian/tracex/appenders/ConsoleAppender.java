package fr.florian.tracex.appenders;

import fr.florian.tracex.TraceX;
import fr.florian.tracex.TraceXCore;
import fr.florian.tracex.TraceXListener;
import fr.florian.tracex.enums.Priority;
import fr.florian.tracex.objects.TraceXMessage;
import fr.florian.tracex.utils.ConsoleColors;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.lang.model.util.Elements;
import java.math.BigInteger;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ConsoleAppender implements TraceXListener {

    private TraceX traceX;
    private Priority traceXLevel = Priority.ALL;

    private static boolean colored = true;
    private static String format = "{line} ({trace}) [{name}] [{type}] {date} : {message}";
    private static String name = "LOGGER", version = null;
    private static long processId = ProcessHandle.current().pid();
    private static String dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
    private static ZoneId zoneId = ZoneId.systemDefault();
    private static BigInteger nextLine = new BigInteger("1");

    public ConsoleAppender(TraceX traceX) {
        TraceXCore.registerListener(this);
    }

    public ConsoleAppender(TraceX traceX, NodeList nodeList) {
        TraceXCore.registerListener(this);
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nodeAt = nodeList.item(i);
            if (nodeAt.getNodeType() == Node.ELEMENT_NODE
                    && nodeAt.getTextContent() != null
                    && nodeAt.getTextContent().length() > 0) {
                if (nodeAt.getNodeName().equals("format")) {
                    setFormat(nodeAt.getTextContent());
                } else if (nodeAt.getNodeName().equals("name")) {
                    setName(nodeAt.getTextContent());
                } else if(nodeAt.getNodeName().equals("line")) {
                    setNextLine(new BigInteger(nodeAt.getTextContent()));
                } else if (nodeAt.getNodeName().equals("colored")) {
                    setColored(Boolean.parseBoolean(nodeAt.getTextContent()));
                }
            }
        }
    }

    @Override
    public void onLogEvent(TraceXMessage message) {
        if (message.getPriority().getLevel() < getTraceXLevel().getLevel()) return;
        String messageStr = (message.getData() != null) ? message.getData().toString() : "null";
        String result = format;
        result = result.replaceAll("\\{line}", nextLine.toString());
        result = result.replaceAll("\\{trace}", getFormattedTrace());
        result = result.replaceAll("\\{name}", name);
        result = result.replaceAll("\\{version}", version);
        result = result.replaceAll("\\{process}", String.valueOf(processId));
        result = result.replaceAll("\\{type}", (colored) ? message.getPriority().getPrefixColored() : message.getPriority().getPrefix());
        result = result.replaceAll("\\{date}", getFormattedDate(message.getZonedDateTime()));
        result = result.replaceAll("\\{message}", messageStr);
        System.out.println(result);
        nextLine = nextLine.add(new BigInteger("1"));
    }

    private static String getFormattedTrace() {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        StackTraceElement stackTraceElement = stackTraceElements[5];
        return stackTraceElement.getFileName() + ":" + stackTraceElement.getMethodName() + ":" + stackTraceElement.getLineNumber();
    }

    private static String getFormattedDate(ZonedDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(getDateFormat());
        return formatter.format(date);
    }

    /*
     *      GETTER & SETTER
     */

    public TraceX getTraceX() {
        return traceX;
    }

    public void setTraceX(TraceX traceX) {
        this.traceX = traceX;
    }

    public Priority getTraceXLevel() {
        return traceXLevel;
    }

    public void setPriority(Priority priority) {
        this.traceXLevel = priority;
    }

    public static boolean isColored() {
        return colored;
    }

    public static void setColored(boolean colored) {
        ConsoleAppender.colored = colored;
    }

    public static String getFormat() {
        return format;
    }

    public static void setFormat(String format) {
        ConsoleAppender.format = format;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        ConsoleAppender.name = name;
    }

    public static String getVersion() {
        return version;
    }

    public static void setVersion(String version) {
        ConsoleAppender.version = version;
    }

    public static long getProcessId() {
        return processId;
    }

    public static void setProcessId(long processId) {
        ConsoleAppender.processId = processId;
    }

    public static String getDateFormat() {
        return dateFormat;
    }

    public static void setDateFormat(String dateFormat) {
        ConsoleAppender.dateFormat = dateFormat;
    }

    public static ZoneId getZoneId() {
        return zoneId;
    }

    public static void setZoneId(ZoneId zoneId) {
        ConsoleAppender.zoneId = zoneId;
    }

    public static BigInteger getNextLine() {
        return nextLine;
    }

    public static void setNextLine(BigInteger nextLine) {
        ConsoleAppender.nextLine = nextLine;
    }
}
