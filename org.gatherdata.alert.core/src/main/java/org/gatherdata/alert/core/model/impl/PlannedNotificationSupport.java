/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
package org.gatherdata.alert.core.model.impl;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.gatherdata.alert.core.model.PlannedNotification;
import org.gatherdata.commons.model.impl.DescribedEntitySupport;

public class PlannedNotificationSupport {

    public static boolean deepEquals(PlannedNotification lhs, PlannedNotification rhs) {
        boolean areEqual = true;
        
        if (lhs != rhs) {
            areEqual = DescribedEntitySupport.deepEquals(lhs, rhs);

            if (areEqual) { // go deeper
                // check template and event-type
                areEqual = LanguageScriptSupport.deepEquals(lhs.getTemplate(), rhs.getTemplate());
            }
        }
        return areEqual;
    }

}
