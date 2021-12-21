package fr.florian.tracex;

import fr.florian.tracex.objects.TraceXMessage;

public interface TraceXListener {

    void onLogEvent(TraceXMessage message);

}
