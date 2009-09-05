package org.gatherdata.alert.builder;

import java.net.URI;
import java.net.URISyntaxException;

import org.gatherdata.alert.core.model.MutablePlannedNotification;
import org.gatherdata.alert.core.model.PlannedNotification;

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
        return plannedNotification;
    }
}
