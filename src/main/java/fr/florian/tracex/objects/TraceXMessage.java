package fr.florian.tracex.objects;

import fr.florian.tracex.enums.Priority;

import java.time.ZonedDateTime;

public class TraceXMessage {

    private Priority traceXLevel;
    private Object data;
    private ZonedDateTime zonedDateTime;

    public TraceXMessage(Priority traceXLevel, Object data) {
        this.traceXLevel = traceXLevel;
        this.data = data;
        this.zonedDateTime = ZonedDateTime.now();
    }

    public TraceXMessage(Priority priority, Object data, ZonedDateTime zonedDateTime) {
        this.traceXLevel = priority;
        this.data = data;
        this.zonedDateTime = zonedDateTime;
    }

    /*
     *      GETTER
     */

    public Priority getPriority() {
        return traceXLevel;
    }

    public Object getData() {
        return data;
    }

    public ZonedDateTime getZonedDateTime() {
        return zonedDateTime;
    }
}
