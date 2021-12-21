package fr.florian.tracex.appenders;

import fr.florian.tracex.TraceX;
import fr.florian.tracex.TraceXCore;
import fr.florian.tracex.TraceXListener;
import fr.florian.tracex.objects.TraceXMessage;

import java.util.logging.XMLFormatter;

public class HtmlAppender implements TraceXListener {

    private TraceX traceX;

    public HtmlAppender(TraceX traceX) {
        this.traceX = traceX;
        TraceXCore.registerListener(this);
    }

    @Override
    public void onLogEvent(TraceXMessage message) {

    }

}
