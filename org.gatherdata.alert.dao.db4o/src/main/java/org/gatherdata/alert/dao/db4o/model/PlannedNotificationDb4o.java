package org.gatherdata.alert.dao.db4o.model;

import java.net.URI;

import org.gatherdata.alert.core.model.DetectableEventType;
import org.gatherdata.alert.core.model.LanguageScript;
import org.gatherdata.alert.core.model.PlannedNotification;
import org.gatherdata.commons.db.db4o.model.DescribedEntityDb4o;
import org.joda.time.DateTime;

public class PlannedNotificationDb4o extends DescribedEntityDb4o implements PlannedNotification {

    private URI destination;
    private DetectableEventTypeDb4o eventType;
    private LanguageScriptDb4o template;

    public URI getDestination() {
        return this.destination;
    }
    
    public void setDestination(URI destination) {
        this.destination = destination;
    }

    public DetectableEventType getEventType() {
        return this.eventType;
    }
    
    public void setEventType(DetectableEventTypeDb4o eventType) {
        this.eventType = eventType;
    }

    public LanguageScript getTemplate() {
        return this.template;
    }
    
    public void setTemplate(LanguageScriptDb4o template) {
        this.template = template;
    }

    public PlannedNotificationDb4o copy(PlannedNotification template) {
        if ((template != null) && (template != this)) {
            super.copy(template);
            setDestination(template.getDestination());
            DetectableEventType templateEventType = template.getEventType();
            if (templateEventType != null) {
                setEventType((DetectableEventTypeDb4o) new DetectableEventTypeDb4o().copy(templateEventType));
            }
            LanguageScript templateTemplate = template.getTemplate();
            if (templateTemplate != null) {
                setTemplate(new LanguageScriptDb4o().copy(templateTemplate));
            }
        }
        return this;
    }

}
