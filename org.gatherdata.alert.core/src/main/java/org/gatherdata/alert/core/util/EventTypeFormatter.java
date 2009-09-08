package org.gatherdata.alert.core.util;

import org.gatherdata.alert.core.model.DetectableEventType;

public class EventTypeFormatter {
    public static String toString(DetectableEventType eventType) {
        return "DetectableEventType [" + eventType.getName() + "]";
    }
}
