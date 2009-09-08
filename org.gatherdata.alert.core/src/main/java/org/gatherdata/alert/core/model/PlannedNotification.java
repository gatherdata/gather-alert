package org.gatherdata.alert.core.model;

import java.net.URI;

import org.gatherdata.commons.model.DescribedEntity;

/**
 * A PlannedNotification specifies the notification which should
 * be sent in response to a DetectedEvent.
 *
 */
public interface PlannedNotification extends DescribedEntity {
    
    public URI getDestination();
    
    public LanguageScript getTemplate();
    
    public DetectableEventType getEventType();
    
}
