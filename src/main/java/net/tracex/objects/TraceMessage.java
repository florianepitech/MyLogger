package net.tracex.objects;

import net.tracex.priority.CustomPriority;

import java.time.ZonedDateTime;

public class TraceMessage {

    private CustomPriority customPriority;
    private Object data;
    private ZonedDateTime zonedDateTime;

    public TraceMessage(CustomPriority customPriority, Object data) {
        this.customPriority = customPriority;
        this.data = data;
        this.zonedDateTime = ZonedDateTime.now();
    }

    public TraceMessage(CustomPriority customPriority, Object data, ZonedDateTime zonedDateTime) {
        this.customPriority = customPriority;
        this.data = data;
        this.zonedDateTime = zonedDateTime;
    }

    /*
     *      GETTER
     */

    public CustomPriority getCustomPriority() {
        return customPriority;
    }

    public Object getData() {
        return data;
    }

    public ZonedDateTime getZonedDateTime() {
        return zonedDateTime;
    }
}
