package org.gatherdata.alert.core.model;

import java.net.URI;

import org.gatherdata.commons.model.MutableDescribedEntity;

public class MutablePlannedNotification extends MutableDescribedEntity implements PlannedNotification {

    /**
     * Auto-generated
     */
    private static final long serialVersionUID = 4371753026929595918L;
    
    
    private URI destination;
    private LanguageScript template;

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

}
