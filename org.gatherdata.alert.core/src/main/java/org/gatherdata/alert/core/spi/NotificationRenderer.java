/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
package org.gatherdata.alert.core.spi;

import org.gatherdata.alert.core.model.PlannedNotification;

/**
 * The NotificationRenderer renders the template contained
 * in a PlannedNotification.
 *
 */
public interface NotificationRenderer {

    public String render(PlannedNotification notification);
    
}
