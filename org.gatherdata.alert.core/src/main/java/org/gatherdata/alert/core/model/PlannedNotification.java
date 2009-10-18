package org.gatherdata.alert.core.model;

import java.net.URI;

import org.gatherdata.commons.model.DescribedEntity;
import org.gatherdata.commons.model.UniqueEntity;

/**
 * A PlannedNotification specifies the notification which should
 * be sent in response to a DetectedEvent.
 *
 */
public interface PlannedNotification extends UniqueEntity {
    
    public ActionPlan getPlan();
    
    public URI getDestination();
    
    public LanguageScript getTemplate();
    
}
