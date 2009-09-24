package org.gatherdata.alert.core.model.impl;

import java.net.URI;

import org.gatherdata.alert.core.model.DetectableEventType;
import org.gatherdata.alert.core.model.LanguageScript;
import org.gatherdata.alert.core.model.PlannedNotification;
import org.gatherdata.commons.model.MutableDescribedEntity;

public class MutablePlannedNotification extends MutableDescribedEntity implements PlannedNotification {

    /**
     * Auto-generated
     */
    private static final long serialVersionUID = 4371753026929595918L;
    
    
    private URI destination;
    private LanguageScript template;
    private DetectableEventType eventType;

    public URI getDestination() {
        return this.destination;
    }
    
    public void setDestination(URI destination) {
        this.destination = destination;
    }

    public LanguageScript getTemplate() {
        return this.template;
    }

    public void setTemplate(LanguageScript template) {
        this.template = template;
    }

    public DetectableEventType getEventType() {
        return this.eventType;
    }
    
    public void setEventType(DetectableEventType eventType) {
        this.eventType = eventType;
    }
}
