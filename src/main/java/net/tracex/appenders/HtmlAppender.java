package net.tracex.appenders;

import net.tracex.TraceX;
import net.tracex.TraceListener;
import net.tracex.objects.TraceMessage;

public class HtmlAppender implements TraceListener {

    private TraceX traceX;

    public HtmlAppender(TraceX traceX) {
        this.traceX = traceX;
    }

    @Override
    public void onLogEvent(TraceMessage message) {

    }

}
