package fr.florian.tracex;

import fr.florian.tracex.objects.TraceMessage;

public interface TraceListener {

    void onLogEvent(TraceMessage message);

}
