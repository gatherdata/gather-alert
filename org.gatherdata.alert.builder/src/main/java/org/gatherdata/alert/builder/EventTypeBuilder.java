package org.gatherdata.alert.builder;

import java.net.URI;
import java.net.URISyntaxException;

import org.gatherdata.alert.core.model.ActionPlan;
import org.gatherdata.alert.core.model.DetectableEventType;
import org.gatherdata.alert.core.model.MutableDetectableEventType;
import org.gatherdata.commons.net.CbidFactory;

public class EventTypeBuilder implements FluentBuilder<DetectableEventType> {

    private final MutableDetectableEventType eventType = new MutableDetectableEventType();

    public static EventTypeBuilder event() {
        return new EventTypeBuilder();
    }

    public static EventTypeBuilder event(String namedOfEventType) {
        return new EventTypeBuilder().named(namedOfEventType);
    }
    
    private EventTypeBuilder() {
        ;
    }
        
    public DetectableEventType build() {
        if (eventType.getName() == null) {
            eventType.setName("overactive imagination");
        }
        if (eventType.getDescription() == null) {
            eventType.setDescription("lions, and tigers, and bears, oh my.");
        }
        if (eventType.getUid() == null) {
            eventType.setUid(CbidFactory.createCbid(eventType.getName() + eventType.getDescription()));
        }
        return eventType;
    }
    
    public EventTypeBuilder named(String nameOfEventType) {
        eventType.setName(nameOfEventType);
        return this;
    }
    
    public EventTypeBuilder describedAs(String descriptionOfEventType) {
        eventType.setDescription(descriptionOfEventType);
        return this;
    }
    
    public EventTypeBuilder identifiedAs(String uid) {
        try {
            eventType.setUid(new URI(uid));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return this;
    }

}
