package org.gatherdata.alert.core.spi;

import org.gatherdata.alert.core.model.PlannedNotification;

/**
 * The NotificationRenderer renders the template contained
 * in a PlannedNotification.
 *
 */
public interface NotificationRenderer {

    public String render(PlannedNotification notification);
    
}
