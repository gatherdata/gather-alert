/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
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
