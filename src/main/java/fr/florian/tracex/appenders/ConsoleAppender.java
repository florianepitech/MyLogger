package fr.florian.tracex.appenders;

import fr.florian.tracex.TraceX;
import fr.florian.tracex.TraceListener;
import fr.florian.tracex.priority.Priority;
import fr.florian.tracex.objects.TraceMessage;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.math.BigInteger;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ConsoleAppender implements TraceListener {

    private TraceX traceX;
    private Priority priority = Priority.ERROR;

    private boolean colored = true;
    private String format = "{line} ({trace}) [{name}] [{type}] {date} : {message}";
    private long processId = ProcessHandle.current().pid();
    private String dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
    private ZoneId zoneId = ZoneId.systemDefault();
    private static BigInteger nextLine = new BigInteger("1");

    public ConsoleAppender(TraceX traceX) {
        this.traceX = traceX;
    }

    public ConsoleAppender(TraceX traceX, Node node) {
        this.traceX = traceX;
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nodeAt = nodeList.item(i);
            if (nodeAt.getNodeType() == Node.ELEMENT_NODE
                    && nodeAt.getTextContent() != null
                    && nodeAt.getTextContent().length() > 0) {
                if (nodeAt.getNodeName().equals("format")) {
                    setFormat(nodeAt.getTextContent());
                } else if (nodeAt.getNodeName().equals("colored")) {
                    setColored(Boolean.parseBoolean(nodeAt.getTextContent()));
                } else if (nodeAt.getNodeName().equals("priority")) {
                    Priority priority = Priority.getPriority(nodeAt.getTextContent());
                    if (priority != null) setPriority(priority);
                }
            }
        }
    }

    @Override
    public void onLogEvent(TraceMessage message) {
        if (message.getCustomPriority().getLevel() < getPriority().getLevel()) return;
        String messageStr = (message.getData() != null) ? message.getData().toString() : "null";
        String result = format;
        result = result.replaceAll("\\{line}", nextLine.toString());
        result = result.replaceAll("\\{trace}", getFormattedTrace());
        result = result.replaceAll("\\{name}", getTraceX().getAppName());
        result = result.replaceAll("\\{version}", getTraceX().getAppVersion());
        result = result.replaceAll("\\{process}", String.valueOf(processId));
        result = result.replaceAll("\\{type}", (colored) ? message.getCustomPriority().getPrefixColored() : message.getCustomPriority().getPrefix());
        result = result.replaceAll("\\{date}", getFormattedDate(message.getZonedDateTime()));
        result = result.replaceAll("\\{message}", messageStr);
        System.out.println(result);
        nextLine = nextLine.add(new BigInteger("1"));
    }

    private String getFormattedTrace() {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        StackTraceElement stackTraceElement = stackTraceElements[5];
        return stackTraceElement.getFileName() + ":" + stackTraceElement.getMethodName() + ":" + stackTraceElement.getLineNumber();
    }

    private String getFormattedDate(ZonedDateTime date) {
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

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public boolean isColored() {
        return colored;
    }

    public void setColored(boolean colored) {
        this.colored = colored;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public long getProcessId() {
        return processId;
    }

    public void setProcessId(long processId) {
        this.processId = processId;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public ZoneId getZoneId() {
        return zoneId;
    }

    public void setZoneId(ZoneId zoneId) {
        this.zoneId = zoneId;
    }

    public BigInteger getNextLine() {
        return nextLine;
    }

    public void setNextLine(BigInteger nextLine) {
        this.nextLine = nextLine;
    }
}
