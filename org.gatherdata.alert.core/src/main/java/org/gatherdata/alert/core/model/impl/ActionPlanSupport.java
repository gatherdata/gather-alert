package org.gatherdata.alert.core.model.impl;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.gatherdata.alert.core.model.ActionPlan;
import org.gatherdata.alert.core.model.PlannedNotification;
import org.gatherdata.commons.model.DescribedEntitySupport;
import org.gatherdata.commons.model.UniqueEntitySupport;

public class ActionPlanSupport {

    public int hashCode(ActionPlan entity) {
        final URI uid = entity.getUid();
        final int prime = 31;
        int result = 1;
        result = prime * result + ((uid == null) ? 0 : uid.hashCode());
        return result;
    }

    public boolean equals(ActionPlan lhs, ActionPlan rhs) {
        if (lhs == rhs)
            return true;
        if (rhs == null)
            return false;
        if (lhs.getUid() == null) {
            if (rhs.getUid() != null)
                return false;
        } else if (!lhs.getUid().equals(rhs.getUid()))
            return false;
        
        if (lhs.getEventType() == null) {
            if (rhs.getEventType() != null)
                return false;
        } else if (!lhs.getEventType().equals(rhs.getEventType()))
            return false;
        
        if (lhs.getName() == null) {
            if (rhs.getName() != null)
                return false;
        } else if (!lhs.getName().equals(rhs.getName()))
            return false;
        
        return true;
    }

    public static boolean deepEquals(ActionPlan lhs, ActionPlan rhs) {
        boolean areEqual = true;
    
        if (lhs != rhs) { // don't bother comparing object to itself
            areEqual = DescribedEntitySupport.deepEquals(lhs, rhs);
    
            if (areEqual) { // check EventTypes
                DescribedEntitySupport.deepEquals(lhs.getEventType(), rhs.getEventType());
    
                if (areEqual) { // check PlannedNotifications
    
                    Map<URI, PlannedNotification> lhsNotificationMap = new HashMap<URI, PlannedNotification>();
                    for (PlannedNotification notification : lhs.getNotifications()) {
                        lhsNotificationMap.put(notification.getUid(), notification);
                    }
                    Map<URI, PlannedNotification> rhsNotificationMap = new HashMap<URI, PlannedNotification>();
                    for (PlannedNotification notification : rhs.getNotifications()) {
                        rhsNotificationMap.put(notification.getUid(), notification);
                    }
                    areEqual = lhsNotificationMap.size() == rhsNotificationMap.size();
                    if (areEqual) { // check RuleSets
                        for (URI key : lhsNotificationMap.keySet()) {
                            PlannedNotification lhsNotification = lhsNotificationMap.get(key);
                            PlannedNotification rhsNotification = rhsNotificationMap.get(key);
    
                            areEqual = PlannedNotificationSupport.deepEquals(lhsNotification, rhsNotification);
    
                        }
                        
                        if (areEqual) { // check RuleSets
                            areEqual = RuleSetSupport.deepEquals(lhs.getRuleSet(), rhs.getRuleSet());
                        }
                    }
                }
            }
        }
    
        return areEqual;
    }
}
