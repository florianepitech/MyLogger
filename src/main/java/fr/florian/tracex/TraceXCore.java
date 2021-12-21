package fr.florian.tracex;

import java.util.ArrayList;
import java.util.List;

public class TraceXCore {

    /*
     *      TRACE X
     */

    private static List<TraceX> loggers = new ArrayList<>();

    public static void addTraceX(TraceX traceX) {
        if (!loggers.contains(traceX)) loggers.add(traceX);
    }

    public static TraceX getTraceX(Package p) {
        return getTraceX(p.getName());
    }

    public static TraceX getTraceX(Class c) {
        return getTraceX(c.getPackage().getName());
    }

    public static TraceX getTraceX(String packageName) {
        for (TraceX traceX : loggers) {
            if (traceX.getPackage().getName().equals(packageName)) return traceX;
        }
        return null;
    }

    /*
     *      LISTENER
     */

    private static List<TraceXListener> listeners = new ArrayList<TraceXListener>();

    public static void registerListener(TraceXListener traceXListener) {
        listeners.add(traceXListener);
    }

    public static List<TraceXListener> getListeners() {
        return listeners;
    }
}
