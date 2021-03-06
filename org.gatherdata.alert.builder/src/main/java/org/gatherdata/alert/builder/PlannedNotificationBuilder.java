/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
package org.gatherdata.alert.builder;

import java.net.URI;
import java.net.URISyntaxException;

import org.gatherdata.alert.core.model.PlannedNotification;
import org.gatherdata.alert.core.model.impl.MutablePlannedNotification;
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

    public MutablePlannedNotification build() {
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

}
