/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
package org.gatherdata.alert.core.model.impl;

import static org.junit.Assert.assertTrue;

import java.net.URI;
import java.net.URISyntaxException;

import org.gatherdata.alert.core.model.PlannedNotification;
import org.gatherdata.alert.core.model.mock.MockActionPlanFactory;
import org.gatherdata.alert.core.model.mock.MockPlannedNotificationFactory;
import org.junit.Test;

public class MutablePlannedNotificationTest {

    @Test
    public void shouldBeEqualToCopy() {
        PlannedNotification originalNotification = MockPlannedNotificationFactory.create();
        MutablePlannedNotification copiedNotification = new MutablePlannedNotification();
        copiedNotification.copy(originalNotification);
        
        assertTrue(copiedNotification.equals(originalNotification));
        assertTrue(copiedNotification.hashCode() == originalNotification.hashCode());
        assertTrue(PlannedNotificationSupport.deepEquals(originalNotification, copiedNotification));

    }
    
    @Test
    public void shouldUpdateDestinationFromPartial() throws URISyntaxException {
        URI updatedDestination = new URI("mailto:updated@nowhere.org");
        
        MutablePlannedNotification originalNotification = MockPlannedNotificationFactory.create();
        MutablePlannedNotification partialNotification = new MutablePlannedNotification();
        partialNotification.setDestination(updatedDestination);
        
        originalNotification.update((PlannedNotification)partialNotification);
        
    }
}
