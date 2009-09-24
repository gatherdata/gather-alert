package org.gatherdata.alert.core.model.impl;

import org.gatherdata.alert.core.model.PlannedNotification;

public class PlannedNotificationFormatter {

    public static String toLongString(PlannedNotification notification) {
        return "PlannedNotification [" + notification.getName() + ": " + 
            notification.getDestination() + 
            " \"" + notification.getTemplate().getScript() + "\"]";
    }

    
}
