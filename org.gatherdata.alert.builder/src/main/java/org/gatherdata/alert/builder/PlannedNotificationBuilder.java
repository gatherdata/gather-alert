package org.gatherdata.alert.builder;

import java.net.URI;
import java.net.URISyntaxException;

import org.gatherdata.alert.core.model.DetectableEventType;
import org.gatherdata.alert.core.model.MutablePlannedNotification;
import org.gatherdata.alert.core.model.PlannedNotification;
import org.gatherdata.commons.net.CbidFactory;
import org.joda.time.DateTime;

public class PlannedNotificationBuilder implements FluentBuilder<PlannedNotification> {

    private final MutablePlannedNotification plannedNotification = new MutablePlannedNotification();
    
    private PlannedNotificationBuilder() {
        ;
    }
    
    public static PlannedNotificationBuilder address(String addressUri) {
        return new PlannedNotificationBuilder().destination(addressUri);
    }

    public PlannedNotificationBuilder destination(String addressUri) {
        try {
            plannedNotification.setDestination(new URI(addressUri));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return this;
    }

    public PlannedNotificationBuilder message(LanguageScriptBuilder messageTemplate) {
        plannedNotification.setTemplate(messageTemplate.build());
        return this;
    }

    public PlannedNotification build() {
        if (plannedNotification.getName() == null) {
            plannedNotification.setName("plan");
        }
        if (plannedNotification.getDescription() == null) {
            plannedNotification.setDescription("no details available");
        }
        if (plannedNotification.getDateCreated() == null) {
            plannedNotification.setDateCreated(new DateTime());
        }
        if (plannedNotification.getUid() == null) {
            plannedNotification.setUid(CbidFactory.createCbid(
                    plannedNotification.getName() +
                    plannedNotification.getDescription() +
                    plannedNotification.getDestination().toASCIIString() +
                    plannedNotification.getDateCreated().toString()
                    ));
        }
        return plannedNotification;
    }

    public void setEventType(DetectableEventType eventType) {
        plannedNotification.setEventType(eventType);
    }
}
