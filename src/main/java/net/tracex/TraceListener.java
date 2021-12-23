package net.tracex;

import net.tracex.objects.TraceMessage;

public interface TraceListener {

    void onLogEvent(TraceMessage message);

}
