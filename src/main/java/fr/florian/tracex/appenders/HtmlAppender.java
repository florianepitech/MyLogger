package fr.florian.tracex.appenders;

import fr.florian.tracex.TraceX;
import fr.florian.tracex.TraceListener;
import fr.florian.tracex.objects.TraceMessage;

public class HtmlAppender implements TraceListener {

    private TraceX traceX;

    public HtmlAppender(TraceX traceX) {
        this.traceX = traceX;
    }

    @Override
    public void onLogEvent(TraceMessage message) {

    }

}
