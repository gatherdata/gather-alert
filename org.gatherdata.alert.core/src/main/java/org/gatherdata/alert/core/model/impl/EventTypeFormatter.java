package org.gatherdata.alert.core.model.impl;

import org.gatherdata.alert.core.model.DetectableEventType;

public class EventTypeFormatter {
    public static String toString(DetectableEventType eventType) {
        return "DetectableEventType [" + eventType.getName() + ": " + eventType.getDescription() + "]";
    }
}
