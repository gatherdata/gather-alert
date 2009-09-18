package org.gatherdata.alert.core.model;

import org.apache.commons.lang.builder.EqualsBuilder;

public class PlannedNotificationSupport {

    public static boolean deepEquals(PlannedNotification lhs, PlannedNotification rhs) {
        boolean areEqual = true;
        
        if (lhs != rhs) {
            areEqual = DescribedEntitySupport.deepEquals(lhs, rhs);

            if (areEqual) { // go deeper
                // check template and event-type
                areEqual = LanguageScriptSupport.deepEquals(lhs.getTemplate(), rhs.getTemplate());
            
                if (areEqual) {
                    areEqual = DescribedEntitySupport.deepEquals(lhs.getEventType(), rhs.getEventType());
                }
            }
        }
        return areEqual;
    }

}
